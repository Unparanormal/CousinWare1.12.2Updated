package io.ace.nordclient.hacks.render;

import io.ace.nordclient.NordClient;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.hacks.client.ClickGuiHack;
import io.ace.nordclient.utilz.FontRenderUtil;
import io.ace.nordclient.utilz.TpsUtils;
import io.ace.nordclient.utilz.clientutil.Setting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class Overlay extends Hack {


    Setting x;
    Setting y;
    Setting ping;
    Setting server;
    Setting fps;
    Setting tps;

    public Overlay() {
        super("Overlay", Category.RENDER);
        NordClient.INSTANCE.settingsManager.rSetting(x = new Setting("x", this, 959, 0, 1000, false, "OverlayX"));
        NordClient.INSTANCE.settingsManager.rSetting(y = new Setting("y", this, 500, 0, 1000, false, "OverlayY"));
        NordClient.INSTANCE.settingsManager.rSetting(ping = new Setting("Ping", this, true, "OverlayPing"));
        NordClient.INSTANCE.settingsManager.rSetting(server = new Setting("Server", this, true, "OverlayServer"));
        NordClient.INSTANCE.settingsManager.rSetting(fps = new Setting("Fps", this, true, "OverlayFps"));
        NordClient.INSTANCE.settingsManager.rSetting(tps = new Setting("Tps", this, true, "OverlayTps"));

    }

    @SubscribeEvent
    public void onRenderWorld(RenderGameOverlayEvent.Text event) {
        int yOffset = 0;
        String tpsString = "Tps " + Math.round(TpsUtils.getTickRate() * 10) / 10.0;
        String fpsString = "Fps " + mc.getDebugFPS();
        Color c = new Color(ClickGuiHack.red.getValInt(), ClickGuiHack.green.getValInt(), ClickGuiHack.blue.getValInt(), 255);

        if (server.getValBoolean()) {
            if (!mc.isSingleplayer()) {
                FontRenderUtil.drawLeftStringWithShadow("Server " + mc.getCurrentServerData().serverIP, x.getValInt(), y.getValInt() + yOffset * -10, c.getRGB());
                yOffset++;
            }
        }

        if (ping.getValBoolean()) {
            if (mc.isSingleplayer()) {
                FontRenderUtil.drawLeftStringWithShadow("Ping " + "0" + "ms", x.getValInt(), y.getValInt() + yOffset * -10, c.getRGB());
                yOffset++;
            } else {
                FontRenderUtil.drawLeftStringWithShadow("Ping " + mc.getCurrentServerData().pingToServer + "ms", x.getValInt(), y.getValInt() + yOffset * -10, c.getRGB());
                yOffset++;
            }
        }
        if (tpsString.length() > fpsString.length()) {
            if (tps.getValBoolean()) {
                FontRenderUtil.drawLeftStringWithShadow("Tps " + Math.round(TpsUtils.getTickRate() * 10) / 10.0, x.getValInt(), y.getValInt() + yOffset * -10, c.getRGB());
                yOffset++;
            }
            if (fps.getValBoolean()) {
                FontRenderUtil.drawLeftStringWithShadow("Fps " + mc.getDebugFPS(), x.getValInt(), y.getValInt() + yOffset * -10, c.getRGB());
                yOffset++;
            }

        } else {
            if (fps.getValBoolean()) {
                FontRenderUtil.drawLeftStringWithShadow("Fps " + mc.getDebugFPS(), x.getValInt(), y.getValInt() + yOffset * -10, c.getRGB());
                yOffset++;
            }
            if (tps.getValBoolean()) {
                FontRenderUtil.drawLeftStringWithShadow("Tps " + Math.round(TpsUtils.getTickRate() * 10) / 10.0, x.getValInt(), y.getValInt() + yOffset * -10, c.getRGB());
                yOffset++;
            }


        }




    }
}
