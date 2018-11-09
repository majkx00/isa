package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable {

    private boolean isOn;
    private Reactor reactor;
    private Animation onAnimation;
    private Animation offAnimation;

    public Cooler(Reactor reactor) {
        this.isOn = false;
        this.reactor = reactor;
        this.onAnimation = new Animation("sprites/fan.png",32,32,0.2f, Animation.PlayMode.LOOP_PINGPONG);
        this.offAnimation = new Animation("sprites/fan.png",32,32,0.2f,Animation.PlayMode.ONCE);
        setAnimation(offAnimation);
    }

    public void turnOn() {
        setAnimation(onAnimation);
        this.isOn = true;
    }

    public void turnOff() {
        setAnimation(offAnimation);
        this.isOn = false;
    }

    public boolean isOn() {
        return this.isOn;
    }

    public void coolReactor() {
        if (isOn && reactor.getTemperature()>0) {
            reactor.decreaseTemperature(1);
        }
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke(this::coolReactor)).scheduleOn(this);
    }
}
