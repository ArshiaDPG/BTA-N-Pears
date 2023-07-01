package digitalpear.pears.common.features;

import net.minecraft.shared.Minecraft;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.helper.Direction;

import java.util.Random;

public class WorldGenTreeShapePear extends WorldGenerator {
    protected int leavesID;
    protected int logID;
    protected int enchantedLogID;

    public WorldGenTreeShapePear(int leavesID, int logID, int enchantedLogID){
        this.leavesID = leavesID;
        this.logID = logID;
        this.enchantedLogID = enchantedLogID;
    }
    public WorldGenTreeShapePear(int leavesID, int logID){
        this.leavesID = leavesID;
        this.logID = logID;
        this.enchantedLogID = logID;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        int height = random.nextInt(16) + 8;
        int widthLeaves = random.nextInt(1) + 1;
        int baseY = y + 1;

        if (baseY + height + 3 >= Minecraft.WORLD_HEIGHT_BLOCKS){
            return false;
        }
        for (int currentHeight = baseY; currentHeight < baseY + height; currentHeight++){
            if (!world.isAirBlock(x, currentHeight, z)){
                return false;
            }
        }

        int stumpwidth = 5;

        for (int i = -stumpwidth; i <= stumpwidth; i++){
            for (int j = -stumpwidth; j <= stumpwidth; j++){
                for (int k = 0; k < random.nextInt(4) + 3; k++) {
                    if (random.nextInt(10) < 9 && world.isBlockOpaqueCube(x + i, y + k, z + j)) {
                        placeLog(world, random, x + i, baseY + k, z + j);
                    }
                }
            }
        }

        for (int currentHeight = y; currentHeight < y + height; currentHeight++){
            placeLog(world, random, x, currentHeight, z);

            if (random.nextInt(20) < 10 && currentHeight > y + 4){
                generateBranch(world, random, widthLeaves - 1, x, currentHeight, z);
            }
        }
        placePatch(world, widthLeaves, 1, x, y + height, z);

        return true;
    }

    public void generateBranch(World world, Random random, int widthLeaves, int x, int y, int z){
        Direction direction = Direction.getDirection(random.nextInt(4));
        int currentX = x;
        int currentY = y;
        int currentZ = z;
        for (int i = 1; i < random.nextInt(5) + 2; i++){
            if (direction == Direction.NORTH){
                currentZ++;
            }
            else if (direction == Direction.SOUTH){
                currentZ--;
            }
            else if (direction == Direction.WEST){
                currentX--;
            }
            else if (direction == Direction.EAST){
                currentX++;
            }

            if (random.nextBoolean()){
                currentY++;
            }
            placeLog(world, random, currentX, currentY, currentZ);
        }
        placePatch(world, widthLeaves, 1, currentX, currentY, currentZ);
    }

    public void placePatch(World world, int leavesWidth, int leavesHeight, int x, int y, int z){
        placeLeaves(world, leavesWidth, leavesHeight, x, y, z);
        placeLeaves(world, leavesWidth + 2, leavesHeight - 1, x, y, z);
    }
    public void placePatch(World world, int leavesWidth, int x, int y, int z){
        placeLeaves(world, leavesWidth, leavesWidth, x, y, z);
        placeLeaves(world, leavesWidth + 2, leavesWidth - 1, x, y, z);
    }

    public void placeLeaves(World world, int leavesWidth, int height, int x, int y, int z){
        for (int i = -leavesWidth; i <= leavesWidth; i++){
            for (int j = -height; j <= height; j++){
                for (int k = -leavesWidth; k <= leavesWidth; k++) {
                    if (world.isAirBlock(x + i, y + j, z + k)){
                        world.setBlock(x + i, y + j, z + k, this.leavesID);
                    }
                }
            }
        }
    }

    public void placeLog(World world, Random random, int x, int y, int z){
        if (world.isAirBlock(x, y, z)){
            world.setBlock(x, y, z, random.nextInt(5) < 2 ? this.enchantedLogID : this.logID);
        }
    }
}
