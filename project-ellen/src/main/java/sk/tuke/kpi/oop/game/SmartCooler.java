package sk.tuke.kpi.oop.game;

public class SmartCooler extends Cooler {

    private Reactor reactor;

    public SmartCooler(Reactor reactor){
        super(reactor);
        this.reactor = reactor;
    }

    public void coolReactor(){
        if(reactor != null){
            super.coolReactor();
            if (reactor.getTemperature() >= 2500 && reactor.isOn()) this.turnOn();
            if (reactor.getTemperature() < 1500) this.turnOff();
        }
    }
}
