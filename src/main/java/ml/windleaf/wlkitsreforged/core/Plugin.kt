package ml.windleaf.wlkitsreforged.core

interface Plugin {
    val name: String
    var enabled: Boolean
    val type: LoadType

    fun load()
    fun unload()
    fun registers()
}