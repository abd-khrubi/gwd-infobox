package com.abed;


import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@PluginDescriptor(
		name = "GWD kc info"
)
public class GwdKcPlugin extends Plugin {

	private static final int COMBAT_ACHIEVEMENT_COMPLETE = 2;

	@Inject
	public Client client;

	@Inject
	public GwdKcConfig config;

	@Inject
	public InfoBoxManager infoBoxManager;

	private GwdKcInfo[] gwdKc;

	@Override
	protected void startUp() {
		gwdKc = new GwdKcInfo[]{
				new GwdKcInfo(this, GwdKcGods.ARMADYL),
				new GwdKcInfo(this, GwdKcGods.BANDOS),
				new GwdKcInfo(this, GwdKcGods.SARADOMIN),
				new GwdKcInfo(this, GwdKcGods.ZAMORAK),
				new GwdKcInfo(this, GwdKcGods.ZAROS)
		};
	}

	@Override
	protected void shutDown() {
		cleanupInfoBoxes();
		Widget gwdKcWidget = getGwdKcWidget();
		if (gwdKcWidget != null) {
			gwdKcWidget.setHidden(false);
		}
	}

	@Provides
	GwdKcConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(GwdKcConfig.class);
	}

	@Subscribe
	public void onGameTick(GameTick event) {
		Widget gwdKcWidget = getGwdKcWidget();
		if (gwdKcWidget == null) {
			cleanupInfoBoxes();
		} else {
			gwdKcWidget.setHidden(true);
			Arrays.stream(gwdKc).filter(Objects::nonNull).forEach(GwdKcInfo::updateKc);
		}
	}

	private void cleanupInfoBoxes() {
		Arrays.stream(gwdKc).filter(Objects::nonNull).forEach(GwdKcInfo::removeInfoBox);
	}

	private Widget getGwdKcWidget() {
		return client.getWidget(ComponentID.GWD_KC_LAYER);
	}

	public int getRequiredKc() {
		for (GwdKcRequired required : GwdKcRequired.values()) {
			if (required == GwdKcRequired.DEFAULT) break;

			boolean completedCa = client.getVarbitValue(required.getCombatAchievementVarbitId()) == COMBAT_ACHIEVEMENT_COMPLETE;
			if (completedCa) {
				return required.getKcRequired();
			}
		}
		return GwdKcRequired.DEFAULT.getKcRequired();
	}
}
