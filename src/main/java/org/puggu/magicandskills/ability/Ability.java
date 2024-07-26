package org.puggu.magicandskills.ability;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.experience.ExperienceUtil;
import org.puggu.magicandskills.ability.events.UpdateActionBarEvent;
import org.puggu.magicandskills.managers.PlayerCooldownManager;
import org.puggu.magicandskills.managers.PlayerEnergyManager;

public abstract class Ability {
    protected final MagicAndSkills plugin;
    protected final Player player;
    protected long cooldown;
    protected final int cost;
    protected final NamespacedKey abilityKey;

    protected Ability(MagicAndSkills plugin, Player player, long cooldown, int cost, NamespacedKey abilityKey) {
        this.plugin = plugin;
        this.cooldown = cooldown;
        this.cost = cost;
        this.player = player;
        this.abilityKey = abilityKey;
    }

//    private long lastUseTime = 0;

    public void setOnCooldown() {
//        cooldowns.put(player.getUniqueId(), System.currentTimeMillis());
    }

    public long getCooldown() {
//        if (cooldowns.get(player.getUniqueId()) == null) {
//            return -1;
//        }

//        return cooldowns.get(player.getUniqueId());
        return -1L;
    }

    public boolean isOnCooldown() {
        return (System.currentTimeMillis() - getCooldown()) < cooldown;
    }


    /**
     * The ability's main process
     */
    protected abstract void ability();

    /**
     * Deplete player resource (Mana/Stamina)
     */
    protected void depleteResource(Player player, int amount){
        player.giveExp(-amount);
    }

    /**
     * Increments player resource (Mana/Stamina)
     */
    protected void incrementResource(Player player, int amount){
        player.giveExp(amount);
    }

    /**
     * Get user Mana/Stamina
     * @return whether there's enough energy
     */
    public boolean enoughResource(Player player, int cost){
        player.sendMessage(player.getLevel() + " | " + player.getExp());
        return ExperienceUtil.calculateTotalXP(player.getLevel(), player.getExp()) >= cost;
    }

    /**
     * Implemented by MagicSpell/Skill's uniquely to handle cast fails for different reasons
     * @param player player
     * @param reason reason
     */
    public abstract void failedToCast(Player player, ReasonForCastFail reason);

    /**
     * Checks if player has resources and if ability is on cooldown
     * Depletes resource, calls ability, sets ability on cooldown
     * requests update for action bar
     */
    public void executeAbility() {
        player.sendMessage(cost + " " + ExperienceUtil.calculateTotalXP(player.getLevel(), player.getExp()));
        if (!enoughResource(player, cost)) {
            failedToCast(player, ReasonForCastFail.NOT_ENOUGH_ENERGY);
            return;
        }

        // Successful cast
        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1f, 1f);
        depleteResource(player, cost);
        ability();
//        setOnCooldown();

//        Bukkit.getServer().getPluginManager().callEvent(new UpdateActionBarEvent(player));
//        DisplayActionBarSchedule.updateEnergyBar(player,
//                playerEnergyManager.getPlayerMana(player),
//                playerEnergyManager.getPlayerStamina(player));
    }

    public NamespacedKey getAbilityKey(){
        return abilityKey;
    }
}
