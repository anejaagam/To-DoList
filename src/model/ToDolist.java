package model;

import Exceptions.TooManyThingsToDo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ToDolist implements Loadable, Saveable {
    static Scanner scanner = new Scanner(System.in);
    public List<RegularItem> regularItems = new ArrayList<>();
    public List<UrgentItem> urgentItems = new ArrayList<>();
    public List<Item> doneItems = new ArrayList<>();

    public ToDolist() throws IOException {
            load();
    }



    public void remove(int index){
        if(index > urgentItems.size()){
            index = index - urgentItems.size();
            this.doneItems.add(regularItems.get(index));
            this.regularItems.remove(index);
        }else {
           // this.doneItems.add(urgentItems.get(index));
           // this.urgentItems.remove(index);
        }


    }

    public String ListIterator(){
        int i = 0;
        String string = null;

        for (RegularItem regularItem : regularItems) {
            string = i + ". " + regularItem.getItemName();

            i++;
            System.out.println(string);
            return string;

        }
        return string;
    }

    public String DoneList(){
        for (Item doneRegularItem : doneItems) {
            return doneRegularItem.getItemName();
        }
        return null;
    }


    @Override
    public void save() {
        PrintWriter writer = null;
        PrintWriter writer2 = null;
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
    }

    @Override
    public void load(){


        try {
            String DELIMITER = " ";
            BufferedReader br = Files.newBufferedReader(Paths.get("Regulardata.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(DELIMITER, 2);
                String itemname = lines[0];
                int itemdate = Integer.parseInt(lines[1]);
                regularItems.add(new RegularItem(itemname,itemdate));
            } br.close();
            BufferedReader bt = Files.newBufferedReader(Paths.get("Urgentdata.txt"));
            String line2;
            while ((line2 = br.readLine()) != null) {
                String[] lines2 = line2.split(DELIMITER, 2);
                String itemname2 = lines2[0];
                int itemdate2 = Integer.parseInt(lines2[1]);
                urgentItems.add(new UrgentItem(itemname2,itemdate2,true));
            } bt.close();
        } catch (IOException e) {
            File newFile = new File("Regulardata.txt");
            try {
                BufferedWriter write = new BufferedWriter(new FileWriter(newFile));
                write.write("");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            File newFile2 = new File("Urgentdata.txt");
            try {
                BufferedWriter write2 = new BufferedWriter(new FileWriter(newFile2));
                write2.write("");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }

    public void addItemToDo(String item, int date, String urgent) throws TooManyThingsToDo{
        if (urgentItems.size() + regularItems.size() == 10){
            throw new TooManyThingsToDo();
        }
        if(urgent.equals("Y")){
            UrgentItem urgentItem = new UrgentItem(item,date,true);
            if(!(urgentItem.Location.contains(this))){
                urgentItem.Location(this);
            }
            urgentItems.add(urgentItem);
        }
        else if (urgent.equals("N")){
            RegularItem regularItem = new RegularItem(item,date);
            if(!(regularItem.Location.contains(this))){
                regularItem.Location(this);
            }
            regularItems.add(regularItem);
        }

    }


}
