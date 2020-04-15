package simulator.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class StatusBar extends JPanel implements TrafficSimObserver {

	private Controller _ctrl;
	private JLabel text;
	private JLabel evento;

	public StatusBar(Controller _ctrl) {
		super();
		this._ctrl = _ctrl;
		text = new JLabel("Time: ");
		evento = new JLabel("Mensaje: ");
		_ctrl.addObserver(this); //lo ha dicho la profe, deberia estar tbn en control panel o ahi no?
		initGUI();
	}

	private void initGUI() {
		FlowLayout  flow = new FlowLayout();	//es para rellenar pero...HACE ALGO?
		this.setLayout(flow);
		
		this.add(text);
		this.add(evento);
		this.setBorder(getBorder());		
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String err) {
		// TODO Auto-generated method stub
		
	}

}
