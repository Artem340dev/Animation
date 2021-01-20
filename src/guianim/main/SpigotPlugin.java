package guianim.main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SpigotPlugin extends JavaPlugin implements Listener {
    private HashMap<Player, Integer> tasks = new HashMap<>();
    private Utils utils = new Utils();

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Inventory i = Bukkit.createInventory(null, 9, "Анимация");
            for (int j = 0; j < 9; j++) {
                i.setItem(j, utils.item("&7*", null, Material.CONCRETE_POWDER, 0));
            }
            p.openInventory(i);
            int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
                for (int j = 0; j < 9; j++) {
                    ItemStack it = i.getItem(j);
                    it.setDurability((short) utils.random(1, 15));
                    i.setItem(j, it);
                }
            }, 20L, 20L);
            tasks.put(p, id);
            return true;
        } else return false;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().getName().equals("Анимация")) {
            Bukkit.getScheduler().cancelTask(tasks.get(e.getPlayer()));
            tasks.remove(e.getPlayer());
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().getName().equals("Анимация")) {
            e.setCancelled(true);
        }
    }
}