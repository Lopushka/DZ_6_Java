import java.util.ArrayList;
import java.util.List;

/**
 * Book
 */
public class Book {

    private String name;
    private int RAM;
    private int diskSize;
    private String OS;
    private String colour;

    public Book(String name, int RAM, int diskSize, String OS, String colour) {
        this.name = name;
        this.RAM = RAM;
        this.diskSize = diskSize;
        this.OS = OS;
        this.colour = colour;
    }

    public boolean validateOdject() {
        return true;
    }

    public static List<String> Filter() {
        List<String> list = new ArrayList<>();
        list.add("RAM");
        list.add("diskSize");
        list.add("OS");
        list.add("colour");
        return list;
    }

    @Override
    public String toString() {
        return "Ноутбук: (" + name + "): " + "кол-во RAM: " + RAM + ", объём ЖД: " + diskSize
                + ",  операционная система: " + OS + ", цвет:" + colour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRAM() {
        return RAM;
    }

    public void getRAM(int RAM) {
        this.RAM = RAM;
    }

    public int getdiskSize() {
        return diskSize;
    }

    public void getdiskSize(int diskSize) {
        this.diskSize = diskSize;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getcolour() {
        return colour;
    }

    public void setcolour(String colour) {
        this.colour = colour;
    }
}