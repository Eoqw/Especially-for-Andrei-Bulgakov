import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", "XPS 13", 8, 256, "Windows 10", "Silver"));
        laptops.add(new Laptop("Apple", "MacBook Air", 8, 512, "macOS", "Gold"));
        laptops.add(new Laptop("HP", "Pavilion", 16, 1024, "Windows 11", "Black"));
        laptops.add(new Laptop("Lenovo", "ThinkPad X1", 8, 512, "Windows 10", "Black"));
        laptops.add(new Laptop("Asus", "ZenBook", 16, 256, "Windows 10", "Blue"));

        Scanner scanner = new Scanner(System.in);
        Map<String, Object> criteria = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int criterion = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (criterion) {
            case 1:
                System.out.print("Введите минимальное значение ОЗУ (ГБ): ");
                int minRam = scanner.nextInt();
                criteria.put("ram", minRam);
                break;
            case 2:
                System.out.print("Введите минимальный объем ЖД (ГБ): ");
                int minHdd = scanner.nextInt();
                criteria.put("hdd", minHdd);
                break;
            case 3:
                System.out.print("Введите операционную систему: ");
                String os = scanner.nextLine();
                criteria.put("os", os);
                break;
            case 4:
                System.out.print("Введите цвет: ");
                String color = scanner.nextLine();
                criteria.put("color", color);
                break;
            default:
                System.out.println("Неверный критерий.");
                return;
        }

        Set<Laptop> filteredLaptops = filterLaptops(laptops, criteria);
        System.out.println("Ноутбуки, соответствующие критериям:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }

    public static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, Object> criteria) {
        Set<Laptop> filteredLaptops = new HashSet<>();

        for (Laptop laptop : laptops) {
            boolean matches = true;

            if (criteria.containsKey("ram") && laptop.getRam() < (int) criteria.get("ram")) {
                matches = false;
            }
            if (criteria.containsKey("hdd") && laptop.getHdd() < (int) criteria.get("hdd")) {
                matches = false;
            }
            if (criteria.containsKey("os") && !laptop.getOs().equalsIgnoreCase((String) criteria.get("os"))) {
                matches = false;
            }
            if (criteria.containsKey("color") && !laptop.getColor().equalsIgnoreCase((String) criteria.get("color"))) {
                matches = false;
            }

            if (matches) {
                filteredLaptops.add(laptop);
            }
        }

        return filteredLaptops;
    }
}