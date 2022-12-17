package com.mkdavid.eventbroadcaster;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;


public class HttpClientHelper {
	public static void PlayerJoinedEvent(String playerName) {
		Runnable runnable = () -> { // Change to Asynchronous HttpClient
			CommonConfig config = EventBroadcaster.COMMON_CONFIG;
			String body = config.getPlayerJoinedEvent().getPayload().replace("#{playerName}", playerName);
			SendPostRequest(config.getServer().getEndpoint(), body, config.getServer().getBearerToken());
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	public static void PlayerLeftEvent(String playerName) {
		Runnable runnable = () -> { // Change to Asynchronous HttpClient
			CommonConfig config = EventBroadcaster.COMMON_CONFIG;
			String body = config.getPlayerLeftEvent().getPayload().replace("#{playerName}", playerName);
			SendPostRequest(config.getServer().getEndpoint(), body, config.getServer().getBearerToken());
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	private static void SendPostRequest(String endpoint, String body, String token) {
		try {
			URL url = new URL(endpoint);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "Bearer " + token);
			con.setDoOutput(true);

			OutputStream os = con.getOutputStream();
			byte[] input = body.getBytes("utf-8");
			os.write(input, 0, input.length);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			in.close();

		} catch (Exception e) {
			LogManager.getLogger().error(e.getStackTrace());
		}
	}
}

