package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;
import sk.tuke.kpi.oop.game.tools.FireExtinguisher;
import sk.tuke.kpi.oop.game.tools.Hammer;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable,Repairable {

    private boolean isRunning;
    private int temperature;
    private int damage;
    private Animation normalAnimation;
    private Animation hotAnimation;
    private Animation brokenAnimation;
    private Animation offAnimation;
    private Set<EnergyConsumer> devices;

    public Reactor() {
        this.isRunning = false;
        this.temperature = 0;
        this.damage = 0;
        this.normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
        this.brokenAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.offAnimation = new Animation("sprites/reactor.png", 80, 80);
        devices = new HashSet<>();
        setAnimation(offAnimation);
    }

    public int getTemperature() {
        return this.temperature;
    }

    public int getDamage() {
        return this.damage;
    }

    public boolean isOn() {
        return this.isRunning;
    }

    public void turnOn() {
        this.isRunning = true;
        updateAnimation();

    }

    public void turnOff() {
        this.isRunning = false;
        updateAnimation();
    }

    public void increaseTemperature(int increment) {

        int incr = increment;
        if (! isRunning) return;
        if(incr<0) return;

        if (damage > 66) {
            incr *= 2;
        } else if (damage > 33) {
            incr *= 1.5;
        }

        this.temperature += incr;
        if (temperature > 2000) {
            damage = (int)((temperature - 2000) * 0.025);
        }
        if (temperature >= 6000) {
            damage = 100;
            turnOff();
        }

        updateAnimation();
    }

    public void decreaseTemperature(int decrement) {
        int decr = decrement;
        if(!isRunning || decr < 0 || damage>= 100) return;
        if (damage>50 && damage <100) decr /= 2;
        temperature = temperature-decr;
        updateAnimation();
    }

    public void repairWith(Hammer hammer) {

        if (!isRunning || hammer == null || damage == 0 || damage == 100) return;

        damage -= 50;
        if (damage < 0) damage = 0;
        if (temperature > 2000) temperature = 2000 + damage * 40;
        hammer.useWith();
        updateAnimation();
    }

    public void addDevice(EnergyConsumer device) {
        devices.add(device);
        device.setPowered(true);
    }
    public void removeDevice(EnergyConsumer device) {
        devices.remove(device);
        device.setPowered(false);
    }

    private void updateAnimation() {
        if (damage == 100) setAnimation(brokenAnimation);
         else if (! isRunning) setAnimation(offAnimation);
         else if (temperature > 4000) setAnimation(hotAnimation);
         else setAnimation(normalAnimation);
    }

    public void extinguishWith(FireExtinguisher fireExtinguisher) {
        if (getDamage() <= 100 && fireExtinguisher != null && fireExtinguisher.getUses() > 0) {

            fireExtinguisher.useWith();
            temperature = temperature - 4000;
            if (temperature < 0) temperature = 0;
            Animation extinguished = new Animation("sprites/reactor_extinguished.png", 80, 80, 10);
            setAnimation(extinguished);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleOn(this);
    }

    @Override
    public boolean repair() {
        return false;
    }
}
