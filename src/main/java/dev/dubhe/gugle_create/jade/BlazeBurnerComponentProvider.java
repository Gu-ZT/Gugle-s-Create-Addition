package dev.dubhe.gugle_create.jade;

import com.mojang.logging.LogUtils;

import com.simibubi.create.content.contraptions.processing.burner.BlazeBurnerTileEntity;

import dev.dubhe.gugle_create.mixin.BlazeBurnerTileEntityAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;

public enum BlazeBurnerComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockEntity> {
	INSTANCE;

	@Override
	public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig iPluginConfig) {
		String time = "0";
		if (accessor.getServerData().contains("Fuel")) {
			time = String.valueOf(accessor.getServerData().getInt("Fuel") / 20);
		}
		if (accessor.getServerData().contains("Creative")) {
			if (accessor.getServerData().getBoolean("Creative")) time = "∞";
		}
		Item item = Items.CAMPFIRE;
		if (accessor.getServerData().contains("Special")) {
			if (accessor.getServerData().getBoolean("Special")) item = Items.SOUL_CAMPFIRE;
		}
		IElementHelper elements = tooltip.getElementHelper();
		IElement icon = elements.item(new ItemStack(item), 0.5f).size(new Vec2(10, 10)).translate(new Vec2(0, -1));
		tooltip.add(icon);
		tooltip.append(new TranslatableComponent("gugle_create.blaze_burner.time", time));
	}

	@Override
	public ResourceLocation getUid() {
		return CreatePlugin.BLAZE_BURNER;
	}

	@Override
	public void appendServerData(CompoundTag data, ServerPlayer serverPlayer, Level level, BlockEntity entity, boolean b) {
		BlazeBurnerTileEntityAccessor blazeBurner = (BlazeBurnerTileEntityAccessor) entity;
		data.putInt("Fuel", blazeBurner.getRemainingBurnTime());
		data.putBoolean("Creative", blazeBurner.isCreative());
		data.putBoolean("Special", blazeBurner.getActiveFuel() == BlazeBurnerTileEntity.FuelType.SPECIAL);
	}
}
