package org.puggu.magicandskills.actionbar;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.managers.PlayerClickManager;
import org.puggu.magicandskills.managers.PlayerEnergyManager;

public class DisplayActionBarHandler implements Listener {

    private final MagicAndSkills plugin;
    private final PlayerClickManager playerClickManager;
    private final PlayerEnergyManager playerEnergyManager;
    private final DisplayActionBarSchedule displayActionBarSchedule;

    public DisplayActionBarHandler(MagicAndSkills plugin) {
        this.plugin = plugin;
        playerClickManager = new PlayerClickManager(plugin);
        playerEnergyManager = new PlayerEnergyManager(plugin);
        displayActionBarSchedule = new DisplayActionBarSchedule(plugin);
    }

    @EventHandler
    public void handler(UpdateActionBarEvent event) {
        Player player = event.getPlayer();
        displayActionBarSchedule.updateCastSequenceBar(player,
//                playerEnergyManager.getPlayerMana(player),
//                playerEnergyManager.getPlayerStamina(player),
                playerClickManager.getCastSequenceAsString(player));
    }
}
