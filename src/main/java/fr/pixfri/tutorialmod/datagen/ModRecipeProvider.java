package fr.pixfri.tutorialmod.datagen;

import fr.pixfri.tutorialmod.TutorialMod;
import fr.pixfri.tutorialmod.block.ModBlocks;
import fr.pixfri.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> RUBY_SMELTABLES = List.of(ModItems.RAW_RUBY,
            ModBlocks.RUBY_ORE, ModBlocks.DEEPSLATE_RUBY_ORE, ModBlocks.NETHER_RUBY_ORE, ModBlocks.END_STONE_RUBY_ORE);

    private static final List<ItemConvertible> PLANKS = List.of(Blocks.ACACIA_PLANKS, Blocks.BAMBOO_PLANKS,
            Blocks.BIRCH_PLANKS, Blocks.CHERRY_PLANKS, Blocks.CRIMSON_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.JUNGLE_PLANKS,
            Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.WARPED_PLANKS);

    private static final List<ItemConvertible> RUBY_TOOLS = List.of(ModItems.RUBY_PICKAXE, ModItems.RUBY_AXE,
            ModItems.RUBY_SWORD, ModItems.RUBY_SHOVEL, ModItems.RUBY_HOE);

    public static final List<ItemConvertible> RUBY_ARMORS = List.of(ModItems.RUBY_HELMET, ModItems.RUBY_CHESTPLATE,
            ModItems.RUBY_LEGGINGS, ModItems.RUBY_BOOTS);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY,
                0.7f, 200, "ruby");
        offerBlasting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY,
                0.7f, 100, "ruby");

        offerSmelting(exporter, PLANKS, RecipeCategory.MISC, ModItems.COAL_BRIQUETTE,
                0.2f, 100, "coal_briquette");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.RUBY,
                RecipeCategory.DECORATIONS, ModBlocks.RUBY_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.RAW_RUBY,
                RecipeCategory.DECORATIONS, ModBlocks.RAW_RUBY_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.METAL_DETECTOR, 1)
                .pattern("  S")
                .pattern(" I ")
                .pattern("IPI")
                .input('S', Items.STICK)
                .input('I', Items.IRON_INGOT)
                .input('P', Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), conditionsFromItem(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.METAL_DETECTOR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SOUND_BLOCK, 1)
                .pattern("WWW")
                .pattern("WRW")
                .pattern("WWW")
                .input('W', ItemTags.PLANKS)
                .input('R', ModItems.RUBY)
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SOUND_BLOCK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RUBY_STAFF, 1)
                .pattern("  R")
                .pattern(" S ")
                .pattern("S  ")
                .input('R', ModItems.RUBY)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.RUBY_STAFF)));

        offerStairsRecipe(exporter, ModBlocks.RUBY_STAIRS, ModItems.RUBY);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RUBY_SLAB, ModItems.RUBY);
        offerButtonRecipe(exporter, ModBlocks.RUBY_BUTTON, ModItems.RUBY);
        offerPressurePlateRecipe(exporter, ModBlocks.RUBY_PRESSURE_PLATE, ModItems.RUBY);
        offerFenceRecipe(exporter, ModBlocks.RUBY_FENCE, ModItems.RUBY);
        offerFenceGateRecipe(exporter, ModBlocks.RUBY_FENCE_GATE, ModItems.RUBY);
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RUBY_WALL, ModItems.RUBY);
        offerDoorRecipe(exporter, ModBlocks.RUBY_DOOR, ModItems.RUBY);
        offerTrapdoorRecipe(exporter, ModBlocks.RUBY_TRAPDOOR, ModItems.RUBY);

        offerToolsRecipes(exporter, RUBY_TOOLS, ModItems.RUBY);

        offerArmorRecipes(exporter, RUBY_ARMORS, ModItems.RUBY);
    }

    private CraftingRecipeJsonBuilder createButtonRecipe(ItemConvertible output, Ingredient input) {
        return ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, output, 4).input(input);
    }

    private CraftingRecipeJsonBuilder createPickaxeRecipe(ItemConvertible output, Ingredient input) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output, 1)
                .input('M', input)
                .input('S', Items.STICK)
                .pattern("MMM")
                .pattern(" S ")
                .pattern(" S ");
    }

    private CraftingRecipeJsonBuilder createAxeRecipe(ItemConvertible output, Ingredient input) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, output, 1)
                .input('M', input)
                .input('S', Items.STICK)
                .pattern("MM")
                .pattern("SM")
                .pattern("S ");
    }

    private CraftingRecipeJsonBuilder createSwordRecipe(ItemConvertible output, Ingredient input) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, output, 1)
                .input('M', input)
                .input('S', Items.STICK)
                .pattern("M")
                .pattern("M")
                .pattern("S");
    }

    private CraftingRecipeJsonBuilder createShovelRecipe(ItemConvertible output, Ingredient input) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output, 1)
                .input('M', input)
                .input('S', Items.STICK)
                .pattern("M")
                .pattern("S")
                .pattern("S");
    }

    private CraftingRecipeJsonBuilder createHoeRecipe(ItemConvertible output, Ingredient input) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output, 1)
                .input('M', input)
                .input('S', Items.STICK)
                .pattern("MM")
                .pattern("S ")
                .pattern("S ");
    }

    private CraftingRecipeJsonBuilder createHelmetRecipe(ItemConvertible output, Ingredient input) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, output, 1)
                .input('M', input)
                .pattern("MMM")
                .pattern("M M");
    }

    private CraftingRecipeJsonBuilder createChestplateRecipe(ItemConvertible output, Ingredient input) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, output, 1)
                .input('M', input)
                .pattern("M M")
                .pattern("MMM")
                .pattern("MMM");
    }

    private CraftingRecipeJsonBuilder createLeggingsRecipe(ItemConvertible output, Ingredient input) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, output, 1)
                .input('M', input)
                .pattern("MMM")
                .pattern("M M")
                .pattern("M M");
    }

    private CraftingRecipeJsonBuilder createBootsRecipe(ItemConvertible output, Ingredient input) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, output, 1)
                .input('M', input)
                .pattern("M M")
                .pattern("M M");
    }

    private void offerStairsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createStairsRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    private void offerButtonRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createButtonRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    private void offerFenceRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createFenceRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    private void offerFenceGateRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createFenceGateRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    private void offerDoorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createDoorRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    private void offerTrapdoorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createTrapdoorRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    private void offerToolsRecipes(Consumer<RecipeJsonProvider> exporter, List<ItemConvertible> tools,
                                   ItemConvertible material) {
        for (ItemConvertible tool : tools) {

            switch (tool) {
                case PickaxeItem pickaxe -> createPickaxeRecipe(pickaxe, Ingredient.ofItems(material))
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(exporter);

                case AxeItem axe -> createAxeRecipe(axe, Ingredient.ofItems(material))
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(exporter);

                case SwordItem sword -> createSwordRecipe(sword, Ingredient.ofItems(material))
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(exporter);

                case ShovelItem shovel -> createShovelRecipe(shovel, Ingredient.ofItems(material))
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(exporter);

                case HoeItem hoe -> createHoeRecipe(hoe, Ingredient.ofItems(material))
                        .criterion(hasItem(material), conditionsFromItem(material))
                        .offerTo(exporter);

                default -> TutorialMod.LOGGER.error("Item {} isn't a tool.", tool);
            }
        }
    }

    private void offerArmorRecipes(Consumer<RecipeJsonProvider> exporter, List<ItemConvertible> armorPieces,
                                   ItemConvertible material) {
        for (ItemConvertible armorPiece : armorPieces) {
            if (armorPiece instanceof ArmorItem armorItem) {

                switch (armorItem.getType()) {

                    case ArmorItem.Type.HELMET -> createHelmetRecipe(armorPiece, Ingredient.ofItems(material))
                            .criterion(hasItem(material), conditionsFromItem(material)).offerTo(exporter);

                    case ArmorItem.Type.CHESTPLATE -> createChestplateRecipe(armorPiece, Ingredient.ofItems(material))
                            .criterion(hasItem(material), conditionsFromItem(material)).offerTo(exporter);

                    case ArmorItem.Type.LEGGINGS -> createLeggingsRecipe(armorPiece, Ingredient.ofItems(material))
                            .criterion(hasItem(material), conditionsFromItem(material)).offerTo(exporter);

                    case ArmorItem.Type.BOOTS -> createBootsRecipe(armorPiece, Ingredient.ofItems(material))
                            .criterion(hasItem(material), conditionsFromItem(material)).offerTo(exporter);

                    default -> TutorialMod.LOGGER.error("Item {} isn't a valid armor item type.", armorItem);
                }
            } else {
                TutorialMod.LOGGER.error("Item {} isn't a valid armor item.", armorPiece);
            }
        }
    }
}
