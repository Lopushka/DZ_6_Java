import java.util.*;

/**
 * OperNOte
 */
public class OperNote {

    private Set<Book> books = new HashSet<>();
    private List<Criterion> critList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void printL() {
        for (Book book : books) {
            if (bookCor(book)) {
                System.out.println(book);
            }
        }
    }

    public boolean bookCor(Book book) {
        for (Criterion crit : critList) {
            Object valuebook = null;
            if (crit.property.equals("name")) {
                valuebook = book.getName();
            } else if (crit.property.equals("RAM")) {
                valuebook = book.getRAM();
            } else if (crit.property.equals("diskSize")) {
                valuebook = book.getdiskSize();
            } else if (crit.property.equals("OS")) {
                valuebook = book.getOS();
            } else if (crit.property.equals("colour")) {
                valuebook = book.getcolour();
            } else {
                continue;
            }
            if (crit.value != null && !crit.value.equals(valuebook)) {
                return false;
            }
            if (crit.maxValue != null && crit.maxValue < Double.parseDouble(Objects.toString(valuebook))) {
                return false;
            }
            if (crit.minValue != null && crit.minValue > Double.parseDouble(Objects.toString(valuebook))) {
                return false;
            }
        }
        return true;
    }

    public OperNote(Set<Book> books) {
        OperNote.scanner = new Scanner(System.in);
        this.books = books;
    }

    public OperNote(Set<Book> books, List<Criterion> critList) {
        OperNote.scanner = new Scanner(System.in);
        this.books = books;
        this.critList = critList;

    }

    public int getCriterion() {
        String text = "Введите цифру, соответствующую необходимому критерию: ";

        List<String> properties = Filter();

        for (int i = 0; i < properties.size(); i++) {
            text += "\n" + (i + 1) + ". " + getFilter(properties.get(i));
        }

        System.out.println(text);

        int value = scanner.nextInt();

        return value;
    }

    public String getFilter(String property) {

        Map<String, String> descriptionsProperties = descProp();

        return descriptionsProperties.get(property);

    }

    public Map<String, String> descProp() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ноутбук");
        map.put("RAM", "Кол-во RAM:");
        map.put("diskSize", "объём ЖД");
        map.put("OS", "операционная система");
        map.put("colour", "цвет");

        return map;

    }

    public List<String> Filter() {
        List<String> list = new ArrayList<>();
        list.add("name");
        list.add("RAM");
        list.add("diskSize");
        list.add("OS");
        list.add("colour");

        return list;
    }

    public String getOper() {

        String txt = "Choose your destiny: \n " +
                "1. Добавить критерий поиска \n " +
                "2. Результат поиска \n " +
                "3. Завершить";

        System.out.println(txt);

        String a = scanner.next();

        return a;
    }

    public Set<String> Select() {
        Set<String> set = new HashSet<>();
        set.add("RAM");
        set.add("diskSize");
        return set;
    }

    public Set<String> Select1() {
        Set<String> set = new HashSet<>();
        set.add("name");
        set.add("OS");
        set.add("colour");
        return set;
    }

    public void start() {

        boolean flag = true;
        while (flag) {

            String operation = getOper();
            if (operation.equals("3")) {
                flag = false;
                scanner.close();
                continue;
            } else if (operation.equals("1")) {

                int criterion = getCriterion();
                List<String> properties = Filter();
                if (criterion - 1 < 0 || criterion - 1 > properties.size() - 1) {
                    System.out.println("Введено некорректное значение");
                    continue;
                }
                String property = properties.get(criterion - 1);
                Criterion criterionObject = null;
                try {
                    if (Select().contains(property)) {
                        criterionObject = Criterion.startGet(scanner, property, true);
                    } else {
                        criterionObject = Criterion.startGet(scanner, property, false);
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка при выборе критерия");
                    continue;
                }
                if (criterionObject != null) {
                    System.out.println("Критерий добавлен");
                    critList.add(criterionObject);
                }
            } else if (operation.equals("2")) {
                printL();
            }
        }
    scanner.close();
    }
}

class Criterion {
    Object value;
    Double minValue;
    Double maxValue;
    boolean isQuantitative;
    String property;

    public Criterion(String property, boolean isQuantitative, Object value, Double minValue, Double maxValue) {
        this.property = property;
        this.isQuantitative = isQuantitative;
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public static Criterion startGet(Scanner scanner, String property, boolean isQuantitative) {

        if (isQuantitative) {

            String quest = "Выберите тип криетрия: " +
                    "\n 1. Значение" +
                    "\n 2. Меньше" +
                    "\n 3. Больше" +
                    "\n 4. Интервал";
            System.out.println(quest);

            String text = scanner.next();

            Criterion criterion = null;

            if (text.equals("1")) {
                System.out.println("Введите значение поиска: ");
                int getValue = scanner.nextInt();
                criterion = new Criterion(property, isQuantitative, getValue, null, null);
            } else if (text.equals("2")) {
                System.out.println("Введите максимальное предельное значение: ");
                double getValue = scanner.nextDouble();
                criterion = new Criterion(property, isQuantitative, null, null, getValue);
            } else if (text.equals("3")) {
                System.out.println("Введите минимальное предельное значение: ");
                double getValue = scanner.nextDouble();
                criterion = new Criterion(property, isQuantitative, null, getValue, null);
            } else if (text.equals("4")) {
                System.out.println("Введите минимальное предельное значение: ");
                double getMin = scanner.nextDouble();
                System.out.println("Введите максимальное предельное значение: ");
                double getMax = scanner.nextDouble();
                criterion = new Criterion(property, isQuantitative, null, getMin, getMax);
            }

            return criterion;
        }

        System.out.println("Введите значение поиска: ");
        String getValue = scanner.next();
        return new Criterion(property, isQuantitative, getValue, null, null);
    }

}
