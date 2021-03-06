package command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CommandFactory {

	private static CommandFactory instance; // Singleton

	private static final Logger logger = LogManager.getLogger(CommandFactory.class);
	
	private static final String ANTECEDENT = "command.Command";

	private CommandFactory() {
		//nothing to do
	}


	public List<String> executeCommand(String commandLigne) {

		String commandName = commandLigne.split(" ")[0];
		String args = "";

		args = commandLigne.replace(commandName + " ", "");

		// Use reflexivity
		try {
			Class<?> commandClass = Class.forName(ANTECEDENT + commandName);
			Command command = (Command) commandClass.getDeclaredConstructor().newInstance();
			return command.execute(args);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			return unexpectedBehavior(commandName, args, e);
		}
	}

	private ArrayList<String> unexpectedBehavior(String commandName, String args, Exception e) {
		String logMsg = String.format("This command does not exist or there is a problem with args, please check the command validity : %n %s %s %n %s",
				commandName, args, e);
		logger.error(logMsg);
		ArrayList<String> result = new ArrayList<>();
		result.add("false");
		result.add(commandName + " this command does not exist with " + args);
		return result;
	}

	/* Singleton pattern */
	public static CommandFactory getInstance() {
		if (instance == null) {
			instance = new CommandFactory();
		}
		return instance;
	}
}
