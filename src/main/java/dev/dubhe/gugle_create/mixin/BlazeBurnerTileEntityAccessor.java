package dev.dubhe.gugle_create.mixin;

import com.simibubi.create.content.contraptions.processing.burner.BlazeBurnerTileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlazeBurnerTileEntity.class)
public interface BlazeBurnerTileEntityAccessor {
	@Accessor(value = "remainingBurnTime", remap = false)
	int getRemainingBurnTime();

	@Accessor(value = "isCreative", remap = false)
	boolean isCreative();

	@Accessor(value = "activeFuel", remap = false)
	BlazeBurnerTileEntity.FuelType getActiveFuel();
}
