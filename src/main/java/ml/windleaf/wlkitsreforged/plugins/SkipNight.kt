package ml.windleaf.wlkitsreforged.plugins

import ml.windleaf.wlkitsreforged.core.Plugin
import ml.windleaf.wlkitsreforged.core.WLKits
import ml.windleaf.wlkitsreforged.utils.Util
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBedEnterEvent
import org.bukkit.event.player.PlayerBedLeaveEvent

class SkipNight : Plugin, Listener {
    override var name = "SkipNight"
    private var enabled = Util.isEnabled(name)
    private var onBed = ArrayList<Player>()

    override fun load() {
        Util.registerEvent(this)
    }

    override fun unload() {
    }

    @EventHandler
    fun onPlayerBedEnterEvent(e: PlayerBedEnterEvent) {
        if (enabled && (e.player.world.time >= 12010 || e.player.world.isThundering)) {
            if (!onBed.contains(e.player)) onBed.add(e.player)
            val percent = Util.getPluginConfig(name, "percent") as Int
            if (percent < 0 || percent > 100)
                WLKits.log("&c错误: 配置文件中 &6plugins.skipnight.percent &c数值小于 0 或大于 100, 请重新进行配置!")
            else {
                if ((onBed.size / Bukkit.getOnlinePlayers().size) >= (percent / 100)) {
                    Util.broadcastPlayers(Util.getPluginMsg(name, "msg-ok"))
                    val world: World = e.player.world
                    world.time = 100
                } else {
                    val i = HashMap<String, String>()
                    i["onBed"] = onBed.size.toString()
                    var fakePlayers = 0
                    while ((fakePlayers / Bukkit.getOnlinePlayers().size) < (percent / 100)) fakePlayers += 1
                    i["needPlayers"] = fakePlayers.toString()
                    Util.broadcastPlayers(Util.insert(Util.getPluginMsg(name, "msg-need"), i))
                }
            }
        }
    }

    @EventHandler
    fun onPlayerBedLeaveEvent(e: PlayerBedLeaveEvent) {
        if (enabled) onBed.remove(e.player)
    }
}