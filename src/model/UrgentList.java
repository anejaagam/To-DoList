package model;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class UrgentList extends ToDolist{

    public List<UrgentItem> urgentItems = new ArrayList<>();
    public List<Item> doneItems = new ArrayList<>();

    public UrgentList() throws IOException {
    }

    public void add(UrgentItem urgentItem) {
        urgentItems.add(urgentItem);
    }

    @Override
    public String ListIterator() {

        String string = null;
        for (int j = 0; j < urgentItems.size(); j++) {
           string = j + ". " + urgentItems.get(j).getItemName() + " -PRIORITY";
            return string;
        }
        return string;
    }

    @Override
    public void save() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("Urgentdata.txt", "UTF-8");
            for(UrgentItem urgentItem : urgentItems){
                writer.println(urgentItem.getItemName() + " "+ urgentItem.getDueDate());
            }
            writer.close();
        }
        catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
