package Tests;

import Exceptions.TooManyThingsToDo;
import model.Item;
import model.RegularItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import model.ToDolist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDolistTest {
    List<RegularItem> regularItems = new ArrayList<>();
    RegularItem some;
    ToDolist toDolist;

    {
        try {
            toDolist = new ToDolist();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeAll
    static void beforeAll() throws IOException {
        
 {
 }
    }

    @Test
    void remove() {
        try {
            ToDolist toDolist1 = new ToDolist();
            toDolist1.remove(0);
            assertTrue(toDolist1.regularItems.isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void listIterator() {



    }

    @Test
    void doneList() {

    }

    @Test
    void addItemToDo() {


        try {
            toDolist.addItemToDo("some",12345,"N");

            assertFalse(toDolist.regularItems.isEmpty());
        } catch (TooManyThingsToDo e) {
            e.printStackTrace();
        }
       


    }
}