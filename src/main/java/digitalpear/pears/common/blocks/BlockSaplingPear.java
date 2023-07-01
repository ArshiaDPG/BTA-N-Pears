package digitalpear.pears.common.blocks;

import digitalpear.pears.Pears;
import digitalpear.pears.common.features.WorldGenTreeShapePear;
import net.minecraft.src.*;

import java.util.Random;

public class BlockSaplingPear extends BlockSaplingBase {
    public BlockSaplingPear(int i) {
        super(i);
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        if (!world.isMultiplayerAndNotHost) {
            super.updateTick(world, i, j, k, random);
            int growthRate = 30;
            if (world.getBlockLightValue(i, j + 1, k) >= 9 && random.nextInt(growthRate) == 0) {
                int l = world.getBlockMetadata(i, j, k);
                if ((l & 8) == 0) {
                    world.setBlockMetadataWithNotify(i, j, k, l | 8);
                } else {
                    this.growTree(world, i, j, k, random);
                }
            }

        }
    }

    @Override
    public void growTree(World world, int i, int j, int k, Random random) {
        WorldGenTreeShapePear treeBig = new WorldGenTreeShapePear(Pears.pearLeaves.blockID, Pears.pearLog.blockID, Pears.enchantedPearLog.blockID);
        WorldGenTreeShapeDefault treeSmall = new WorldGenTreeShapeDefault(Pears.pearLeaves.blockID, Pears.pearLog.blockID, 4);
        world.setBlock(i, j, k, 0);
        if (!((WorldGenerator)treeBig).generate(world, random, i, j, k) && !((WorldGenerator)treeSmall).generate(world, random, i, j, k)) {
            world.setBlock(i, j, k, this.blockID);
        }
    }
}
