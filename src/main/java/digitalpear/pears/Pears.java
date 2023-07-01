package digitalpear.pears;

import digitalpear.pears.common.blocks.BlockEnchantedLog;
import digitalpear.pears.common.blocks.BlockLeavesPear;
import digitalpear.pears.common.blocks.BlockSaplingPear;
import net.fabricmc.api.ModInitializer;
import net.minecraft.src.*;
import net.minecraft.src.helper.DyeColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockHelper;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.RecipeHelper;


public class Pears implements ModInitializer {
    public static final String MOD_ID = "pears";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Item pear = ItemHelper.createItem(MOD_ID, new ItemFood(142, 4, false), "food.pear", "pear.png");
    public static final Block pearLog = BlockHelper.createBlock(MOD_ID, new BlockLog(900), "log.pear", "pear_log_top.png", "pear_log.png", Block.soundWoodFootstep, 2.0f, 2.0f, 0.0f);
    public static final Block enchantedPearLog = BlockHelper.createBlock(MOD_ID, new BlockEnchantedLog(901), "log.enchanted_pear", "pear_log_top.png", "enchanted_pear_log.png", Block.soundWoodFootstep, 2.0f, 2.0f, 0.5f);

    public static final Block pearLeaves = BlockHelper.createBlock(MOD_ID, new BlockLeavesPear(902), "leaves.pear", "pear_leaves.png", Block.soundGrassFootstep, 2.0f, 2.0f, 0.0f);

    public static final Block pearSapling = BlockHelper.createBlock(MOD_ID, new BlockSaplingPear(903), "sapling.pear", "pear_sapling.png", Block.soundGrassFootstep, 0.0f, 0.0f, 0.0f);

    /*
        Known issues:
        -Pear logs cannot be crafted into yellow planks, producing white planks instead.
        -Logs can only be smelted into coal, rather than charcoal.
        -World generation not yet implemented. (I'm not sure how or where to add it to the world)
     */

    @Override
    public void onInitialize() {

        RecipeHelper.Crafting.createShapelessRecipe(new ItemStack(Block.planksOakPainted, 4, DyeColor.dyeYellow).getItem(), 1, new Object[]{new ItemStack(pearLog)});
        RecipeHelper.Crafting.createShapelessRecipe(new ItemStack(Block.planksOakPainted, 4, DyeColor.dyeYellow).getItem(), 1, new Object[]{new ItemStack(enchantedPearLog)});


        RecipeHelper.Smelting.createRecipe(Item.coal, pearLog);
        RecipeHelper.Smelting.createRecipe(Item.nethercoal, enchantedPearLog);

        LOGGER.info("Pears initialized.");
    }
}
