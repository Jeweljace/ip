package helio.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import helio.task.Task;
import helio.task.Todo;
import helio.task.Deadline;
import helio.task.Event;
import helio.task.TaskList;

public class Storage {
    private final Path path;

    public Storage(String dir, String fileName) {
        this.path = Paths.get(dir, fileName);
    }

    public void load(TaskList dest) {
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            File file = path.toFile();
            if (!file.exists()) return;

            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine().trim();
                    if (line.isEmpty()) continue;
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

    public void save(TaskList src) {
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
        } catch (IOException ioe) {
            System.out.println("Save error: " + ioe.getMessage());
        }
    }

    private Task parse(String line) {
        String[] p = line.split("\\s*\\|\\s*");
        if (p.length < 3) throw new IllegalArgumentException("Too few fields");
        String type = p[0];
        boolean done = "1".equals(p[1]);
        String desc = p[2];

        Task t;
        switch (type) {
        case "T":
            t = new Todo(desc);
            break;
        case "D":
            if (p.length != 4) throw new IllegalArgumentException("Deadline needs 4 fields");
            t = new Deadline(desc, p[3]);
            break;
        case "E":
            if (p.length != 5) throw new IllegalArgumentException("Event needs 5 fields");
            t = new Event(desc, p[3], p[4]);
            break;
        default:
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        if (done) t.markAsDone();
        return t;
    }
}
