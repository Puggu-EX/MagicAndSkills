package org.puggu.magicandskills.ability.skill;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.puggu.magicandskills.MagicAndSkills;
import org.puggu.magicandskills.ability.events.ArrowStormEvent;

public class ArrowStorm extends Skill implements Listener {

    public ArrowStorm(MagicAndSkills plugin) {
        super(plugin, 2000, 10);
    }

    private Player player;

    @EventHandler
    public void onArrowStormEvent(ArrowStormEvent event) {
        System.out.println("Arrow Storm Event Heard");
        player = event.getPlayer();
        executeAbility(player);
    }

    @Override
    protected boolean ability() {

        int numberOfArrows = 10; // Change this value to the desired number of arrows

        new BukkitRunnable() {
            int arrowsSpawned = 0;

            @Override
            public void run() {
                if (arrowsSpawned < numberOfArrows) {
                    // Spawn an arrow at the calculated location
                    Arrow arrow = player.getWorld().spawnArrow(
                            player.getEyeLocation(), player.getEyeLocation().getDirection(), 10.0f, 0.0f
                    );
                    arrow.setShooter(player);
                    arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);

                    arrowsSpawned++;
                } else {
                    cancel(); // Stop the task after all arrows have been spawned
                }
            }
        }.runTaskTimer(plugin, 0L, 1L); // Delay of 2 ticks (0.1 seconds)
        return true;
    }
}
