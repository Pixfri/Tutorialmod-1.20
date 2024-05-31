package fr.pixfri.tutorialmod.item;

import fr.pixfri.tutorialmod.TutorialMod;
import fr.pixfri.tutorialmod.block.ModBlocks;
import fr.pixfri.tutorialmod.item.custom.MetalDetectorItem;
import fr.pixfri.tutorialmod.item.custom.ModArmorItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item RUBY = registerItem("ruby", new Item(new FabricItemSettings()));

    public static final Item RAW_RUBY = registerItem("raw_ruby", new Item(new FabricItemSettings()));


    public static final Item METAL_DETECTOR = registerItem("metal_detector", new MetalDetectorItem(
            new FabricItemSettings().maxDamage(64)));


    public static final Item TOMATO = registerItem("tomato", new Item(
            new FabricItemSettings().food(ModFoodComponents.TOMATO)));

    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds",
            new AliasedBlockItem(ModBlocks.TOMATO_CROP, new FabricItemSettings()));


    public static final Item COAL_BRIQUETTE = registerFuelItem("coal_briquette", new Item(new FabricItemSettings()),
            200);

    public static final Item RUBY_STAFF = registerItem("ruby_staff",
            new Item(new FabricItemSettings().maxCount(1)));


    public static final Item RUBY_PICKAXE = registerItem("ruby_pickaxe",
            new PickaxeItem(ModToolMaterial.RUBY, 2, 2f, new FabricItemSettings()));

    public static final Item RUBY_AXE = registerItem("ruby_axe",
            new AxeItem(ModToolMaterial.RUBY, 6, 2f, new FabricItemSettings()));

    public static final Item RUBY_SWORD = registerItem("ruby_sword",
            new SwordItem(ModToolMaterial.RUBY, 5, 3f, new FabricItemSettings()));

    public static final Item RUBY_SHOVEL = registerItem("ruby_shovel",
            new ShovelItem(ModToolMaterial.RUBY, 2, 2f, new FabricItemSettings()));

    public static final Item RUBY_HOE = registerItem("ruby_hoe",
            new HoeItem(ModToolMaterial.RUBY, 1, 1.5f, new FabricItemSettings()));

    // Ruby armor set
    // Only one item needs to use the ModArmorItem in the whole set since it checks for every other armor item.
    public static final Item RUBY_HELMET = registerItem("ruby_helmet",
            new ModArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.HELMET, new FabricItemSettings()));

    public static final Item RUBY_CHESTPLATE = registerItem("ruby_chestplate",
            new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));

    public static final Item RUBY_LEGGINGS = registerItem("ruby_leggings",
            new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));

    public static final Item RUBY_BOOTS = registerItem("ruby_boots",
            new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(TutorialMod.MOD_ID, name), item);
    }

    private static Item registerFuelItem(String name, Item item, int value) {
        Item fuelItem = registerItem(name, item);
        FuelRegistry.INSTANCE.add(fuelItem, value);
        return fuelItem;
    }

    public static void registerItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);

        TutorialMod.LOGGER.info("Modifying vanilla item groups for " + TutorialMod.MOD_ID);
        modifyItemGroup(ItemGroups.TOOLS, ModItemGroupModifiers::addItemsToToolsItemGroup);
        modifyItemGroup(ItemGroups.COMBAT, ModItemGroupModifiers::addItemsToCombatItemGroup);
        modifyItemGroup(ItemGroups.INGREDIENTS, ModItemGroupModifiers::addItemsToIngredientsItemGroup);
        modifyItemGroup(ItemGroups.FOOD_AND_DRINK, ModItemGroupModifiers::addItemsToFoodsAndDrinksItemGroup);
        modifyItemGroup(ItemGroups.NATURAL, ModItemGroupModifiers::addItemsToNaturalItemGroup);
    }

    private static void modifyItemGroup(RegistryKey<ItemGroup> group, ItemGroupEvents.ModifyEntries modifyEntries) {
        ItemGroupEvents.modifyEntriesEvent(group).register(modifyEntries);
    }
}
