package helio.parser;

import helio.command.*;

/**
 * Parses raw user input strings into executable {@link Command} objects.
 * The parser reads the first word of input as the command word and passes
 * the rest of the line as arguments. Unknown commands are wrapped in
 * {@link UnknownCommand}.
 */
public class Parser {
    /**
     * Parses a raw user input string into a {@link Command}.
     *
     * @param input the full line entered by the user
     * @return a {@link Command} representing the requested action
     */
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
