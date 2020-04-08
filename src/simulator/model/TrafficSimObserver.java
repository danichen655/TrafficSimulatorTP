package simulator.model;

import java.util.List;

public interface TrafficSimObserver {		//TODO esto se mete dentro de otra clase o aqui esta bien
	void onAdvanceStart(RoadMap map, List<Event> events, int time);

	void onAdvanceEnd(RoadMap map, List<Event> events, int time);

	void onEventAdded(RoadMap map, List<Event> events, Event e, int time);

	void onReset(RoadMap map, List<Event> events, int time);

	void onRegister(RoadMap map, List<Event> events, int time);

	void onError(String err);
}

