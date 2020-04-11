package simulator.view;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class ControlPanel extends JPanel implements TrafficSimObserver {
	
	private Controller _ctrl;

	public ControlPanel(Controller _ctrl) {
		super();
		//cada uno de los botonoes
		//una jtoolbar (dnd estaran los botones)
		//atibuto roadMap que se actualice cuando el modelo cambie (min 24:25)
		//tiempo actual
		//controlador.addObserver(this);   //en todas
		//jfilechooser (min 29:36)
		this._ctrl = _ctrl;
	}
	
	//ñam ñam
	private void initGUI() {
		/*SUPONGO QUE A ESTO LO LLAMA LA CONSTRUCTORA*/
		JToolBar barraBotones = new JToolBar();
		//barraBotones.add(BOTONES);
		this.add(barraBotones);
		//TBN HA DICHO ALGO DE UN ATRIBUTO Y SUAMRLO PERO NO ENTENDI (MIN 28:50)
		
		
		/*ESTO NO HA ESPECIFICADO DND VA... HAY QUE VER DOND LO METEMOS SI AKI O EN LA CONSTRUC*/
		JFileChooser fileChooser = new JFileChooser();
		JButton load= new JButton();
		//lo configuramos
		//settoolgymierdas pone un texto al pasar el raton por encima 
		//setIcon
		//mouselistener
		//addactionlisener para registrar un listener en el boton
		
		
		
		
		//en actionPerform...
		fileChooser.showOpenDialog(this);
		//fileChooser.showOpenDialog(parent); algo de esto
		//si se da okey devuelve approve opction, aunque esto es lo que he encontrado-->	fileChooser.approveSelection();
		// y si lo es habrai que...
		//main.setinfile , un setter para este artributo infile del main, y ahi le pasamos el archivo que tal... con getselectedfile
		//luego viene bien en el enunciado llamar al reset y blah blah blah (min33)
		
	}
	
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
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
