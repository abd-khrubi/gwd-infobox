package com.abed;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("gwd_info")
public interface GwdKcConfig extends Config {

	@ConfigItem(
			keyName = "show_zero_kc",
			name = "Show zero kc",
			description = "Show GWD with zero kill count"
	)
	default boolean showZeroKc() {
		return false;
	}
}
