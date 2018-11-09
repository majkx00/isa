package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class DefectiveLight extends Light implements Repairable {
    private boolean repaired;
    private int time;
    private Animation onAnimation;
    private Animation offAnimation;

    public DefectiveLight(){
        this.onAnimation = new Animation("sprites/light_on.png",16,16);
        this.offAnimation = new Animation("sprites/light_off.png",16,16);
    }

    public void turnOn(){}

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        if(!repaired) {
            int a = (int) Math.ceil(Math.random() * 20);
            if (a == 1) {
                this.toggle();
            }
        }else time++;

        if(time == 1000){
            repaired = false;
            time = 0;
        }

        if(repaired){
            turnOn();
            updateAnimation();
        }

    }

    private void updateAnimation() {
        if (isOn && isPowered) {
            setAnimation(onAnimation);
        } else {
            setAnimation(offAnimation);
        }
    }

    @Override
    public boolean repair() {
        return false;
    }
}
