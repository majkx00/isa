package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class Helicopter  extends AbstractActor {

    private Player player;
    private boolean isChasing;

    public Helicopter() {
        setAnimation(new Animation("sprites/heli.png", 64, 64, 0.1f, Animation.PlayMode.LOOP));
        isChasing=false;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke(() ->kill(scene))).scheduleOn(this);
    }

    public void searchAndDestroy(){
        isChasing = true;
    }

    private void kill(Scene scene){
        if(isChasing){
            int x_heli = this.getPosX();
            int y_heli = this.getPosY();

            player = (Player) scene.getFirstActorByName("Player");

            y_heli+= (player.getPosY() < this.getPosY()) ? -1 : 1;
            x_heli+= (player.getPosX() < this.getPosX()) ? -1 : 1;

            int actLife = player.getEnergy();
            if(this.intersects(player)){
                player.setEnergy(actLife-1);
            }
            if(actLife == 0){
                isChasing = false;
            }
            this.setPosition(x_heli,y_heli);
        }
    }


}
