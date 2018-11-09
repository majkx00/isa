package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {

    Animation bomb;
    private Animation activeBomb;
    private Animation explodeBomb;
    private float timeout;
    private boolean activated;

    public TimeBomb(float timeout) {
        activated = false;
        this.timeout = timeout;
        bomb = new Animation("sprites/bomb.png", 16, 16, 100);
        activeBomb = new Animation("sprites/bomb_activated.png", 16, 16, 50);
        explodeBomb = new Animation("sprites/small_explosion.png", 16, 16, 100);
        setAnimation(bomb);
    }

    public void activate(){
        activated = true;
        setAnimation(activeBomb);
    }

    public boolean isActivated(){
        return activated;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        if (activated) {
            timeout--;
            if (timeout <= 0) {
                setAnimation(explodeBomb);
            }
            if (timeout == -50) {
                getScene().removeActor(this);
            }
        }
    }
}
