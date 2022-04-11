package UI;

import Exceptions.TooManyThingsToDo;
import Network.ReadWebPageEx;
import model.Item;
import model.ToDolist;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);//Taken from B04-LittleCalculatorStarterLecLab-master - LoggingCalculator.java
    static ToDolist toDolist;



    public static void main(String[] args) throws IOException {
        try {
            toDolist = new ToDolist();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //toDolist = new ToDolist();
        new ReadWebPageEx();
        for (int i = 0; i < 2; i++) {
            System.out.println("What would you like to do [1] add a to do list item, [2] cross off an item [3] show all the items [4] Exit");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("What would you like to add. Press Enter, if nothing.");
                String item = scanner.next();
                System.out.println("What is the due date? DDMMYY");
                int date = scanner.nextInt();
                System.out.println("Is this Item Urgent? [Y/N]");
                String urgent = scanner.next();
                try{
                    toDolist.addItemToDo(item,date,urgent);
                }catch(TooManyThingsToDo e){
                    System.out.println("You have too many things on your To-Do List. Do some work first.");
                }

                i = 0;
            }
            if (choice == 2) {
                System.out.println("Please choose which number to cross off");
                System.out.println(toDolist.ListIterator());
                int itemcross = scanner.nextInt();
                toDolist.remove(itemcross);
                i = 0;
            }
            if (choice == 3) {
                System.out.println("To-do List:");
                System.out.println(toDolist.ListIterator());
                System.out.println("Done List");
                toDolist.DoneList();
                i = 0;
            }
            if (choice == 4) {
                toDolist.save();
                i = 2;
            }

        }



    }

}
