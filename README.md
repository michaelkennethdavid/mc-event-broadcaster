# MC Event Broadcaster (Forge)

This mod allows you to subscribe to events in Minecraft, such as when a player joins or leaves the server, and publish these events to a webhook server. This can be useful for creating custom integrations with other applications or services, or simply for keeping track of activity on your Minecraft server. With this mod, you can easily create custom notifications or triggers for any event you choose, giving you more control over your Minecraft experience.

---

## Configuration
`config/eventbroadcaster-common.toml`
```toml
[playerJoinedEvent]
	#Use #{playerName} to get the player's name
	payload = "{ \"message\": \"#{playerName} joined the game\" }"
	#Whether to publish player joined events or not
	enabled = true

[playerLeftEvent]
	#Use #{playerName} to get the player's name
	payload = "{ \"message\": \"#{playerName} left the game\" }"
	#Whether to publish player left events or not
	enabled = true

[server]
	#Server that receives the webhook
	endpoint = "https:/my.website.webhook"
	#Authentication token
	bearerToken = "103955a8a4a14175b735875e957d520c"
```
