import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.CREATE;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
public class ProductWriter {
    public static void main(String[] args) {

        String ID = "";
        String name = "";
        String description = "";
        Double cost;
        String csvRec = "";
        boolean done = false;

        Scanner in = new Scanner(System.in);
        ArrayList<String> recs = new ArrayList<>();

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID");
            description = SafeInput.getNonZeroLenString(in, "Enter the name");
            ID = SafeInput.getNonZeroLenString(in, "Enter description");
            cost = SafeInput.getRangedDouble(in, "Enter the cost", 100, 9999);

            csvRec = ID + ", " + name + ", " + description + ", " + cost;
            recs.add(csvRec);

            done = SafeInput.getYNConfirm(in, "Are you done?");

        } while (!done);


        try {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!
            String r;
            for (String rec : recs) {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
