package tfar.ferroustry.tree;

import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class ResourceTree extends LargeTreeSaplingGenerator {

  private final TreeFeatureConfig baseTreeFeatureConfig;

  public ResourceTree(TreeFeatureConfig treeFeatureConfigFeature) {
    this.baseTreeFeatureConfig = treeFeatureConfigFeature;
  }

  @Nullable
  protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random p_225546_1_, boolean p_225546_2_) {
    return Feature.TREE.configure(baseTreeFeatureConfig);
  }

  @Nullable
  protected ConfiguredFeature<TreeFeatureConfig, ?> createLargeTreeFeature(Random p_225547_1_) {
    return Feature.TREE.configure(p_225547_1_.nextBoolean() ? DefaultBiomeFeatures.MEGA_SPRUCE_TREE_CONFIG : DefaultBiomeFeatures.MEGA_PINE_TREE_CONFIG);
  }
}
