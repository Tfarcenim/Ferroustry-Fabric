package tfar.ferroustry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.block.*;
import net.minecraft.item.Items;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
public class Scripts {

  public static final String append = "C:\\Users\\xluser\\Documents\\MinecraftMods\\mods\\ResourceTrees\\src\\main\\resources\\assets\\ferroustry\\";

  public static final String appenddata = "C:\\Users\\xluser\\Documents\\MinecraftMods\\mods\\ResourceTrees\\src\\main\\resources\\data\\ferroustry\\";

  public static Gson g = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

  public static void scripts() {

    for (OreType ore : OreType.values()) {
      //blockstate jsons
      writeToFile(append + "blockstates\\" + ore.toString() + "_log.json", ore.getLogState(false, false));
      writeToFile(append + "blockstates\\stripped_" + ore.toString() + "_log.json", ore.getLogState(true, false));
      writeToFile(append + "blockstates\\" + ore.toString() + "_wood.json", ore.getLogState(false, true));
      writeToFile(append + "blockstates\\stripped_" + ore.toString() + "_wood.json", ore.getLogState(true, true));
      for (OreType.BasicStateType type : OreType.BasicStateType.values()) {
        writeToFile(append + "blockstates\\" + ore.toString() + "_" + type.toString() + ".json", ore.getBasicState(type));
      }
      writeToFile(append + "blockstates\\potted_" + ore.toString() + "_sapling.json", ore.getPottedState());

      writeToFile(append + "blockstates\\" + ore.toString() + "_slab.json", ore.getSlabState());
      writeToFile(append + "blockstates\\" + ore.toString() + "_stairs.json", ore.getStairsState());
      writeToFile(append + "blockstates\\" + ore.toString() + "_fence.json", ore.getFenceState());

      //blockmodel jsons
      writeToFile(append + "models\\block\\" + ore.toString() + "_leaves.json", ore.getLeavesModel());
      writeToFile(append + "models\\block\\" + ore.toString() + "_planks.json", ore.getPlanksModel());
      for (OreType.FenceModelType type : OreType.FenceModelType.values()) {
        writeToFile(append + "models\\block\\" + ore.toString() + "_fence_" + type.toString() + ".json", ore.getFenceModel(type));
      }
      writeToFile(append + "models\\block\\" + ore.toString() + "_log.json", ore.getLogModel(false, false));
      writeToFile(append + "models\\block\\stripped_" + ore.toString() + "_log.json", ore.getLogModel(true, false));
      writeToFile(append + "models\\block\\" + ore.toString() + "_wood.json", ore.getLogModel(false, true));
      writeToFile(append + "models\\block\\stripped_" + ore.toString() + "_wood.json", ore.getLogModel(true, true));
      writeToFile(append + "models\\block\\" + ore.toString() + "_sapling.json", ore.getSaplingModel());
      writeToFile(append + "models\\block\\" + ore.toString() + "_slab.json", ore.getSlabModel(false));
      writeToFile(append + "models\\block\\" + ore.toString() + "_slab_top.json", ore.getSlabModel(true));
      writeToFile(append + "models\\block\\" + ore.toString() + "_stairs.json", ore.getStairModel(OreType.StairsModelType.stairs));
      writeToFile(append + "models\\block\\" + ore.toString() + "_stairs_outer.json", ore.getStairModel(OreType.StairsModelType.outer_stairs));
      writeToFile(append + "models\\block\\" + ore.toString() + "_stairs_inner.json", ore.getStairModel(OreType.StairsModelType.inner_stairs));
      writeToFile(append + "models\\block\\potted_" + ore.toString() + "_sapling.json", ore.getPottedModel());

      //itemmodel jsons
      writeToFile(append + "models\\item\\" + ore.toString() + "_log.json", ore.getBasicItemmodel(OreType.BasicItemModelType.log));
      writeToFile(append + "models\\item\\stripped_" + ore.toString() + "_log.json", ore.getBasicItemmodel(OreType.BasicItemModelType.stripped_log));
      writeToFile(append + "models\\item\\" + ore.toString() + "_wood.json", ore.getBasicItemmodel(OreType.BasicItemModelType.wood));
      writeToFile(append + "models\\item\\stripped_" + ore.toString() + "_wood.json", ore.getBasicItemmodel(OreType.BasicItemModelType.stripped_wood));
      writeToFile(append + "models\\item\\" + ore.toString() + "_planks.json", ore.getBasicItemmodel(OreType.BasicItemModelType.planks));
      writeToFile(append + "models\\item\\" + ore.toString() + "_leaves.json", ore.getBasicItemmodel(OreType.BasicItemModelType.leaves));
      writeToFile(append + "models\\item\\" + ore.toString() + "_stairs.json", ore.getBasicItemmodel(OreType.BasicItemModelType.stairs));
      writeToFile(append + "models\\item\\" + ore.toString() + "_slab.json", ore.getBasicItemmodel(OreType.BasicItemModelType.slab));
      writeToFile(append + "models\\item\\" + ore.toString() + "_sapling.json", ore.getSaplingItemmodel());
      writeToFile(append + "models\\item\\" + ore.toString() + "_fence.json", ore.getFenceItemmodel());
      writeToFile(appenddata + "loot_tables\\blocks\\" + ore.toString() + "_leaves.json", ore.getLootTableLeaves());
      writeToFile(appenddata + "loot_tables\\blocks\\potted_" + ore.toString() + "_sapling.json", ore.getPottedLootTable());

      //recipes
      writeToFile(appenddata + "recipes\\crafting\\" + ore.toString() + "_planks.json", ore.getPlanksRecipe());
      writeToFile(appenddata + "recipes\\crafting\\" + ore.toString() + "_fence.json", ore.getFenceRecipe());
      writeToFile(appenddata + "recipes\\crafting\\" + ore.toString() + "_slab.json", ore.getSlabRecipe());
      writeToFile(appenddata + "recipes\\crafting\\" + ore.toString() + "_stairs.json", ore.getStairsRecipe());
      writeToFile(appenddata + "recipes\\crafting\\" + ore.toString() + "_wood.json", ore.getWoodRecipe());

      writeToFile(appenddata + "recipes\\sapling\\" + ore.toString() + "_sapling.json", ore.getSaplingRecipe());
      writeToFile(appenddata + "recipes\\sapling2\\" + ore.toString() + "_sapling.json", ore.getSapling2Recipe());

      writeToFile(appenddata + "recipes\\smelting\\" + ore.toString() + "_log.json", ore.getBlastingRecipe());

      writeToFile(appenddata + "recipes\\recycling\\" + ore.toString() + ".json", ore.getRecyclingRecipe());


      //log tags
      //writeToFile(appenddata + "tags\\blocks\\" + ore.toString() + "_logs.json", ore.getLogTags());
      //writeToFile(appenddata + "tags\\items\\" + ore.toString() + "_logs.json", ore.getLogTags());
    }
    //loottables
    for (Block block : Ferroustry.MOD_BLOCKS)
      if (!(block instanceof LeavesBlock) && !(block instanceof FlowerPotBlock))
      //writeToFile(appenddata + "loot_tables\\blocks\\"+block.getRegistryName().getPath()+".json", OreType.getLootTable(block));
    lang();
  }

  private static void lang() {


    JsonObject lang = new JsonObject();
    for (Block block : Ferroustry.MOD_BLOCKS) {
      String[] translations = block.getDescriptionId().split("\\.");

      List<String> a1 = new ArrayList<>(Arrays.asList(translations));
      a1.remove(0);
      a1.remove(0);

      translations = a1.toArray(new String[0]);
      String translation = Arrays.toString(translations);
      translations = translation.split("_");

      for (int i = 0; i < translations.length; i++) {
        translations[i] = translations[i].substring(0, 1).toUpperCase() + translations[i].substring(1);
      }

      translation = Arrays.toString(translations);
      translation = translation.replace(",", "");
      translation = translation.replace("[", "");
      translation = translation.replace("]", "");
      translation = translation.substring(0, 1).toUpperCase() + translation.substring(1);

      lang.addProperty(block.getDescriptionId(), translation);

    }
    File file = new File("C:\\Users\\xluser\\Documents\\MinecraftMods\\mods\\ResourceTrees\\src\\main\\resources\\assets\\ferroustry\\lang\\en_us.json");

    try {

      FileWriter writer1 = new FileWriter(file);
      writer1.write(g.toJson(lang));
      writer1.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void loottable(Block block) {
    if (block instanceof LeavesBlock) return;
    BufferedInputStream in = new BufferedInputStream(Scripts.class.getResourceAsStream("/coal_log.json"));
    //hardcoded into the mod, used for oredict, jsons and modids
    String s = null;
    try {
      s = IOUtils.toString(in, Charset.defaultCharset());
      //System.out.println(s);
    } catch (IOException e) {
      e.printStackTrace();
    }

    String s1 = s.replace("coal_log", block.getRegistryName().getPath());
    File file = new File("C:\\Users\\xluser\\Documents\\MinecraftMods\\mods\\ResourceTrees\\src\\main\\resources\\data\\ferroustry\\loot_tables\\blocks\\" + block.getRegistryName().getPath() + ".json");

    try {

      FileWriter writer1 = new FileWriter(file);
      writer1.write(s1);
      writer1.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void writeToFile(String filename, String file) {
    File file1 = new File(filename);
    try {
      FileWriter writer = new FileWriter(file1);
      writer.write(file);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Mod.EventBusSubscriber
  public static class JsonCrap {

    @SubscribeEvent
    public static void script(PlayerInteractEvent.RightClickItem e) {
      if (e.getItemStack().getItem() == Items.DEBUG_STICK) {
        scripts();
      }
    }
  }
*///