package digitalpear.pears.common.blocks;

import digitalpear.pears.Pears;
import net.minecraft.src.BlockLeavesOak;

import java.util.Random;

public class BlockLeavesPear extends BlockLeavesOak {
    public BlockLeavesPear(int i) {
        super(i);
    }


    @Override
    public int idDropped(int i, Random random) {
        return random.nextInt(10) == 0 ? Pears.pear.itemID : Pears.pearSapling.blockID;
    }

}
