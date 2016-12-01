package me.modmuss50.svw;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by modmuss50 on 01/12/16.
 */
public class Config {

	public static Configuration config;
	public static String CATEGORY_IDS = "IDs";
	public static String CATEGORY_TWEAKS = "TWEAKS";

	public static int dimID = 43;
	public static boolean darkSky = true;
	public static boolean eternalDay = true;

	public static void load(File configFile) {
		config = new Configuration(configFile);
		config.load();

		dimID = config.get(CATEGORY_IDS, "Dim id", 43, "This is the id of the dimension in the mod, this should be unique to Simple Void World").getInt();

		darkSky = config.get(CATEGORY_TWEAKS, "Dark mode", false, "When set to true the sky and fog color are black this creates a seamless skybox").getBoolean();
		eternalDay = config.get(CATEGORY_TWEAKS, "Its High Noon", false, "When true this locks the at noon and creates an eternal day").getBoolean();

		config.save();
	}

}
