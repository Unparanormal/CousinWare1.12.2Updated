package io.ace.nordclient.hacks.player;

import io.ace.nordclient.event.EventPlayerTravel;
import io.ace.nordclient.hacks.Hack;
import io.ace.nordclient.utilz.MathUtil;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

public class NoSlow extends Hack {

    public NoSlow() {
        super("NoSlow", Category.PLAYER, 1);
    }

    public void onUpdate() {
        if (mc.player.isHandActive()) {
            final double[] dir = MathUtil.directionSpeed(.26);
            mc.player.motionX = dir[0];
            mc.player.motionZ = dir[1];
        }
    }
}
