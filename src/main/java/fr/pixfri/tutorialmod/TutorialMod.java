package fr.pixfri.tutorialmod;

import fr.pixfri.tutorialmod.block.ModBlocks;
import fr.pixfri.tutorialmod.item.ModItemGroups;
import fr.pixfri.tutorialmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerItems();

		ModBlocks.registerModBlocks();
	}
}