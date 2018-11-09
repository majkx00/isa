package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool extends AbstractActor implements Usable {
    private int remainingUses;

    public BreakableTool(int uses){
        remainingUses = uses;
    }

    public void useWith(){
        remainingUses--;
        if(remainingUses == 0) getScene().removeActor(this);
    }

    public void setUses(int uses){
        remainingUses = uses;
    }
    public int getUses(){ return this.remainingUses;}
}
