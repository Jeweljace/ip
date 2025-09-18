import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private final Path path = Paths.get("data", "helio.txt");

    public int load(Task[] dest) {
        int count = 0;
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            File file = path.toFile();
            if (!file.exists()) {
                return 0;
            }

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                try {
                    Task t = parse(line);
                    if (count < dest.length) {
                        dest[count++] = t;
                    } else {
                        System.out.println("Warning: task array full; skipping remaining lines.");
                        break;
                    }
                } catch (Exception ex) {
                    System.out.println("Skipping corrupted line: " + line);
                }
            }
            sc.close();
        } catch (IOException ioe) {
            System.out.println("Load error: " + ioe.getMessage());
        }
        return count;
    }

    public void save(Task[] src, int count) {
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            FileWriter fw = new FileWriter(path.toFile());
            for (int i = 0; i < count; i++) {
                fw.write(src[i].toSave());
                fw.write(System.lineSeparator());
            }
            fw.close(); // important
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
        if ("T".equals(type)) {
            t = new Todo(desc);
        } else if ("D".equals(type)) {
            if (p.length != 4) throw new IllegalArgumentException("Deadline needs 4 fields");
            t = new Deadline(desc, p[3]);
        } else if ("E".equals(type)) {
            if (p.length != 5) throw new IllegalArgumentException("Event needs 5 fields");
            t = new Event(desc, p[3], p[4]);
        } else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        if (done) t.markAsDone();
        return t;
    }
}
