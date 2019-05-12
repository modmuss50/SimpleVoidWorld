package me.modmuss50.svw;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.function.BiFunction;

public class DimensionTypeWrapper extends DimensionType {

	public int id;

	public DimensionTypeWrapper(Identifier name, int id, BiFunction<World, DimensionType, ? extends Dimension> factory) {
		super(id, name.getNamespace() + "_" + name.getPath(), "DIM_" + name.getNamespace() + "_" + name.getPath(), factory, true);
		this.id = id;
		register(name);
	}

	private DimensionType register(Identifier identifier) {
		return Registry.register(Registry.DIMENSION, this.id, identifier.toString(), this);
	}
}
