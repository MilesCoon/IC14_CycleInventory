import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Initialize a count at 0
        int count = 0;
        // Initialize all necessary fields
        String manufacturer;
        double price, fuelCapacity;
        int displacement, frameSize, cranks;
        // Initialize Scanner for inputs
        Scanner keyboard = new Scanner(System.in);
        // Initialize array inventory
        Cycle[] inventory = new Cycle[10];
        // Open file and write everything into the array
        File BINARY_FILE = new File("Cycle_Inventory.dat");
        if (BINARY_FILE.exists() && BINARY_FILE.length() > 0) {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(BINARY_FILE));
                inventory = (Cycle[]) fileReader.readObject();
                for (Cycle c : inventory) {
                    if (c != null) {
                        count++;
                        System.out.println(c);
                    } else {
                        break;
                    }
                }
                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
        // Print no data if array is empty after recording file into array
        if (count == 0) {
            System.err.println("[No data.  Please enter cycles into inventory.]");
        }
        int option;
        do {
            Thread.sleep(100);
            System.out.println("\n******* Options Menu *******");
            System.out.println("Enter (1) to add a Bicycle");
            System.out.println("Enter (2) to add a Motorcycle");
            System.out.println("Enter (3) to display inventory");
            System.out.println("Enter (4) to quit");
            option = keyboard.nextInt();
            keyboard.nextLine();
            switch (option) {
                case 1: // Bicycle
                    // Prompt for variables
                    System.out.print("Manufacturer:               ");
                    manufacturer = keyboard.nextLine();
                    System.out.print("Price:                      ");
                    price = keyboard.nextDouble();
                    System.out.print("Frame Size (15\", 17\", 19\"): ");
                    frameSize = keyboard.nextInt();
                    if (frameSize != 15 && frameSize != 17 && frameSize != 19) {
                        try {
                            throw new InvalidFrameSizeException();
                        } catch (InvalidFrameSizeException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    }
                    System.out.print("# of cranks:               ");
                    cranks = keyboard.nextInt();
                    // Add to array
                    inventory[count++] = new Bicycle(manufacturer, price, frameSize, cranks);
                    break;
                case 2: // Motorcycle
                    // Prompt for variables
                    System.out.print("Manufacturer:        ");
                    manufacturer = keyboard.nextLine();
                    System.out.print("Price:               ");
                    price = keyboard.nextDouble();
                    System.out.print("Engine Displacement: ");
                    displacement = keyboard.nextInt();
                    System.out.print("Fuel Capacity:       ");
                    fuelCapacity = keyboard.nextDouble();
                    // Add to array
                    inventory[count++] = new Motorcycle(manufacturer, price, displacement, fuelCapacity);
                    break;
                case 3: // Display Inventory
                    System.out.println("\n******* Inventory *******");
                    for (int i = 0; i < count; i++) {
                        System.out.println(inventory[i]);
                    }
                    System.out.println("Average Price: $");
                    System.out.print(findAveragePrice(inventory, count));
                    System.out.println("Highest Displacement Motorcycle: ");
                    System.out.println(findMaxDisplacement(inventory, count));
                    break;
                case 4: // Quit
                    try {
                        ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(BINARY_FILE));
                        fileWriter.writeObject(inventory);
                        fileWriter.close();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                    System.out.println("\nThank you!");
                    break;
            }
        } while (option != 4);
    }
    // findAveragePrice()
    public static double findAveragePrice(Cycle[] inventory, int count) {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += inventory[i].getPrice();
        }
        return total/count;
    }
    // findHMaxDisplacement()
    public static Motorcycle findMaxDisplacement(Cycle[] inventory, int count) {
        int max = Integer.MIN_VALUE;
        Motorcycle maxDisplacement = null;
        for (int i = 0; i < count; i++) {
            if (inventory[i] instanceof Motorcycle m) {
                if (m.getDisplacement() > max) {
                    max = m.getDisplacement();
                    maxDisplacement = m;
                }
            }
        }
        return maxDisplacement;
    }
}