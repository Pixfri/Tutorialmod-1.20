package fr.pixfri.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;

public class ModItemGroupModifiers {
    protected static void addItemsToToolsItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModItems.RUBY_SHOVEL);
        entries.add(ModItems.RUBY_PICKAXE);
        entries.add(ModItems.RUBY_AXE);
        entries.add(ModItems.RUBY_HOE);

        entries.add(ModItems.METAL_DETECTOR);
    }

    protected static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModItems.RUBY_SWORD);
        entries.add(ModItems.RUBY_HELMET);
        entries.add(ModItems.RUBY_CHESTPLATE);
        entries.add(ModItems.RUBY_LEGGINGS);
        entries.add(ModItems.RUBY_BOOTS);
    }

    protected static void addItemsToIngredientsItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModItems.RAW_RUBY);
        entries.add(ModItems.RUBY);
        entries.add(ModItems.COAL_BRIQUETTE);

        entries.add(ModItems.TOMATO);
    }

    protected static void addItemsToFoodsAndDrinksItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModItems.TOMATO);
    }

    protected static void addItemsToNaturalItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModItems.TOMATO_SEEDS);
    }
}
