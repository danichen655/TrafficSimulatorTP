package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import simulator.control.Controller;

public class MainWindow extends JFrame {
	private Controller _ctrl;

	public MainWindow(Controller ctrl) {
		super("Traffic Simulator");
		_ctrl = ctrl;
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		mainPanel.add(new ControlPanel(_ctrl), BorderLayout.PAGE_START);
		mainPanel.add(new StatusBar(_ctrl),BorderLayout.PAGE_END);
		JPanel viewsPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(viewsPanel, BorderLayout.CENTER);
		JPanel tablesPanel = new JPanel();
		tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.Y_AXIS));//		Y_AXIS=columna
		viewsPanel.add(tablesPanel);
		JPanel mapsPanel = new JPanel();
		mapsPanel.setLayout(new BoxLayout(mapsPanel, BoxLayout.Y_AXIS));
		viewsPanel.add(mapsPanel);
		// tables
		JPanel eventsView = createViewPanel(new JTable(new EventsTableModel(_ctrl)), "Events");//titulo del borde negro que rodea la tabla, se hace en borderFactoy, se implementa en createViewPanel, menos los botones casi todo lleva bordes!
		eventsView.setPreferredSize(new Dimension(500, 200));
		tablesPanel.add(eventsView);	//el add es para poder verlo
		// add other tables
		JPanel vehiclesView = createViewPanel(new JTable(new VehiclesTableModel(_ctrl)), "Vehicles");//titulo del borde negro que rodea la tabla, se hace en borderFactoy, se implementa en createViewPanel, menos los botones casi todo lleva bordes!
		vehiclesView.setPreferredSize(new Dimension(500, 200));
		tablesPanel.add(vehiclesView);
		
		JPanel roadsView = createViewPanel(new JTable(new RoadsTableModel(_ctrl)), "Vehicles");//titulo del borde negro que rodea la tabla, se hace en borderFactoy, se implementa en createViewPanel, menos los botones casi todo lleva bordes!
		roadsView.setPreferredSize(new Dimension(500, 200));
		tablesPanel.add(roadsView);
		
		
		JPanel junctionsView = createViewPanel(new JTable(new JunctionssTableModel(_ctrl)), "Vehicles");//titulo del borde negro que rodea la tabla, se hace en borderFactoy, se implementa en createViewPanel, menos los botones casi todo lleva bordes!
		junctionsView.setPreferredSize(new Dimension(500, 200));
		tablesPanel.add(junctionsView);
		// ...
		
		
		// maps
		JPanel mapView = createViewPanel(new MapComponent(_ctrl), "Map");
		mapView.setPreferredSize(new Dimension(500, 400));
		mapsPanel.add(mapView);
		// add a map for MapByRoadComponent
		JPanel mapByRoad = createViewPanel(new MapByRoadComponent(_ctrl), "Map by Road");
		mapByRoad.setPreferredSize(new Dimension(500, 400));
		mapsPanel.add(mapByRoad);
		// ...
		
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	private JPanel createViewPanel(JComponent c, String title) {
		JPanel p = new JPanel(new BorderLayout());
		// TODO add a framed border to p with title	
		//p.setBorder(border);//					ALGO DE BORDERFACTORY
		p.add(new JScrollPane(c));
		return p;
	}
}
