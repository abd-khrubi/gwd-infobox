package com.abed;

import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;

public class GwdKcInfo {

	private final GwdKcPlugin plugin;
	private final GwdKcGods godsInfo;

	private GwdKcInfoBox infoBox = null;

	public GwdKcInfo(GwdKcPlugin plugin, GwdKcGods godsInfo) {
		this.plugin = plugin;
		this.godsInfo = godsInfo;
	}

	public void updateKc() {
		int kc = plugin.client.getVarbitValue(godsInfo.getKcVarbitId());
		boolean showZeroKc = this.plugin.config.showZeroKc();
		boolean shouldShowInfoBox = showZeroKc || kc > 0;

		if (shouldShowInfoBox) {
			if (infoBox == null) {
				BufferedImage img = ImageUtil.loadImageResource(plugin.getClass(), godsInfo.getImageFile());
				infoBox = new GwdKcInfoBox(img, plugin, godsInfo);
				plugin.infoBoxManager.addInfoBox(infoBox);
			}
			infoBox.setKc(kc);
		} else {
			removeInfoBox();
		}
	}

	public void removeInfoBox() {
		if (infoBox != null) {
			plugin.infoBoxManager.removeInfoBox(infoBox);
			infoBox = null;
		}
	}
}
