package simulator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import simulator.control.Controller;
import simulator.launcher.Main;
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
		//tiempo :actual + num ticks o algo asi
		//controlador.addObserver(this);   //en todas las contructuroa de todas las clases...
		//jfilechooser (min 29:36)
		this._ctrl = _ctrl;
	}
	
	//ñam ñam
	private void initGUI() {//creomos lo botones, los inicializamos, y hacemos el codigo...  //LA LLAMA LA CONSTRUCTORA
		
		JToolBar barraBotones = new JToolBar();
		this.add(barraBotones);
		//TODO barraBotones.add(BOTONES);
		
		/*Carga del fichero de eventos*/		
			
		JFileChooser fileChooser = new JFileChooser();
		JButton load= new JButton();
		ImageIcon icono = new ImageIcon("resources/icons/open.png");		
		load.setIcon(icono);		
		load.setToolTipText("Guapeton cargame el fichero que tu quieras");
		//añadir alguna configuracion mas (3 min creo que dijo)
		
		
		load.addActionListener(
				new ActionListener(){					
					public void actionPerformed(ActionEvent arg0) {
						int returnValue = fileChooser.showOpenDialog(fileChooser);//TODO revisar						
						if(returnValue == fileChooser.APPROVE_OPTION) {							
							Main.set_inFile(fileChooser.getSelectedFile().getName());
						}
					} 
				}	
		);
		
			
		
		// y si lo es habrai que...
		//main.setinfile , un setter para este artributo infile del main, y ahi le pasamos el archivo que tal... con getselectedfile
		//luego viene bien en el enunciado llamar al reset y blah blah blah (min33) METIENDOLO EN UN TRY CATCH
		//dice alguna cosilla interesante del catch de como mostrrar los errores
		
		
		
		//77crear un atributo ewstado para el cambio de clase de contaminacion del vehiculo del pdf
		//si 0 en cancel por ejem y 1 en okey
		//sumar lista de parejas etc etc
		
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
