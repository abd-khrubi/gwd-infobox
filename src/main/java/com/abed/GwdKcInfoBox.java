package com.abed;

import lombok.Getter;
import lombok.Setter;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.ui.overlay.infobox.InfoBoxPriority;

import javax.annotation.Nonnull;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GwdKcInfoBox extends InfoBox {

	private final GwdKcPlugin plugin;

	@Getter
	@Setter
	private int kc;

	public GwdKcInfoBox(BufferedImage image, @Nonnull GwdKcPlugin plugin, GwdKcGods godInfo) {
		super(image, plugin);
		this.plugin = plugin;
		this.kc = 0;

		setPriority(InfoBoxPriority.LOW);
		setTooltip(godInfo.getName());
	}

	private boolean hasEnoughKc() {
		return this.kc >= this.plugin.getRequiredKc();
	}

	@Override
	public String getText() {
		return Integer.toString(kc);
	}

	@Override
	public Color getTextColor() {
		return hasEnoughKc() ? Color.GREEN : Color.WHITE;
	}
}
