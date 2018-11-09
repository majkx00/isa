package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.tools.BreakableTool;
import sk.tuke.kpi.oop.game.tools.Usable;

public class Wrench extends BreakableTool implements Usable {

    public Wrench() {
        super(2);
        setAnimation(new Animation("sprites/wrench.png",16,16));
    }
}

