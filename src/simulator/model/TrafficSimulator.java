package simulator.model;

import java.util.List;

import org.json.JSONObject;

import exceptions.NegativeException;
import exceptions.StatusException;
import simulator.misc.SortedArrayList;

public class TrafficSimulator {

	private RoadMap mapaCarreteras; // guarda todos los objetos de la simulacion
	private List<Event> listaEventos; // usar SortedArrayList
	private int tiempo;

	public TrafficSimulator() {
		super();
		this.mapaCarreteras = new RoadMap();
		this.listaEventos = new SortedArrayList<Event>();
		this.tiempo = 0;
	}

	public void addEvent(Event e) {
		listaEventos.add(e);
	}

	public void advance() throws NegativeException, StatusException {
		// 1
		tiempo++;
		// 2

		while (listaEventos.size() > 0 && listaEventos.get(0).getTime() == this.tiempo) {

			listaEventos.remove(0).execute(mapaCarreteras);

		}

		// 3
		for (Junction cruces : mapaCarreteras.getJunctions()) {
			cruces.advance(tiempo);
		}
		// 4
		for (Road carreteras : mapaCarreteras.getRoads()) {
			carreteras.advance(tiempo);
		}

	}

	public void reset() {
		tiempo = 0;
		mapaCarreteras.reset();
		listaEventos.clear();
	}

	public JSONObject report() {
		JSONObject jo = new JSONObject();
		jo.put("time", tiempo);
		jo.put("state", mapaCarreteras.report());
		return jo;
	}

}
