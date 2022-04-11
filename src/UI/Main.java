package UI;

import Exceptions.TooManyThingsToDo;
import Network.ReadWebPageEx;
import model.Item;
import model.ToDolist;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static Scanner scanner = new Scanner(System.in);//Taken from B04-LittleCalculatorStarterLecLab-master - LoggingCalculator.java
    static ToDolist toDolist;



    public static void main(String[] args) throws IOException {
        // Create a 1000 X 1000 Frame
        JFrame frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Input");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton enter = new JButton("Enter");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(enter);

        // Text Area at the Center
        JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);

        // Display prompt to user
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        String prompt = "What would you like to do [1] add a to do list item, [2] cross off an item [3] show all the items\n"; // creates a string by getting the text that was entered by the user
        ta.append(prompt);

        try {
            toDolist = new ToDolist();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //toDolist = new ToDolist();
        new ReadWebPageEx();

        // add button functionality
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //must add and define the actionPreformed func
                String label2 = tf.getText() + "\n";

                if (label2.equals("1\n")){
                    ta.append("\n\nEnter the task, due date [DDMMYY], and urgency [Y/N] separated by a space and ending with \"ADD\"");
                }
                else if (label2.equals("2\n")){
                    ta.append("\n\nPlease enter which number to cross off ending with \"REM\":");
                }
                else if (label2.equals("3\n")){
                    ta.append("\n\nTo-Do List: ");
                    ta.append("\n- " + toDolist.ListIterator());

                    ta.append("\n\nDone List: ");
                    ta.append("\n- " + toDolist.DoneList());
                }
                else if (label2.contains("ADD")){
                    String [] addInfo = label2.split(" ");
                    String item = addInfo[0];
                    int date = Integer.parseInt(addInfo[1]);
                    String urgent = addInfo[2];

                    try{
                        toDolist.addItemToDo(item,date,urgent);
                        ta.append("\nItem added");
                        ta.append("\nWhat would you like to do [1] add a to do list item, [2] cross off an item [3] show all the items\n");
                    }catch(TooManyThingsToDo err){
                        System.out.println("You have too many things on your To-Do List. Do some work first.");
                    }
                }
                else if (label2.contains("REM")){
                    String [] remInfo = label2.split(" ");
                    int itemcross = Integer.parseInt(remInfo[0]);
                    
                    toDolist.remove(itemcross);
                    ta.append("\nItem removed");
                    ta.append("\nWhat would you like to do [1] add a to do list item, [2] cross off an item [3] show all the items\n");
                }
            }
        });

        /*
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
        */


        toDolist.save();
    }

}
