package me.modmuss50.svw;

import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
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
		//reflection hack, dev = mapped in dev enviroment, prod = intermediate value
		String dev_name = "net.minecraft.world.gen.chunk.ChunkGeneratorFactory";
		String prod_name = "net.minecraft.class_2801";

		try {
			factoryClass = Class.forName(dev_name);
		} catch (ClassNotFoundException e1) {
			try {
				factoryClass = Class.forName(prod_name);
			} catch (ClassNotFoundException e2) {
				throw (new RuntimeException("Unable to find " + dev_name));
			}
		}
		factoryProxy = Proxy.newProxyInstance(factoryClass.getClassLoader(),
			new Class[] { factoryClass },
			this);
	}

	public VoidChunkGenerator createProxy(World w, BiomeSource biomesource, ChunkGeneratorSettings gensettings) {
		return new VoidChunkGenerator(w, biomesource, gensettings);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (args.length == 3 &&
			args[0] instanceof World &&
			args[1] instanceof BiomeSource &&
			args[2] instanceof ChunkGeneratorSettings
		) {

			return createProxy((World) args[0],
				(BiomeSource) args[1],
				(ChunkGeneratorSettings) args[2]);
		}
		throw (new UnsupportedOperationException("Unknown Method: " + method.toString()));
	}

	public ChunkGeneratorType getChunkGeneratorType(Supplier<ChunkGeneratorSettings> supplier) {
		Constructor<?>[] initlst = ChunkGeneratorType.class.getDeclaredConstructors();
		final Logger log = LogManager.getLogger("ChunkGenErr");

		for (Constructor<?> init : initlst) {
			init.setAccessible(true);
			if (init.getParameterCount() != 3) {
				continue; //skip
			}
			//lets try it
			try {
				return (ChunkGeneratorType) init.newInstance(factoryProxy, true, supplier);
			} catch (Exception e) {
				log.error("Error in calling Chunk Generator Type", e);
			}
		}
		log.error("Unable to find constructor for ChunkGeneratorType");
		return null;
	}
}