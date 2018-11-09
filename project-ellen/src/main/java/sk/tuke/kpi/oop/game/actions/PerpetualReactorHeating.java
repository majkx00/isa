package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

public class PerpetualReactorHeating extends AbstractAction<Reactor> {

    private int temperature;

    public PerpetualReactorHeating(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public void execute(float deltaTime) {
        Reactor reactor = getActor();
        reactor.increaseTemperature(temperature);
    }
}
