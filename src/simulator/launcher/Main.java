package simulator.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import exceptions.NegativeException;
import exceptions.StatusException;
import simulator.control.Controller;
import simulator.factories.Builder;
import simulator.factories.BuilderBasedFactory;
import simulator.factories.Factory;
import simulator.factories.MostCrowdedStrategyBuilder;
import simulator.factories.MoveAllStrategyBuilder;
import simulator.factories.MoveFirstStrategyBuilder;
import simulator.factories.NewCityRoadEventBuilder;
import simulator.factories.NewInterCityRoadEventBuilder;
import simulator.factories.NewJunctionEventBuilder;
import simulator.factories.NewVehicleEventBuilder;
import simulator.factories.RoundRobinStrategyBuilder;
import simulator.factories.SetContClassEventBuilder;
import simulator.factories.SetWeatherEventBuilder;
import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;
import simulator.model.TrafficSimulator;
import simulator.view.MainWindow;

public class Main {

	private final static Integer _timeLimitDefaultValue = 10;
	private static String _inFile = null;
	

	private static String _outFile = null;
	private static Factory<Event> _eventsFactory = null;
	private static Integer ticks = _timeLimitDefaultValue;

	private static void parseArgs(String[] args) {

		// define the valid command line options
		Options cmdLineOptions = buildOptions();

		// parse the command line as provided in args
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseHelpOption(line, cmdLineOptions);
			parseInFileOption(line);
			parseOutFileOption(line);
			parseTicksOption(line);
			parseModeOption(line);

			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			//
			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}

	private static Options buildOptions() { 
		Options cmdLineOptions = new Options();

		cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Events input file").build());
		cmdLineOptions.addOption(Option.builder("o").longOpt("output").hasArg().desc("Output file, where reports are written.").build());
		cmdLineOptions.addOption(Option.builder("t").longOpt("ticks").hasArg().desc("Ticks de la simulacion.").build());
		cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message").build());
		cmdLineOptions.addOption(Option.builder("m").longOpt("mode").desc("Mode de la simulacion.").build());
		
		return cmdLineOptions;
	}

	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	private static void parseInFileOption(CommandLine line) throws ParseException {
		_inFile = line.getOptionValue("i");
		if (_inFile == null) {
			throw new ParseException("An events file is missing");
		}
	}

	private static void parseOutFileOption(CommandLine line) throws ParseException {
		_outFile = line.getOptionValue("o");
	}

	private static void parseTicksOption(CommandLine line) {
		String p;
		p = line.getOptionValue("t");
		if(p == null) {
			ticks = _timeLimitDefaultValue;
		}else {
			
			ticks = Integer.parseInt(line.getOptionValue("t"));
		}
		
	}
	
	private static void parseModeOption(CommandLine line) throws ParseException {	//TODO revisar, lo he añadido en parseArgs y en buildOptions
		String mode = line.getOptionValue("m");
		
		if(mode == null) {
			mode="gui";
		}else {//console
			mode = line.getOptionValue("m");
		}
	}
	private static void initFactories() {
        // TODO complete this method to initialize _eventsFactory
        // poner las ppt de 79, 81, 89 -> crear mas builders

        ArrayList<Builder<LightSwitchingStrategy>> lsbs = new ArrayList<>();
        lsbs.add( new RoundRobinStrategyBuilder() );
        lsbs.add( new MostCrowdedStrategyBuilder() );
        Factory<LightSwitchingStrategy> lssFactory = new BuilderBasedFactory<>(lsbs);

        ArrayList<Builder<DequeuingStrategy>> dqbs = new ArrayList<>();
        dqbs.add( new MoveFirstStrategyBuilder() );
        dqbs.add( new MoveAllStrategyBuilder() );
        Factory<DequeuingStrategy> dqsFactory = new BuilderBasedFactory<>(dqbs); 

        ArrayList<Builder<Event>> ebs = new ArrayList<>();
        ebs.add( new NewJunctionEventBuilder(lssFactory,dqsFactory) );
        ebs.add( new NewCityRoadEventBuilder());
        ebs.add( new NewInterCityRoadEventBuilder());
        ebs.add( new SetContClassEventBuilder());
        ebs.add( new NewVehicleEventBuilder());
        ebs.add( new SetWeatherEventBuilder());
        _eventsFactory = new BuilderBasedFactory<>(ebs); 

    }

	private static void startBatchMode() throws IOException, NegativeException, StatusException {
		// TODO complete this method to start the simulation
		// crear iputstream
		InputStream is = new FileInputStream(new File(_inFile));
//		OutputStream os = null;
//		
//		if(_outFile == null) {
//			os= System.out;
//		}else {
//			os= new FileOutputStream(new File(_outFile));
//		}
		
		TrafficSimulator sim = new TrafficSimulator();
		Controller cont = new Controller(sim, _eventsFactory);
		cont.loadEvents(is);
		cont.run(ticks, null);
		is.close();		
	}

	private static void startGUIMode() throws NegativeException, FileNotFoundException  {	// TODO revisar
		InputStream is = new FileInputStream(new File(_inFile));
		TrafficSimulator sim = new TrafficSimulator();
		Controller cont = new Controller(sim, _eventsFactory);
		cont.loadEvents(is);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow(cont);
			}
		});

	}
	
	private static void start(String[] args) throws IOException, NegativeException, StatusException {
		initFactories();
		parseArgs(args);
		startBatchMode();
		startGUIMode();//TODO revisar (el punto 3)
	}

	// example command lines:
	//
	// -i resources/examples/ex1.json
	// -i resources/examples/ex1.json -t 300
	// -i resources/examples/ex1.json -o resources/tmp/ex1.out.json
	// --help

	public static void main(String[] args) {
		try {
			start(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO revisar
	public static void set_inFile(String _inFile) {
        Main._inFile = _inFile;
    }

    public static void set_outFile(String _outFile) {
        Main._outFile = _outFile;
    }

}
