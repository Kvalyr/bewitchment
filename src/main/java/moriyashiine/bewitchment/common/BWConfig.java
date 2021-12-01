package moriyashiine.bewitchment.common;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Config(name = Bewitchment.MODID)
public class BWConfig implements ConfigData {
	public List<String> disabledPoppets = new ArrayList<>();
	public boolean enableCurses = false;    // Kv: Disabled. Was: true

	public int altarDistributionRadius = 24;

	public boolean generateSilver = false;      // Kv: Disabled. Was: true
	public boolean generateSalt = false;        // Kv: Disabled. Was: true

    // Kv: Added these options
    // public boolean generateBlocks = false;
	public boolean generateAnimals = true;      // Owls, Ravens, Snakes, Toads
	public boolean generateEnemies = false;     // Ghosts, vampires, werewolves, hellhounds
	public boolean generateLoot = false;        // Adds bewitchment seeds to grasses
    public boolean generateTrees = false;       // Juniper, Cypress, Elder
    // Kv

	public List<String> owlBiomeCategories = Arrays.asList(Biome.Category.TAIGA.getName(), Biome.Category.FOREST.getName());
	public int owlWeight = 2;  // Kv: Reduced spawn. Was: 10
	public int owlMinGroupCount = 1;
	public int owlMaxGroupCount = 2;

	public List<String> ravenBiomeCategories = Arrays.asList(Biome.Category.PLAINS.getName(), Biome.Category.FOREST.getName());
	public int ravenWeight = 2; // Kv: Reduced spawn. Was: 10
	public int ravenMinGroupCount = 1;
	public int ravenMaxGroupCount = 3;

	public List<String> snakeBiomeCategories = Arrays.asList(Biome.Category.PLAINS.getName(), Biome.Category.SAVANNA.getName(), Biome.Category.DESERT.getName());
	public int snakeWeight = 2; // Kv: Reduced spawn. Was: 6
	public int snakeMinGroupCount = 1;
	public int snakeMaxGroupCount = 2;

	public List<String> toadBiomeCategories = Arrays.asList(Biome.Category.JUNGLE.getName(), Biome.Category.SWAMP.getName());
	public int toadWeight = 3; // Kv: Reduced spawn. Was: 10
	public int toadMinGroupCount = 1;
	public int toadMaxGroupCount = 3;

	public int ghostWeight = 0; // Kv: Disabled spawn. Was: 10
	public int ghostMinGroupCount = 1;
	public int ghostMaxGroupCount = 1;

	public int vampireWeight = 0; // Kv: Disabled spawn. Was: 10
	public int vampireMinGroupCount = 1;
	public int vampireMaxGroupCount = 1;

	public int werewolfWeight = 0; // Kv: Disabled spawn. Was: 10
	public int werewolfMinGroupCount = 1;
	public int werewolfMaxGroupCount = 1;

	public int hellhoundWeight = 0; // Kv: Disabled spawn. Was: 6
	public int hellhoundMinGroupCount = 1;
	public int hellhoundMaxGroupCount = 1;
}
