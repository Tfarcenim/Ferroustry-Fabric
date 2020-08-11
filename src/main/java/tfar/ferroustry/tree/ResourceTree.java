package tfar.ferroustry.tree;

import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.world.gen.feature.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ResourceTree extends LargeTreeSaplingGenerator {

  private final List<ConfiguredFeature<TreeFeatureConfig, ?>> configuredFeatureList1;
  private final List<ConfiguredFeature<TreeFeatureConfig, ?>> configuredFeatureList2;

  public ResourceTree(List<ConfiguredFeature<TreeFeatureConfig, ?>> configuredFeatureList1, List<ConfiguredFeature<TreeFeatureConfig, ?>> configuredFeatureList2) {
    this.configuredFeatureList1 = configuredFeatureList1;
    this.configuredFeatureList2 = configuredFeatureList2;
  }

  protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random rand, boolean bl) {
    return configuredFeatureList1.get(rand.nextInt(configuredFeatureList1.size()));
  }

  @Nullable
  protected ConfiguredFeature<TreeFeatureConfig, ?> createLargeTreeFeature(Random rand) {
    return configuredFeatureList2.get(rand.nextInt(configuredFeatureList2.size()));
  }
}
