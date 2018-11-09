package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.gamelib.map.MapMarker;

import java.util.Map;

public class Gameplay extends Scenario {

    @Override
    public void setupPlay(Scene scene) {
        Map<String, MapMarker> markers = scene.getMap().getMarkers();
        MapMarker reactorArea2 = markers.get("reactor-area-2");
        MapMarker coolerArea1 = markers.get("cooler-area-2");

        Reactor reactor1 = new Reactor();
        scene.addActor(reactor1, reactorArea2.getPosX(), reactorArea2.getPosY());
        reactor1.turnOn();

        Cooler cooler1 = new Cooler(reactor1);
        scene.addActor(cooler1, coolerArea1.getPosX(), coolerArea1.getPosY());

    }
}
