package digitalpear.pears.common.blocks;

import net.minecraft.src.BlockLog;
import net.minecraft.src.Item;
import net.minecraft.src.World;

import java.util.Random;

public class BlockEnchantedLog extends BlockLog {
    public BlockEnchantedLog(int i) {
        super(i);
    }
    @Override
    public int idDropped(int i, Random random) {
        return Item.dustGlowstone.itemID;
    }

    @Override
    public int quantityDropped(int metadata, Random random) {
        return random.nextInt(2) + 2;
    }

    @Override
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (random.nextInt() < 4){
            spawnParticles(world, i, j, k);
        }

        super.randomDisplayTick(world, i, j, k, random);
    }

    private void spawnParticles(World world, int i, int j, int k) {
        Random random = world.rand;
        double d = 0.0625D;

        for(int l = 0; l < 6; ++l) {
            double d1 = (float)i + random.nextFloat();
            double d2 = (float)j + random.nextFloat();
            double d3 = (float)k + random.nextFloat();
            if (l == 0 && !world.isBlockOpaqueCube(i, j + 1, k)) {
                d2 = (double)(j + 1) + d;
            }

            if (l == 1 && !world.isBlockOpaqueCube(i, j - 1, k)) {
                d2 = (double)(j + 0) - d;
            }

            if (l == 2 && !world.isBlockOpaqueCube(i, j, k + 1)) {
                d3 = (double)(k + 1) + d;
            }

            if (l == 3 && !world.isBlockOpaqueCube(i, j, k - 1)) {
                d3 = (double)(k + 0) - d;
            }

            if (l == 4 && !world.isBlockOpaqueCube(i + 1, j, k)) {
                d1 = (double)(i + 1) + d;
            }

            if (l == 5 && !world.isBlockOpaqueCube(i - 1, j, k)) {
                d1 = (double)(i + 0) - d;
            }

            if (d1 < (double)i || d1 > (double)(i + 1) || d2 < 0.0D || d2 > (double)(j + 1) || d3 < (double)k || d3 > (double)(k + 1)) {
                world.spawnParticle("reddust", d1, d2, d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
