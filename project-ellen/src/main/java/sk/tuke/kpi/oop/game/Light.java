package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable,EnergyConsumer {

    public boolean isOn;
    public boolean isPowered;
    private Animation onAnimation;
    private Animation offAnimation;

    public Light() {
        this.isOn = false;
        this.isPowered = false;
        this.onAnimation = new Animation("sprites/light_on.png",16,16);
        this.offAnimation = new Animation("sprites/light_off.png",16,16);
        setAnimation(offAnimation);
    }

    private void updateAnimation() {
        if (isOn && isPowered) {
            setAnimation(onAnimation);
        } else {
            setAnimation(offAnimation);
        }
    }

    public void toggle(){
        isOn = !isOn;
        updateAnimation();
    }

    @Override
    public void turnOn() {
        isOn = true;
        updateAnimation();
    }

    @Override
    public void turnOff() {
        isOn = false;
        updateAnimation();
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public void setPowered(boolean powered) {
        isPowered = powered;
        updateAnimation();
    }
}
