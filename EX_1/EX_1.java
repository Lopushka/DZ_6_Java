import java.util.HashSet;
import java.util.*;

/**
 * EX_1
 */
public class EX_1 {

    public static void main(String[] args) {

        Set<Book> set = new HashSet<>();
        set.add(new Book("Asus", 16, 512, "Win 11", "Pink"));
        set.add(new Book("Lenovo", 8, 1000, "Win 11", "Gold"));
        set.add(new Book("Asus", 32, 1512, "FreeDOS", "Black"));
        set.add(new Book("Aсer", 8, 256, "Linux", "Green"));
        OperNote oper = new OperNote(set);
        oper.start();
    }
}