package simulator.view;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.launcher.Main;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class ControlPanel extends JPanel implements TrafficSimObserver {

	private Controller _ctrl;
	private boolean _stopped;
	private JToolBar barraBotones;
	private JFileChooser fileChooser;
	private JSpinner ticks;
	private JButton load;
	private JButton changeCo2;
	private JButton changeWeather;
	private JButton b_play;
	private JButton b_stop;
	private JButton cerrar;
	
	private int estado;
	
	
	public ControlPanel(Controller _ctrl) {
		super();
		//FlowLayout ff = new FlowLayout(FlowLayout.LEFT);
		barraBotones = new JToolBar();// BorderLayout.WEST ?									new FlowLayout(FlowLayout.LEFT)
		fileChooser = new JFileChooser("C:\\hlocal\\workspace (programacion)\\GitHub\\TrafficSimulatorTP\\resources\\examples");//"C:\\hlocal\\workspace (programacion)\\GitHub\\TrafficSimulatorTP\\resources\\examples");//esto en el ordenador de adri
		//fileChooser.setCurrentDirectory(new File("/Temp/"));
		ticks = new JSpinner(new SpinnerNumberModel(1, 1, 500, 5)); // value, min, max, step
		load = new JButton();
		changeCo2 = new JButton();
		changeWeather = new JButton();
		b_play = new JButton();
		b_stop = new JButton();
		cerrar = new JButton();

		// atibuto roadMap que se actualice cuando el modelo cambie (min 24:25)
		// tiempo :actual + num ticks o algo asi
		this._ctrl = _ctrl;
		_ctrl.addObserver(this);//esto tiene que estar en todads las clases de la vista...
		// clases...
		
		this._stopped = false;
		initGUI();
	}

	private void initGUI() {
		
		this.add(barraBotones); //BorderLayout.WEST ?
		
		/* BOTONES: Carga del fichero de eventos */
		ImageIcon icono1 = new ImageIcon("resources/icons/open.png");
		load.setIcon(icono1);
		load.setToolTipText("Guapeton cargame el fichero que tu quieras");
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int returnValue = fileChooser.showOpenDialog(fileChooser); // TODO revisar
				if (returnValue == fileChooser.APPROVE_OPTION) {
					try {
						Main.set_inFile(fileChooser.getSelectedFile().getName());//esto es necesario? no lo creo
						_ctrl.reset();
						_ctrl.loadEvents(new FileInputStream(fileChooser.getSelectedFile())); // revisar
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error al abrir archivo");
					}
				}
			}
		});

		/* BOTONES: Cambio de la clase de contaminación de un vehículo */
		ImageIcon icono2 = new ImageIcon("resources/icons/co2class.png");
		changeCo2.setIcon(icono2);		
		changeCo2.setToolTipText("Guapeton cambia de la clase de contaminación de un vehículo");
		changeCo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//minuto 40
				
				
				JDialog changeCo2Dialogo = new JDialog();
				changeCo2Dialogo.setSize(400,400);
				
				JPanel panelTexto = new JPanel();
				panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
				panelTexto.add(new JLabel ("Selecciona un evento para cambiar la clase CO2 de un vehiculo  a partir de un numero de ticks que tu selecciones desde ahora."));
				changeCo2Dialogo.add(panelTexto);
				
				JPanel panelContenido = new JPanel();
				panelTexto.add( panelContenido);
				
							
				Dimension di = new Dimension(80, 20);
								
				panelContenido.add( new JLabel ("Vehicle: "));
				JComboBox vehicleBox = new JComboBox();
				vehicleBox.setPreferredSize(di);
				panelContenido.add(vehicleBox);
				
				panelContenido.add(Box.createGlue());	//PARA SEPARAR
				panelContenido.add(Box.createGlue());
				
				panelContenido.add( new JLabel ("CO2 Class: "));
				JComboBox Co2Box = new JComboBox();
				Co2Box.setPreferredSize(di);
				panelContenido.add(Co2Box);
				
				panelContenido.add(Box.createGlue());	//PARA SEPARAR
				panelContenido.add(Box.createGlue());
				
				panelContenido.add( new JLabel ("Ticks: "));
				JSpinner ticksSpinner = new JSpinner();
				ticksSpinner.setPreferredSize(di);
				panelContenido.add(ticksSpinner);
				
				JPanel panelBotones = new JPanel();
				panelContenido.add(panelBotones);
				
				JButton cancel = new JButton("Cancel");
				panelBotones.add(cancel);
				cancel.setActionCommand("Cancel");
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (e.getActionCommand().equals("Cancel")) { 									
								//JOptionPane.showMessageDialog(null, "Se ha pulsado cancelar.");
								changeCo2Dialogo.setVisible(false);
								estado =0;
						}					
						
					}
				});
				
				JButton ok = new JButton("Ok");
				panelBotones.add(ok);
				ok.setActionCommand("Ok");
				ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (e.getActionCommand().equals("Ok")) {			
								//JOptionPane.showMessageDialog(null, "Se ha seleccionado ok ");
								changeCo2Dialogo.setVisible(false);
								estado=1;
						} 
					}
				});
				
				changeCo2Dialogo.setVisible(true);
				//metodo que diga si es ok/cancel  (ya esta aunque se puee mejorar, aunque no se si vale)
				//otro apra obtener el vehl
				//otro para la co2..
				
				/*				
				if (returnValue == 1) { // ==1
					try {
						List<Pair<String, Integer>> cs = new ArrayList<>();
						Event newCo2Event = new NewSetContClassEvent(returnValue, cs);
						// y a eso le sumamos el resultado de coger el vehiculo y la clase de
						// contaminacion
						// necesitamos un metodo para saber cque se seleciona en el combo box en las
						// clases correspondientes para las tres cosas
						// añadir lo que dice en el min 44
						// 49
						
					} catch (Exception ex) {

					}
				}*/
			}
		});
	
		/* BOTONES: Cambio de las condiciones atmosféricas de una carretera */
		ImageIcon icono3 = new ImageIcon("resources/icons/weather.png");
		changeWeather.setIcon(icono3);
		changeWeather.setToolTipText("Guapeton cambia de la clase de contaminación de un vehículo");
		changeWeather.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // TODO terminar

			}
		});

		/* BOTONES: Ticks */
		JLabel textu = new JLabel("Ticks: ");		//TODO ationPerfomance ??
		Dimension d = new Dimension(80, 40);
		ticks.setMaximumSize(d);
		ticks.setMinimumSize(d);
		ticks.setPreferredSize(d);

		/* BOTONES: Ejecución */
		ImageIcon icono4 = new ImageIcon("resources/icons/run.png");
		b_play.setIcon(icono4);
		b_play.setToolTipText("Guapeton que empiece el juego");
		b_play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // TODO revisar
				load.setEnabled(false);
				changeCo2.setEnabled(false);
				changeWeather.setEnabled(false);
				int n = Integer.parseInt(ticks.getValue().toString());
				run_sim(n);
				_stopped = false;
			}

		});

		/* BOTONES: Parada */
		ImageIcon icono5 = new ImageIcon("resources/icons/stop.png");
		b_stop.setIcon(icono5);
		b_stop.setToolTipText("Guapeton para un poco");
		b_stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stop();
			}
		});

		/* BOTONES: Cerrar */
		ImageIcon icono6 = new ImageIcon("resources/icons/exit.png");
		cerrar.setIcon(icono6);
		cerrar.setToolTipText("Guapeton cierra el programa");
		cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showOptionDialog(null, "Seguro que quieres salir?", "Exit", 0, 0, null, null, null);

				if (JOptionPane.NO_OPTION == 1) { // TODO CAMBIAR QUE ESTO NO VA ASI, pero dejarlo por ahora para
													// cerrar el programa mas facilmente
					System.exit(0);
				}
			}
		});

		// LOS AÑADIMOS
		barraBotones.add(load);
		barraBotones.add(changeCo2);
		barraBotones.add(changeWeather);
		barraBotones.add(b_play);
		barraBotones.add(b_stop);
		barraBotones.add(textu);
		barraBotones.add(ticks);
		// Para darle el efecto de separar los botones
		barraBotones.add(Box.createGlue());
		barraBotones.addSeparator();
		barraBotones.add(cerrar);

	}
	

	
	private void run_sim(int n) {
		if (n > 0 && !_stopped) {
			try {
				_ctrl.run(1, null);
			} catch (Exception e) {
				// TODO show error message
				_stopped = true;
				return;
			}
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					run_sim(n - 1);
				}
			});
		} else {
			enableToolBar(true);
			_stopped = true;
		}
	}

	private void enableToolBar(boolean b) {
		load.setEnabled(b);
		changeCo2.setEnabled(b);
		changeWeather.setEnabled(b);
		b_play.setEnabled(b);
		b_stop.setEnabled(b);
	}

	private void stop() {
		_stopped = true;
	}

//	private void mostrarErrorArchivo() {	//atuch inventions
//		JOptionPane dialog = new JOptionPane();  
//		dialog.showMessageDialog(null, "Error al abrir el archivo");
//	}
	

	
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
