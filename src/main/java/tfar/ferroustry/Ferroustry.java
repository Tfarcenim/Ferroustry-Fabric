package tfar.ferroustry;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import tfar.ferroustry.block.ResourceSaplingBlock;
import tfar.ferroustry.block.ResourceStairsBlock;
import tfar.ferroustry.tree.ResourceTree;
import net.minecraft.block.*;

import net.minecraft.item.*;

import net.minecraft.world.gen.feature.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// The value here should match an entry in the META-INF/mods.toml file
public class Ferroustry implements ModInitializer, ClientModInitializer {
  // Directly reference a log4j logger.

  public static final String MODID = "ferroustry";

  public static final Set<Block> MOD_BLOCKS = new HashSet<>();

  /*private void setup(final FMLCommonSetupEvent event) {
    Map<Block, Block> map = new HashMap<>(AxeItem.STRIPABLES);
    RegistryEvents.MOD_BLOCKS.stream().filter(block -> {
      String name = block.getRegistryName().getPath();
      return (name.endsWith("log") || name.endsWith("wood")) && !name.startsWith("stripped");
    })
            .forEach(block -> map.put(block, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(MODID, "stripped_" +
            block.getRegistryName().getPath()))));
    AxeItem.STRIPABLES = map;
  }*/

  @Override
  public void onInitialize() {
    Block.Settings log = Block.Settings.of(Material.WOOD, MaterialColor.WOOD).strength(2, 4).sounds(BlockSoundGroup.WOOD);
    Block.Settings leaves = Block.Settings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque();
    Block.Settings sapling = Block.Settings.of(Material.LEAVES).noCollision().ticksRandomly().strength(0).sounds(BlockSoundGroup.GRASS);
    Block.Settings plank = Block.Settings.of(Material.WOOD, MaterialColor.CLAY).strength(2, 6).sounds(BlockSoundGroup.WOOD);
    for (OreType material : OreType.values()) {
      PillarBlock logBlock = log(MaterialColor.CLEAR, MaterialColor.CLEAR);
      LeavesBlock leavesBlock = new LeavesBlock(leaves);
      registerBlock( loc(material + "_log"), logBlock);
      registerBlock( loc(material + "_leaves"), leavesBlock);
      TreeFeatureConfig treeFeatureConfig = new TreeFeatureConfig.Builder(
              new SimpleBlockStateProvider(logBlock.getDefaultState()),
              new SimpleBlockStateProvider(leavesBlock.getDefaultState()),
              new BlobFoliagePlacer(2, 0, 0, 0, 3),
              new StraightTrunkPlacer(4, 2, 0), new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build();

      ResourceSaplingBlock resourceSaplingBlock = new ResourceSaplingBlock(new ResourceTree(treeFeatureConfig), sapling);
      registerBlock( loc(material + "_sapling"),
              resourceSaplingBlock);
      //FlowerPotBlock flowerPotBlock = new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT,() -> resourceSaplingBlock,Block.Settings.of(Material.DECORATION).strength(0).noOcclusion());
      //((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(resourceSaplingBlock.getRegistryName(),() -> flowerPotBlock);
      //register(flowerPotBlock,"potted_"+material+"_sapling",event.getRegistry());
      Block planks = new Block(plank);
      registerBlock( loc(material + "_planks"), planks);
      registerBlock( loc(material + "_slab"), new SlabBlock(Block.Settings.copy(planks)));
      registerBlock( loc(material + "_stairs"), new ResourceStairsBlock(planks.getDefaultState(), Block.Settings.copy(planks)));
      registerBlock( loc(material + "_fence"), new FenceBlock(plank));
      registerBlock( loc(material + "_wood"), new PillarBlock(log));
      registerBlock( loc("stripped_" + material + "_log"), log(MaterialColor.WOOD, MaterialColor.BLACK_TERRACOTTA));
      registerBlock( loc("stripped_" + material + "_wood"), new PillarBlock(log));
    }

      Item.Settings settings = new Item.Settings().group(ItemGroup.BUILDING_BLOCKS);
      for (Block block : MOD_BLOCKS) {
        if (!(block instanceof FlowerPotBlock))
          Registry.register(Registry.ITEM,Registry.BLOCK.getId(block), new BlockItem(block, settings));
      }
      Item.Settings settings1 = new Item.Settings().group(ItemGroup.MATERIALS);
      Registry.register(Registry.ITEM,loc("aluminum_ingot"), new Item(settings1));
      Registry.register(Registry.ITEM,loc("copper_ingot"), new Item(settings1));
      Registry.register(Registry.ITEM,loc( "silver_ingot"),new Item(settings1));
      Registry.register(Registry.ITEM,loc("lead_ingot"), new Item(settings1));
      Registry.register(Registry.ITEM,loc("tin_ingot"), new Item(settings1));
      Registry.register(Registry.ITEM,loc("bismuth_ingot"), new Item(settings1));
  }

  public static Identifier loc(String s){
    return new Identifier(MODID,s);
  }

  private static void registerBlock(Identifier id, Block block) {
    Registry.register(Registry.BLOCK,id,block);
    MOD_BLOCKS.add(block);
  }

  @Override
  public void onInitializeClient() {
    MOD_BLOCKS.stream()
            .filter(block -> block instanceof SaplingBlock || block instanceof LeavesBlock || block instanceof FlowerPotBlock)
            .forEach(block -> {
              RenderLayer renderType = block instanceof LeavesBlock ? RenderLayer.getCutoutMipped() : RenderLayer.getCutout();
              BlockRenderLayerMap.INSTANCE.putBlock(block, renderType);
            });
  }
  private static PillarBlock log(MaterialColor p_235430_0_, MaterialColor p_235430_1_) {
    return new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, (p_lambda$func_235430_a_$36_2_) ->
            p_lambda$func_235430_a_$36_2_.get(PillarBlock.AXIS) == Direction.Axis.Y ? p_235430_0_ : p_235430_1_)
            .strength(2.0F).sounds(BlockSoundGroup.WOOD));
  }
}
