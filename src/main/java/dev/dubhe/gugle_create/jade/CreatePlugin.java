package dev.dubhe.gugle_create.jade;

import com.simibubi.create.content.contraptions.processing.burner.BlazeBurnerBlock;

import com.simibubi.create.content.contraptions.processing.burner.BlazeBurnerTileEntity;

import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;

public class CreatePlugin implements IWailaPlugin {
	public static final ResourceLocation BLAZE_BURNER = new ResourceLocation("gugle_create", "blaze_burner");
	@Override
	public void register(IWailaCommonRegistration registration) {
		registration.registerBlockDataProvider(BlazeBurnerComponentProvider.INSTANCE, BlazeBurnerTileEntity.class);
	}

	@Override
	public void registerClient(IWailaClientRegistration registration) {
		registration.registerBlockComponent(BlazeBurnerComponentProvider.INSTANCE, BlazeBurnerBlock.class);
	}
}
