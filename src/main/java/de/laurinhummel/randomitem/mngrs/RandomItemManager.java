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

    public ItemStack getRandomItem() {
        return new ItemStack(Main.items.get(random.nextInt(Main.items.size())));
    }
}
