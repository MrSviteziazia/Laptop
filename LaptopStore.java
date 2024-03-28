import java.util.*;

class Laptop {
    private String brand;
    private int ram;
    private int storage;
    private String os;
    private String color;
    
    public Laptop(String brand, int ram, int storage, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public int getRam() {
        return ram;
    }
    public void setRam(int ram) {
        this.ram = ram;
    }
    public int getStorage() {
        return storage;
    }
    public void setStorage(int storage) {
        this.storage = storage;
    }
    public String getOs() {
        return os;
    }
    public void setOs(String os) {
        this.os = os;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class LaptopStore {
    private Set<Laptop> laptops = new HashSet<>();
    
    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }
    public void filterLaptops(Map<String, Object> filters) {
        List<Laptop> filteredLaptops = new ArrayList<>();
        for (Laptop laptop : laptops) {
            boolean passFilter = true;
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                switch (key) {
                    case "brand":
                        if (!laptop.getBrand().equalsIgnoreCase((String) value)) {
                            passFilter = false;
                        }
                        break;
                    case "ram":
                        if (laptop.getRam() < (int) value) {
                            passFilter = false;
                        }
                        break;
                    case "storage":
                        if (laptop.getStorage() < (int) value) {
                            passFilter = false;
                        }
                        break;
                    case "os":
                        if (!laptop.getOs().equalsIgnoreCase((String) value)) {
                            passFilter = false;
                        }
                        break;
                    case "color":
                        if (!laptop.getColor().equalsIgnoreCase((String) value)) {
                            passFilter = false;
                        }
                        break;
                    default:
                        System.out.println("Invalid filter key: " + key);
                        break;
                }
                if (!passFilter) {
                    break;
                }
            }
            if (passFilter) {
                filteredLaptops.add(laptop);
            }
        }
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }
    public static void main(String[] args) {
        LaptopStore store = new LaptopStore();
        store.addLaptop(new Laptop("Dell", 8, 512, "Windows", "Silver"));
        store.addLaptop(new Laptop("HP", 16, 256, "Linux", "Black"));
        store.addLaptop(new Laptop("Apple", 16, 1024, "macOS", "Silver"));
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filters = new HashMap<>();
        System.out.println("Введите критерии фильтрации:");
        System.out.println("1 - Бренд");
        System.out.println("2 - Оперативная память (RAM)");
        System.out.println("3 - Объем накопителя (ЖД)");
        System.out.println("4 - Операционная система");
        System.out.println("5 - Цвет");
        System.out.println("Введите номер критерия:");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Введите бренд:");
                String brand = scanner.next();
                filters.put("brand", brand);
                break;
            case 2:
                System.out.println("Введите минимальный объем оперативной памяти(RAM):");
                int ram = scanner.nextInt();
                filters.put("ram", ram);
                break;
            case 3:
                System.out.println("Введите минимальный объем накопителя(ГБ):");
                int storage = scanner.nextInt();
                filters.put("storage", storage);
                break;
            case 4:
                System.out.println("Введите операционную систему:");
                String os = scanner.next();
                filters.put("os", os);
                break;
            case 5:
                System.out.println("Введите цвет:");
                String color = scanner.next();
                filters.put("color", color);
                break;
            default:
                System.out.println("Неверный выбор критерия.");
                break;
        }
        scanner.close();
        store.filterLaptops(filters);
    }
}