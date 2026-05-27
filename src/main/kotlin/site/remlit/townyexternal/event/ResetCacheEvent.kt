package site.remlit.townyexternal.event

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class ResetCacheEvent : Event() {
	override fun getHandlers(): HandlerList {
		return HANDLER_LIST
	}

	companion object {
		@JvmStatic
		@Suppress("Unused")
		fun getHandlerList(): HandlerList = HANDLER_LIST
		val HANDLER_LIST: HandlerList = HandlerList()
	}
}