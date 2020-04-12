package simulator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
		//TODO barraBotones.add(BOTONES);  //hacer algo de setVisible?
		
		/*BOTONES: Carga del fichero de eventos*/			
		JFileChooser fileChooser = new JFileChooser();
		JButton load= new JButton();
		ImageIcon icono1 = new ImageIcon("resources/icons/open.png");		
		load.setIcon(icono1);		
		load.setToolTipText("Guapeton cargame el fichero que tu quieras");
		//añadir alguna configuracion mas (3 min creo que dijo)
				
		load.addActionListener(
				new ActionListener(){					
					public void actionPerformed(ActionEvent arg0) {
						int returnValue = fileChooser.showOpenDialog(fileChooser);			//TODO revisar						
						if(returnValue == fileChooser.APPROVE_OPTION) {	
							try {
								Main.set_inFile(fileChooser.getSelectedFile().getName());
								_ctrl.reset();
								_ctrl.loadEvents(new FileInputStream(fileChooser.getName()));	//revisar
							}catch(Exception ex){
								JOptionPane.showMessageDialog(null, "Error al abrir archivo");
							}							
						}
					} 
				}	
		);
		barraBotones.add(load);	//lo añadimos
		
		
		/*BOTONES: Cambio de la clase de contaminación de un vehículo*/
		JButton changeCO2= new JButton();
		ImageIcon icono2 = new ImageIcon("resources/icons/open.png");		
		changeCO2.setIcon(icono2);
		changeCO2.setToolTipText("Guapeton cambia de la clase de contaminación de un vehículo");
	
		changeCO2.addActionListener(
				new ActionListener(){					
					public void actionPerformed(ActionEvent arg0) {
						JDialog dialogo = new JDialog();
						int returnValue = dialogo.getDefaultCloseOperation();	//si?
						if(returnValue == 1) {	
							try {
							}catch(Exception ex){
							
							}
						}
					} 
				}	
		);
		//num Ticks-->jspinner
		//vehicle y co2 -->jcombobox
		
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
