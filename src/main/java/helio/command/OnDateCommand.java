package helio.command;

import java.time.LocalDate;

import helio.ui.Ui;
import helio.storage.Storage;
import helio.task.*;
import helio.time.DateTimeUtil;

public class OnDateCommand extends Command {
    private final String args;

    public OnDateCommand(String args) {
        this.args = args == null ? "" : args.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (args.isEmpty()) {
            ui.showError("Usage: on <yyyy-MM-dd>");
            return;
        }
        try {
            LocalDate query = DateTimeUtil.parseDate(args);
            boolean any = false;
            System.out.println("Tasks on " + DateTimeUtil.formatDate(query) + ":");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.getTask(i);
                if (t instanceof Deadline) {
                    if (((Deadline) t).getBy().equals(query)) {
                        System.out.println((i + 1) + ". " + t);
                        any = true;
                    }
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    LocalDate start = e.getFrom().toLocalDate();
                    LocalDate end = e.getTo().toLocalDate();
                    if (!query.isBefore(start) && !query.isAfter(end)) {
                        System.out.println((i + 1) + ". " + t);
                        any = true;
                    }
                }
            }
            if (!any) {
                System.out.println("Meowww you have nothing planned for this date! Time to snooze:>");
            }
        } catch (IllegalArgumentException ex) {
            ui.showError(ex.getMessage());
        }
    }
}
