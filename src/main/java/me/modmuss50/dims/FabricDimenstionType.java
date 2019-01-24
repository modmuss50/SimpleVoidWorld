package me.modmuss50.dims;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.function.BiFunction;

public class FabricDimenstionType extends DimensionType {

	public int id;

	public FabricDimenstionType(Identifier name, int id, BiFunction<World, DimensionType, ? extends Dimension> factory) {
		super(id, name.getNamespace() + "_" + name.getPath(), "DIM_" + name.getNamespace() + "_" + name.getPath(), factory, true);
		this.id = id;
		register(name);
	}

	private DimensionType register(Identifier identifier) {
		return Registry.set(Registry.DIMENSION, this.id, identifier.toString(), this);
	}
}
