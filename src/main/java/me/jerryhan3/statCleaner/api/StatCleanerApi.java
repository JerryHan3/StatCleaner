package me.jerryhan3.statCleaner.api;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public interface StatCleanerApi {
    /**
     * Reset a single player's stats. <br>
     * The behavior is controlled by the config of StatCleaner.
     * @param player a Bukkit Player object.
     */
    void resetPlayer(Player player);

    /**
     * Reset multiple players' stats. <br>
     * The behavior is controlled by the config of StatCleaner.
     * @param players a list of Bukkit Player objects.
     */
    void resetPlayers(List<Player> players);

    /**
     * Reset players' stats if they match the entity selector provided.
     * @param sender Command sender, which will work together with entity selector to parse targets.
     * @param selector a string that contains a vanilla entity selector, like <code>@a</code>.
     * @throws IllegalArgumentException if the selector is malformed in any way or a parameter is null. Check javadoc for <code>org.bukkit.Bukkit.selectEntities</code> for more detail.
     */
    void resetBySelector(CommandSender sender, String selector) throws IllegalArgumentException;

    /**
     * Check the status of a certain stat category, and determine if the player's certain stat will be reset.
     * @param category Choose from <code>HEALTH</code>, <code>HUNGER</code>, <code>EFFECTS</code>, <code>ATTRIBUTES</code>, and <code>FLYING</code>.
     * @return Whether the category is enabled.
     */
    boolean isCategoryEnabled(ResetCategory category);

    /**
     * Reset a single player's certain category of stat.
     * @param player Target Bukkit Player object.
     * @param category Target stat category to reset.
     */
    void resetPlayerByCategory(Player player, ResetCategory category);

    /**
     * Get Statreset plugin's version.
     */
    String getVersion();
}
