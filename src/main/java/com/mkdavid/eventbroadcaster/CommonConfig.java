package com.mkdavid.eventbroadcaster;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
	private final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
	private final ForgeConfigSpec spec;

	private final PlayerJoinedEvent playerJoinedEvent;
	private final PlayerLeftEvent playerLeftEvent;
	private final Server server;

	public CommonConfig() {
		playerJoinedEvent = new PlayerJoinedEvent();
		playerLeftEvent = new PlayerLeftEvent();
		server = new Server();

		spec = builder.build();
	}

	public PlayerJoinedEvent getPlayerJoinedEvent() {
		return playerJoinedEvent;
	}

	public PlayerLeftEvent getPlayerLeftEvent() {
		return playerLeftEvent;
	}

	public Server getServer() {
		return server;
	}

	public ForgeConfigSpec getSpec() {
		return spec;
	}

	public class PlayerJoinedEvent {
		private final ForgeConfigSpec.BooleanValue enabled;
		private final ForgeConfigSpec.ConfigValue<String> payload;

		public PlayerJoinedEvent() {
			builder.push("playerJoinedEvent");

			enabled = builder.comment("Whether to publish player joined events or not").define("enabled", false);
			payload = builder.comment("Use #{playerName} to get the player's name").define("payload", "");

			builder.pop();
		}

		public boolean getEnabled() {
			return enabled.get();
		}

		public String getPayload() {
			return payload.get();
		}
	}

	public class PlayerLeftEvent {
		private final ForgeConfigSpec.BooleanValue enabled;
		private final ForgeConfigSpec.ConfigValue<String> payload;

		public PlayerLeftEvent() {
			builder.push("playerLeftEvent");

			enabled = builder.comment("Whether to publish player left events or not").define("enabled", false);
			payload = builder.comment("Use #{playerName} to get the player's name").define("payload", "");

			builder.pop();
		}

		public boolean getEnabled() {
			return enabled.get();
		}

		public String getPayload() {
			return payload.get();
		}
	}

	public class Server {
		private final ForgeConfigSpec.ConfigValue<String> endpoint;
		private final ForgeConfigSpec.ConfigValue<String> bearerToken;

		public Server() {
			builder.push("server");

			endpoint = builder.comment("Server that receives the webhook").define("endpoint", "");
			bearerToken = builder.comment("Authentication token").define("bearerToken", "");

			builder.pop();
		}

		public String getEndpoint() {
			return endpoint.get();
		}

		public String getBearerToken() {
			return bearerToken.get();
		}
	}
}
