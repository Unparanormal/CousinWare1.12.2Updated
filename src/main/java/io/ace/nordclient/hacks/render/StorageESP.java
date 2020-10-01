package io.ace.nordclient.hacks.render;

import io.ace.nordclient.CousinWare;
import io.ace.nordclient.event.RenderEvent;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.utilz.NordTessellator;
import io.ace.nordclient.utilz.Setting;
import net.minecraft.tileentity.*;
import net.minecraft.util.math.BlockPos;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

public class StorageESP extends Hack {

    Setting eChest;
    Setting chest;
    Setting shulker;

    public StorageESP() {
        super("StorageESP", Category.RENDER);
        CousinWare.INSTANCE.settingsManager.rSetting(eChest = new Setting("EChest", this, true, "StorageESPEChest"));
        CousinWare.INSTANCE.settingsManager.rSetting(chest = new Setting("Chest", this, true, "StorageESPChest"));
        CousinWare.INSTANCE.settingsManager.rSetting(shulker = new Setting("Shulker", this, true, "StorageESPShulker"));
    }

    @Listener
    public void onWorldRender(RenderEvent event) {
        for (TileEntity e : mc.world.loadedTileEntityList) {
            if (e instanceof TileEntityEnderChest && eChest.getValBoolean()) {
                BlockPos eChestPos = e.getPos();
                NordTessellator.drawBoundingBoxChestBlockPos(eChestPos, 1, 145, 43, 173, 255);
            }
            if (e instanceof TileEntityShulkerBox && shulker.getValBoolean()) {
                BlockPos shulkerPos = e.getPos();
                NordTessellator.drawBoundingBoxBlockPos(shulkerPos, 1, 243, 0, 127, 255);
            }
            if (e instanceof TileEntityChest && chest.getValBoolean()) {
                    BlockPos chestPos = e.getPos();
                    NordTessellator.drawBoundingBoxChestBlockPos(chestPos, 1, 255, 150, 60, 255);

                }
            }
        }
    }
