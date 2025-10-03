package helio.parser;

import helio.command.*;

public class Parser {
    public static Command parse(String input) {
        String trimmed = input.trim();
        String[] parts = trimmed.split("\\s+", 2);
        String cmd = parts[0];
        String args = parts.length > 1 ? parts[1] : "";

        switch (cmd) {
        case "bye":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(args);
        case "unmark":
            return new UnmarkCommand(args);
        case "todo":
            return new AddTodoCommand(args);
        case "deadline":
            return new AddDeadlineCommand(args);
        case "event":
            return new AddEventCommand(args);
        case "delete":
            return new DeleteCommand(args);
        case "on":
            return new OnDateCommand(args);
        case "find":
            return new FindCommand(args);
        default:
            return new UnknownCommand(cmd);
        }
    }
}
