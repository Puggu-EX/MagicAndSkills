package org.puggu.magicandskills.ability.skill;

import org.bukkit.entity.Player;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.Ability;
import org.puggu.magicandskills.ability.ReasonForCastFail;

public abstract class Skill extends Ability {
    private final Double kiCost;
    protected Skill(MagicAndSkills plugin, long cooldownTime, double cost) {
        super(plugin, cooldownTime, cost);
        this.kiCost = cost;
    }

    @Override
    public void depleteResource(Player player, Double amount) {
        if (playerEnergyManager.playerHasEnergyContainers(player)){
            playerEnergyManager.incrementPlayerKi(player, -amount);
        }
    }

    @Override
    public boolean enoughResource(Player player, Double cost) {
        double availableKi = playerEnergyManager.getPlayerKi(player);
        return availableKi > cost;
    }

    @Override
    public void failedToCast(Player player, ReasonForCastFail reason) {

    }

    public Double getKiCost() {
        return kiCost;
    }
}
