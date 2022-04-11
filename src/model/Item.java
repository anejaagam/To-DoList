package model;

import java.util.ArrayList;
import java.util.List;

abstract public class Item {
    public String itemName;
    public int dueDate;
    public boolean priority;
    public List<ToDolist> Location = new ArrayList<>();

    public void setItemName(String name){
        this.itemName = name;
    }
    public String getItemName(){
        return this.itemName;
    }
    public int getDueDate(){
        return this.dueDate;
    }
    public void setDueDate(int date){
        this.dueDate = date;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }
    public void Location(ToDolist toDolist){
        Location.add(toDolist);
    }
}
