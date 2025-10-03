package helio.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDateTime;

import helio.task.Task;
import helio.task.Todo;
import helio.task.Deadline;
import helio.task.Event;
import helio.task.TaskList;
import helio.time.DateTimeUtil;

/**
 * Handles persistence of tasks to and from disk.
 * Responsible for:
 * Creating the data directory if it does not exist
 * Loading tasks from a save file
 * Saving tasks to the file after updates
 */
public class Storage {
    private final Path path;

    /**
     * Constructs a storage manager for the given directory and file name.
     *
     * @param dir      the directory containing the save file
     * @param fileName the name of the save file
     */
    public Storage(String dir, String fileName) {
        this.path = Paths.get(dir, fileName);
    }

    /**
     * Loads tasks from the save file into the given {@link TaskList}.
     * If the file does not exist, nothing is loaded. Malformed lines are skipped.
     *
     * @param dest the task list to populate with loaded tasks
     */
    public void load(TaskList dest) {
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            File file = path.toFile();
            if (!file.exists()) {
                return;
            }

            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine().trim();
                    if (line.isEmpty()) {
                        continue;
                    }
                    try {
                        Task t = parse(line);
                        dest.addTask(t);
                    } catch (Exception ex) {
                        System.out.println("Skipping corrupted line: " + line);
                    }
                }
            }
        } catch (IOException ioe) {
            System.out.println("Load error: " + ioe.getMessage());
        }
    }

    /**
     * Saves the given task list to the save file, overwriting any previous contents.
     *
     * @param src the task list to save
     */
    public boolean save(TaskList src) {
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            try (FileWriter fw = new FileWriter(path.toFile())) {
                for (Task t : src.getAll()) {
                    fw.write(t.toSave());
                    fw.write(System.lineSeparator());
                }
            }
            return true;
        } catch (IOException ioe) {
            return false;
        }
    }

    /**
     * Parses a single line from the save file into a {@link Task}.
     * Expected formats:
     * Todo: {@code T | doneFlag | description}
     * Deadline: {@code D | doneFlag | description | date/time | hasTimeFlag}
     * Event: {@code E | doneFlag | description | from | to | hasTimeFrom | hasTimeTo}
     *
     * @param line a line from the save file
     * @return the parsed task
     * @throws IllegalArgumentException if the line is malformed or type is unknown
     */
    private Task parse(String line) {
        String[] p = line.split("\\s*\\|\\s*");
        if (p.length < 3) {
            throw new IllegalArgumentException("Too few fields");
        }
        String type = p[0];
        boolean done = "1".equals(p[1]);
        String desc = p[2];

        Task t;
        switch (type) {
        case "T":
            t = new Todo(desc);
            break;
        case "D":
            if (p.length != 5) {
                throw new IllegalArgumentException("Deadline needs 5 fields");
            }
            LocalDateTime by = DateTimeUtil.parseDateTimeFlexible(p[3]);
            boolean hasTime = "1".equals(p[4]);
            t = new Deadline(desc, by, hasTime);
            break;
        case "E":
            if (p.length != 7) throw new IllegalArgumentException("Event needs 7 fields");
            LocalDateTime from = DateTimeUtil.parseDateTimeFlexible(p[3]);
            LocalDateTime to = DateTimeUtil.parseDateTimeFlexible(p[4]);
            boolean hasFrom = "1".equals(p[5]);
            boolean hasTo = "1".equals(p[6]);
            t = new Event(desc, from, to, hasFrom, hasTo);
            break;
        default:
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        if (done) {
            t.markAsDone();
        }
        return t;
    }
}
