import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {

       String ID = "";
       String fName = "";
       String lName = "";
       String title = "";
       int YOB = 0;
       String csvRec = "";
       boolean done = false;

       Scanner in = new Scanner(System.in);

       ArrayList<String> recs = new ArrayList<>();

       do {
       ID = SafeInput.getNonZeroLenString(in, "Enter the ID");
       fName = SafeInput.getNonZeroLenString(in, "Enter the first name");
       lName = SafeInput.getNonZeroLenString(in,"Enter the last name");
       title = SafeInput.getNonZeroLenString(in, "Enter the title");
       YOB = SafeInput.getRangedInt(in, "Enter the year you were born", 1000, 9999);

       csvRec = ID + ", " + fName + ", " + lName + ", " + title + ", " + YOB;
       recs.add(csvRec);

       done = SafeInput.getYNConfirm(in, "Are you done?");

       }while(!done);

       File workingDirectory = new File(System.getProperty("user.dir"));
       Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");


       for(String p: recs)
           System.out.println(p);

       try
       {
          // Typical java pattern of inherited classes
          // we wrap a BufferedWriter around a lower level BufferedOutputStream
          OutputStream out =
                  new BufferedOutputStream(Files.newOutputStream(file, CREATE));
          BufferedWriter writer =
                  new BufferedWriter(new OutputStreamWriter(out));

          // Finally can write the file LOL!

          for(String p : recs)
          {
             csvRec = p.toCSV();
             writer.write(csvRec,0, ID.length());  // stupid syntax for write rec
             writer.newLine();  // adds the new line

          }
          writer.close(); // must close the file to seal it and flush buffer
          System.out.println("Data file written!");
       }
       catch (IOException e)
       {
          e.printStackTrace();
       }
    }

    }
}