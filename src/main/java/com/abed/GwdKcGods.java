package com.abed;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GwdKcGods {

	ARMADYL("Armadyl", 3973, "armadyl.png"),
	BANDOS("Bandos", 3975, "bandos.png"),
	SARADOMIN("Saradomin", 3972, "saradomin.png"),
	ZAMORAK("Zamorak", 3976, "zamorak.png"),
	ZAROS("Zaros", 13080, "zaros.png");

	private final String name;

	private final int kcVarbitId;

	private final String imageFile;
}
