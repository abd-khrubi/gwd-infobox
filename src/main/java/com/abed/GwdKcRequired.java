package com.abed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.Varbits;

@Getter
@AllArgsConstructor
public enum GwdKcRequired {
	GRANDMASTER(Varbits.COMBAT_ACHIEVEMENT_TIER_GRANDMASTER, 15),
	MASTER(Varbits.COMBAT_ACHIEVEMENT_TIER_MASTER, 25),
	ELITE(Varbits.COMBAT_ACHIEVEMENT_TIER_ELITE, 30),
	HARD(Varbits.COMBAT_ACHIEVEMENT_TIER_HARD, 35),
	DEFAULT(-1, 40); //  kc requirement for medium CA and under is not changed

	private final int combatAchievementVarbitId;
	private final int kcRequired;
}
