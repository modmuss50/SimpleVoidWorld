package me.modmuss50.svw;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

//Wee this is fun, thanks https://github.com/ezterry/ezwasteland/blob/4c4d825309e713bebbece2191900774e80dfb7b1/src/main/java/com/ezrol/terry/minecraft/wastelands/EzwastelandsFabric.java#L126
public class ChunkGeneratorTypeWorkaround implements InvocationHandler {

	private Object factoryProxy;
	private Class factoryClass;

	public ChunkGeneratorTypeWorkaround() {
		String className = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_2801");

		try {
			factoryClass = Class.forName(className);
		} catch (ClassNotFoundException e1) {
			throw (new RuntimeException("Unable to find " + className, e1));
		}
		factoryProxy = Proxy.newProxyInstance(factoryClass.getClassLoader(), new Class[]{factoryClass}, this);
	}

	public VoidChunkGenerator createProxy(World w, BiomeSource biomesource, ChunkGeneratorConfig gensettings) {
		return new VoidChunkGenerator(w, biomesource, gensettings);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		if (args.length == 3 && args[0] instanceof World && args[1] instanceof BiomeSource && args[2] instanceof ChunkGeneratorConfig) {

			return createProxy((World) args[0], (BiomeSource) args[1], (ChunkGeneratorConfig) args[2]);
		}
		throw (new UnsupportedOperationException("Unknown Method: " + method.toString()));
	}

	public <C extends ChunkGeneratorConfig, T extends ChunkGenerator<C>> ChunkGeneratorType<C, T> getChunkGeneratorType(Supplier<C> supplier) {
		Constructor<?>[] initlst = ChunkGeneratorType.class.getDeclaredConstructors();
		final Logger log = LogManager.getLogger("ChunkGenErr");

		for (Constructor<?> init : initlst) {
			init.setAccessible(true);
			if (init.getParameterCount() != 3) {
				continue; //skip
			}
			//lets try it
			try {
				return (ChunkGeneratorType<C, T>) init.newInstance(factoryProxy, true, supplier);
			} catch (Exception e) {
				log.error("Error in calling Chunk Generator Type", e);
			}
		}
		log.error("Unable to find constructor for ChunkGeneratorType");
		return null;
	}
}