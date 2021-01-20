package guianim.main;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Utils {
    public int random(int min, int max) {
        int range = max - min;
        return (int)(Math.random() * range) + min;
    }

    public ItemStack item(String name, List<String> lore, Material mat, int data) {
        ItemStack stack = new ItemStack(mat, 1, (short) data);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name.replace('&', '\u00a7'));
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }
}