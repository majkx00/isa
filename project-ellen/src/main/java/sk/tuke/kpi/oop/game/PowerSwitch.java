package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor implements Switchable {

    private Switchable switchable;

    public PowerSwitch(Switchable switchable) {
        this.switchable = switchable;
        setAnimation(new Animation("sprites/switch.png",16,16));
    }

    public void switchOn(){
        if(switchable != null) switchable.turnOn();
        getAnimation().setTint(Color.WHITE);
    }

    public void switchOff(){
        if(switchable != null) switchable.turnOff();
        getAnimation().setTint(Color.GRAY);
    }

    public Switchable getDevice(){
        return switchable;
    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }

    @Override
    public boolean isOn() {
        return false;
    }
}
