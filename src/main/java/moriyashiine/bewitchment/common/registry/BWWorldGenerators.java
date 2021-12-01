package moriyashiine.bewitchment.common.registry;

import moriyashiine.bewitchment.api.BewitchmentAPI;
import moriyashiine.bewitchment.common.Bewitchment;
import moriyashiine.bewitchment.common.entity.living.GhostEntity;
import moriyashiine.bewitchment.mixin.OrePlacedFeaturesAccessor;
import moriyashiine.bewitchment.mixin.SimpleBlockStateProviderAccessor;
import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.FeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.foliage.MegaPineFoliagePlacer;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.function.Predicate;

public class BWWorldGenerators {
	private static final FeatureSize EMPTY_SIZE = new TwoLayersFeatureSize(0, 0, 0);

	public static final ConfiguredFeature<TreeFeatureConfig, ?> JUNIPER_TREE = Feature.TREE.configure(new TreeFeatureConfig.Builder(SimpleBlockStateProviderAccessor.callInit(BWObjects.JUNIPER_LOG.getDefaultState()), new ForkingTrunkPlacer(5, 0, 0), SimpleBlockStateProviderAccessor.callInit(BWObjects.JUNIPER_LEAVES.getDefaultState()), new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)), EMPTY_SIZE).ignoreVines().build());
	public static final PlacedFeature JUNIPER_TREE_WITH_CHANCE = JUNIPER_TREE.withPlacement(VegetationPlacedFeatures.modifiersWithWouldSurvive(RarityFilterPlacementModifier.of(10), BWObjects.JUNIPER_SAPLING));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> CYPRESS_TREE = Feature.TREE.configure(new TreeFeatureConfig.Builder(SimpleBlockStateProviderAccessor.callInit(BWObjects.CYPRESS_LOG.getDefaultState()), new StraightTrunkPlacer(6, 1, 1), SimpleBlockStateProviderAccessor.callInit(BWObjects.CYPRESS_LEAVES.getDefaultState()), new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4), EMPTY_SIZE).ignoreVines().build());
	public static final PlacedFeature CYPRESS_TREE_WITH_CHANCE = CYPRESS_TREE.withPlacement(VegetationPlacedFeatures.modifiersWithWouldSurvive(RarityFilterPlacementModifier.of(10), BWObjects.CYPRESS_SAPLING));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> ELDER_TREE = Feature.TREE.configure(new TreeFeatureConfig.Builder(SimpleBlockStateProviderAccessor.callInit(BWObjects.ELDER_LOG.getDefaultState()), new StraightTrunkPlacer(4, 0, 1), SimpleBlockStateProviderAccessor.callInit(BWObjects.ELDER_LEAVES.getDefaultState()), new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4), EMPTY_SIZE).ignoreVines().build());
	public static final PlacedFeature ELDER_TREE_WITH_CHANCE = ELDER_TREE.withPlacement(VegetationPlacedFeatures.modifiersWithWouldSurvive(RarityFilterPlacementModifier.of(10), BWObjects.ELDER_SAPLING));
	public static final ConfiguredFeature<TreeFeatureConfig, ?> DRAGONS_BLOOD_TREE = Feature.TREE.configure(new TreeFeatureConfig.Builder(SimpleBlockStateProviderAccessor.callInit(BWObjects.DRAGONS_BLOOD_LOG.getDefaultState().with(BWProperties.NATURAL, true)), new StraightTrunkPlacer(5, 1, 1), SimpleBlockStateProviderAccessor.callInit(BWObjects.DRAGONS_BLOOD_LEAVES.getDefaultState()), new MegaPineFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0), ConstantIntProvider.create(3)), EMPTY_SIZE).ignoreVines().build());

	public static final List<OreFeatureConfig.Target> SILVER_ORES = List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, BWObjects.SILVER_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, BWObjects.DEEPSLATE_SILVER_ORE.getDefaultState()));
	public static final List<OreFeatureConfig.Target> SALT_ORES = List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, BWObjects.SALT_ORE.getDefaultState()), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, BWObjects.DEEPSLATE_SALT_ORE.getDefaultState()));

	public static final ConfiguredFeature<?, ?> SILVER_ORE = Feature.ORE.configure(new OreFeatureConfig(SILVER_ORES, 10));
	public static final ConfiguredFeature<?, ?> SILVER_ORE_BURIED = Feature.ORE.configure(new OreFeatureConfig(SILVER_ORES, 10, 0.5f));
	public static final ConfiguredFeature<?, ?> SALT_ORE = Feature.ORE.configure(new OreFeatureConfig(SALT_ORES, 15));
	public static final ConfiguredFeature<?, ?> SALT_ORE_BURIED = Feature.ORE.configure(new OreFeatureConfig(SALT_ORES, 15, 0.5f));

	public static final PlacedFeature SILVER_ORE_UPPER = SILVER_ORE_BURIED.withPlacement(OrePlacedFeaturesAccessor.callModifiersWithCount(4, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-64), YOffset.fixed(32))));
	public static final PlacedFeature SILVER_ORE_LOWER = SILVER_ORE_BURIED.withPlacement(OrePlacedFeaturesAccessor.callModifiers(CountPlacementModifier.of(UniformIntProvider.create(0, 1)), HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(-48))));

	public static final PlacedFeature SALT_ORE_UPPER = SALT_ORE.withPlacement(OrePlacedFeaturesAccessor.callModifiersWithCount(30, HeightRangePlacementModifier.uniform(YOffset.fixed(136), YOffset.getTop())));
	public static final PlacedFeature SALT_ORE_LOWER = SALT_ORE_BURIED.withPlacement(OrePlacedFeaturesAccessor.callModifiersWithCount(20, HeightRangePlacementModifier.trapezoid(YOffset.fixed(0), YOffset.fixed(192))));

	private static void registerEntitySpawn(EntityType<?> type, Predicate<BiomeSelectionContext> predicate, int weight, int minGroupSize, int maxGroupSize) {
		BiomeModifications.addSpawn(predicate, type.getSpawnGroup(), type, weight, minGroupSize, maxGroupSize);
	}

	public static void init() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Bewitchment.MODID, "juniper_tree"), JUNIPER_TREE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Bewitchment.MODID, "juniper_tree"), JUNIPER_TREE_WITH_CHANCE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Bewitchment.MODID, "cypress_tree"), CYPRESS_TREE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Bewitchment.MODID, "cypress_tree"), CYPRESS_TREE_WITH_CHANCE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Bewitchment.MODID, "elder_tree"), ELDER_TREE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Bewitchment.MODID, "elder_tree"), ELDER_TREE_WITH_CHANCE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Bewitchment.MODID, "silver_ore"), SILVER_ORE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Bewitchment.MODID, "silver_ore_buried"), SILVER_ORE_BURIED);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Bewitchment.MODID, "silver_ore_upper"), SILVER_ORE_UPPER);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Bewitchment.MODID, "silver_ore_lower"), SILVER_ORE_LOWER);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Bewitchment.MODID, "salt_ore"), SALT_ORE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Bewitchment.MODID, "salt_ore_buried"), SALT_ORE_BURIED);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Bewitchment.MODID, "salt_ore_upper"), SALT_ORE_UPPER);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Bewitchment.MODID, "salt_ore_lower"), SALT_ORE_LOWER);
        BiomeModification worldGen = BiomeModifications.create(new Identifier(Bewitchment.MODID, "world_features"));

        // Kv: Trees
        if (Bewitchment.config.generateTrees) {
            worldGen.add(ModificationPhase.ADDITIONS, BiomeSelectors.categories(Biome.Category.SAVANNA), context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, JUNIPER_TREE_WITH_CHANCE));
            worldGen.add(ModificationPhase.ADDITIONS, BiomeSelectors.categories(Biome.Category.TAIGA, Biome.Category.SWAMP), context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, CYPRESS_TREE_WITH_CHANCE));
            worldGen.add(ModificationPhase.ADDITIONS, BiomeSelectors.categories(Biome.Category.FOREST), context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, ELDER_TREE_WITH_CHANCE));
        }

        // Kv: Silver & Salt
		worldGen.add(ModificationPhase.ADDITIONS, BiomeSelectors.foundInOverworld(), context -> {
            if (Bewitchment.config.generateSilver) {
				context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_ORES, SILVER_ORE_UPPER);
				context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_ORES, SILVER_ORE_LOWER);
			}
			if (Bewitchment.config.generateSalt) {
				context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_ORES, SALT_ORE_UPPER);
				context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.UNDERGROUND_ORES, SALT_ORE_LOWER);
			}
		});

        // Kv: Animals
        if (Bewitchment.config.generateAnimals) {
            if (Bewitchment.config.owlWeight > 0) {
                registerEntitySpawn(BWEntityTypes.OWL, BiomeSelectors.foundInOverworld().and(context -> Bewitchment.config.owlBiomeCategories.contains(context.getBiome().getCategory().getName())), Bewitchment.config.owlWeight, Bewitchment.config.owlMinGroupCount, Bewitchment.config.owlMaxGroupCount);
                SpawnRestrictionAccessor.callRegister(BWEntityTypes.OWL, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
            }
            if (Bewitchment.config.ravenWeight > 0) {
                registerEntitySpawn(BWEntityTypes.RAVEN, BiomeSelectors.foundInOverworld().and(context -> Bewitchment.config.ravenBiomeCategories.contains(context.getBiome().getCategory().getName())), Bewitchment.config.ravenWeight, Bewitchment.config.ravenMinGroupCount, Bewitchment.config.ravenMaxGroupCount);
                SpawnRestrictionAccessor.callRegister(BWEntityTypes.RAVEN, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
            }
            if (Bewitchment.config.snakeWeight > 0) {
                registerEntitySpawn(BWEntityTypes.SNAKE, BiomeSelectors.foundInOverworld().and(context -> Bewitchment.config.snakeBiomeCategories.contains(context.getBiome().getCategory().getName())), Bewitchment.config.snakeWeight, Bewitchment.config.snakeMinGroupCount, Bewitchment.config.snakeMaxGroupCount);
                SpawnRestrictionAccessor.callRegister(BWEntityTypes.SNAKE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
            }
            if (Bewitchment.config.toadWeight > 0) {
                registerEntitySpawn(BWEntityTypes.TOAD, BiomeSelectors.foundInOverworld().and(context -> Bewitchment.config.toadBiomeCategories.contains(context.getBiome().getCategory().getName())), Bewitchment.config.toadWeight, Bewitchment.config.toadMinGroupCount, Bewitchment.config.toadMaxGroupCount);
                SpawnRestrictionAccessor.callRegister(BWEntityTypes.TOAD, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
            }
        }

        // Kv: Enemies
        if (Bewitchment.config.generateEnemies) {
            if (Bewitchment.config.ghostWeight > 0) {
                registerEntitySpawn(BWEntityTypes.GHOST, BiomeSelectors.foundInOverworld().and(context -> !context.getBiome().getSpawnSettings().getSpawnEntries(BWEntityTypes.GHOST.getSpawnGroup()).isEmpty()), Bewitchment.config.ghostWeight, Bewitchment.config.ghostMinGroupCount, Bewitchment.config.ghostMaxGroupCount);
                SpawnRestrictionAccessor.callRegister(BWEntityTypes.GHOST, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GhostEntity::canSpawn);
            }
            if (Bewitchment.config.vampireWeight > 0) {
                registerEntitySpawn(BWEntityTypes.VAMPIRE, BiomeSelectors.foundInOverworld().and(context -> !context.getBiome().getSpawnSettings().getSpawnEntries(BWEntityTypes.VAMPIRE.getSpawnGroup()).isEmpty()).and(context -> context.getBiome().getCategory() == Biome.Category.TAIGA || context.getBiome().getCategory() == Biome.Category.PLAINS), Bewitchment.config.vampireWeight, Bewitchment.config.vampireMinGroupCount, Bewitchment.config.vampireMaxGroupCount);
                SpawnRestrictionAccessor.callRegister(BWEntityTypes.VAMPIRE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (type, serverWorldAccess, spawnReason, pos, random) -> MobEntity.canMobSpawn(type, serverWorldAccess, spawnReason, pos, random) && additionalSpawnCheck(spawnReason, BewitchmentAPI.getMoonPhase(serverWorldAccess) == 4));
            }
            if (Bewitchment.config.werewolfWeight > 0) {
                registerEntitySpawn(BWEntityTypes.WEREWOLF, BiomeSelectors.foundInOverworld().and(context -> !context.getBiome().getSpawnSettings().getSpawnEntries(BWEntityTypes.WEREWOLF.getSpawnGroup()).isEmpty()).and(context -> context.getBiome().getCategory() == Biome.Category.TAIGA || context.getBiome().getCategory() == Biome.Category.FOREST), Bewitchment.config.werewolfWeight, Bewitchment.config.werewolfMinGroupCount, Bewitchment.config.werewolfMaxGroupCount);
                SpawnRestrictionAccessor.callRegister(BWEntityTypes.WEREWOLF, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (type, serverWorldAccess, spawnReason, pos, random) -> MobEntity.canMobSpawn(type, serverWorldAccess, spawnReason, pos, random) && additionalSpawnCheck(spawnReason, BewitchmentAPI.getMoonPhase(serverWorldAccess) == 0));
            }
            if (Bewitchment.config.hellhoundWeight > 0) {
                registerEntitySpawn(BWEntityTypes.HELLHOUND, BiomeSelectors.foundInTheNether(), Bewitchment.config.hellhoundWeight, Bewitchment.config.hellhoundMinGroupCount, Bewitchment.config.hellhoundMaxGroupCount);
                SpawnRestrictionAccessor.callRegister(BWEntityTypes.HELLHOUND, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (type, serverWorldAccess, spawnReason, pos, random) -> additionalSpawnCheck(spawnReason, !BlockTags.WART_BLOCKS.contains(serverWorldAccess.getBlockState(pos.down()).getBlock())));
            }
        }

        // Kv: Seeds etc.
        if (Bewitchment.config.generateLoot) {
            LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, identifier, fabricLootSupplierBuilder, lootTableSetter) -> {
                Identifier seeds = new Identifier(Bewitchment.MODID, "inject/seeds");
                Identifier nether_fortress = new Identifier(Bewitchment.MODID, "inject/nether_fortress");

                if (Blocks.GRASS.getLootTableId().equals(identifier) || Blocks.TALL_GRASS.getLootTableId().equals(identifier) || Blocks.FERN.getLootTableId().equals(identifier) || Blocks.LARGE_FERN.getLootTableId().equals(identifier)) {
                    fabricLootSupplierBuilder.withPool(LootPool.builder().with(LootTableEntry.builder(seeds).weight(1)).build());
                }

                if (LootTables.NETHER_BRIDGE_CHEST.equals(identifier)) {
                    fabricLootSupplierBuilder.withPool(LootPool.builder().with(LootTableEntry.builder(nether_fortress).weight(1)).build());
                }
            });
        }

	}

	private static boolean additionalSpawnCheck(SpawnReason spawnReason, boolean check) {
		if (spawnReason == SpawnReason.NATURAL || spawnReason == SpawnReason.CHUNK_GENERATION) {
			return check;
		}
		return true;
	}
}
