package ml.windleaf.wlkitsreforged.plugins

import ml.windleaf.wlkitsreforged.core.Plugin
import ml.windleaf.wlkitsreforged.plugins.commands.tpa.*
import ml.windleaf.wlkitsreforged.utils.Util
import org.bukkit.entity.Player

class Tpa : Plugin {
    override var name = "Tpa"
    companion object {
        var tpaLogs = HashMap<Player, Player>()
        val enabled = Util.isEnabled("Tpa")
    }

    override fun load() {
        Util.registerCommand("tpa", TpaCommand())
        Util.registerCommand("tpaccept", TpacceptCommand())
        Util.registerCommand("tpadeny", TpadenyCommand())
        Util.registerCommand("tpacancel", TpacancelCommand())
        Util.registerCommand("tpahelp", TpahelpCommand())
    }

    override fun unload() {
    }
}