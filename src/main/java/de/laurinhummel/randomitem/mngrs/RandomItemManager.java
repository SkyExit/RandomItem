package de.laurinhummel.randomitem.mngrs;

import de.laurinhummel.randomitem.Main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomItemManager {

    Random random = new Random();
    private static final List<Material> items = new ArrayList<>();

    public ItemStack getRandomItem() {
        return new ItemStack(items.get(random.nextInt(items.size())));
    }

    public final void createMaterialList() {
        for (Material material : Material.values()) {
            if(material.isItem()) {
                items.add(material);
            }
        }
    }
}
