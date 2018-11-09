package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {

    private boolean isPowered;
    public Computer() {
        setAnimation(new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
    }

    public int add(int val1, int val2) {
        if(isPowered) return val1 + val2;
        return 0;
    }

    public double add(double val1, double val2) {
        if(isPowered)return val1 + val2;
        return 0;
    }

    public int sub(int val1, int val2) {

        if(isPowered) return val1 - val2;
        return 0;
    }

    public double sub(double val1, double val2) {

        if(isPowered)return val1 - val2;
        return 0;
    }

    @Override
    public void setPowered(boolean powered) {
        isPowered = powered;
    }
}
