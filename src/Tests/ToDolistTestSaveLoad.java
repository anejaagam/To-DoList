package Tests;

import model.Item;
import model.RegularItem;
import model.UrgentItem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//import static org.junit.jupiter.api.Assertions.*;

class ToDolistTestSaveLoad {
    public List<RegularItem> regularItems = new ArrayList<>();
    public List<UrgentItem> urgentItems = new ArrayList<>();
    public List<Item> doneItems = new ArrayList<>();
    //@org.junit.jupiter.api.BeforeEach
    void setUp() {
        regularItems.add(new RegularItem("name",22222));
        urgentItems.add(new UrgentItem("Everything",22222,true));
    }

    //@org.junit.jupiter.api.Test
    void save() {
        regularItems.add(new RegularItem("name",22222));
        PrintWriter writer = null;

        try {
            writer = new PrintWriter("Regulardata.txt", "UTF-8");
            for(RegularItem regularItem : regularItems){
                writer.println(regularItem.getItemName() + " "+ regularItem.getDueDate());
            }
            writer.close();
        }

        catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            String DELIMITER = " ";
            BufferedReader br = Files.newBufferedReader(Paths.get("Regulardata.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(DELIMITER, 2);
                String itemname = lines[0];
                int itemdate = Integer.parseInt(lines[1]);
                //assertEquals(itemdate,22222);
               // assertEquals(itemname,"name");
            } br.close();
        } catch (IOException e) {
            File newFile = new File("Regulardata.txt");
            try {
                BufferedWriter write = new BufferedWriter(new FileWriter(newFile));
                write.write("");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    //@org.junit.jupiter.api.Test
    void load() {
        try {
            String DELIMITER = " ";
            BufferedReader br = Files.newBufferedReader(Paths.get("Regulardata.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(DELIMITER, 2);
                String itemname = lines[0];
                int itemdate = Integer.parseInt(lines[1]);
                //assertEquals(itemdate,22222);
                //assertEquals(itemname,"name");
            } br.close();

        } catch (IOException e) {
            File newFile = new File("Regulardata.txt");
            try {
                BufferedWriter write = new BufferedWriter(new FileWriter(newFile));
                write.write("");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}