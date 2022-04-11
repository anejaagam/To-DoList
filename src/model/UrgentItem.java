package model;

public class UrgentItem extends Item {
    public UrgentItem(String name, int date, boolean Priority){
        setItemName(name);
        setDueDate(date);
        setPriority(Priority);

    }
}
