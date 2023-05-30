package com.mkdavid.eventbroadcaster;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;

@Mod(Constant.MODID)
public class EventBroadcaster {
	public static final CommonConfig COMMON_CONFIG = new CommonConfig();

	public EventBroadcaster() {
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerLoggedInEvent.class, this::onPlayerLoggedInEvent);
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, PlayerEvent.PlayerLoggedOutEvent.class, this::onPlayerLoggedOutEvent);

		ModLoadingContext.get().registerConfig(Type.COMMON, COMMON_CONFIG.getSpec());
	}

	public void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
		if (COMMON_CONFIG.getPlayerJoinedEvent().getEnabled()) {
			Player player = event.getPlayer();
			HttpClientHelper.PlayerJoinedEvent(player.getDisplayName().getString());
		}
	}

	public void onPlayerLoggedOutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
		if (COMMON_CONFIG.getPlayerLeftEvent().getEnabled()) {
			Player player = event.getPlayer();
			HttpClientHelper.PlayerLeftEvent(player.getDisplayName().getString());
		}
	}
}
