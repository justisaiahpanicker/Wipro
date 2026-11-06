package inventory;

import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainProgram {

    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        System.out.println("PostgreSQL Connected Successfully!");

        // Create DAO object for accessing database
        ProductDao dao = new ProductDao(factory);
        Scanner sc = new Scanner(System.in);

        try {
            boolean running = true;
            while (running) {
                System.out.println("\n===== Product Inventory System =====");
                System.out.println("1. Add Product");
                System.out.println("2. View All Products");
                System.out.println("3. Search Product");
                System.out.println("4. Update Product");
                System.out.println("5. Delete Product");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                try {
                    switch (sc.nextLine().trim()) {
                    case "1":
                        addProduct(sc, dao);
                        break;
                    case "2":
                        viewAll(dao);
                        break;
                    case "3":
                        searchProduct(sc, dao);
                        break;
                    case "4":
                        updateProduct(sc, dao);
                        break;
                    case "5":
                        deleteProduct(sc, dao);
                        break;
                    case "6":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1-6.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number entered. Please try again.");
                } catch (Exception e) {
                    System.out.println("Operation failed: " + e.getMessage());
                }
            }
        } finally {
            sc.close();
            factory.close();
        }
        System.out.println("Goodbye!");
    }

    private static void addProduct(Scanner sc, ProductDao dao) {
        int id = readInt(sc, "Product ID: ");
        if (dao.getProduct(id) != null) {
            System.out.println("A product with ID " + id + " already exists.");
            return;
        }
        System.out.print("Product Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Category: ");
        String category = sc.nextLine().trim();
        double price = readDouble(sc, "Price: ");
        int quantity = readInt(sc, "Quantity: ");
        dao.addProduct(new Product(id, name, category, price, quantity));
        System.out.println("Product inserted successfully!");
    }

    private static void viewAll(ProductDao dao) {
        List<Product> products = dao.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products in inventory.");
            return;
        }
        String line = "+------+----------------------+-----------------+------------+----------+";
        System.out.println(line);
        System.out.println(String.format("| %-4s | %-20s | %-15s | %10s | %8s |",
                "ID", "Name", "Category", "Price", "Quantity"));
        System.out.println(line);
        for (Product p : products) {
            printRow(p);
        }
        System.out.println(line);
    }

    private static void searchProduct(Scanner sc, ProductDao dao) {
        int id = readInt(sc, "Enter Product ID: ");
        Product p = dao.getProduct(id);
        if (p == null) {
            System.out.println("No product found with ID " + id);
        } else {
            printRow(p);
        }
    }

    private static void updateProduct(Scanner sc, ProductDao dao) {
        int id = readInt(sc, "Enter Product ID to update: ");
        Product existing = dao.getProduct(id);
        if (existing == null) {
            System.out.println("No product found with ID " + id);
            return;
        }
        System.out.print("New Name: ");
        String name = sc.nextLine().trim();
        System.out.print("New Category: ");
        String category = sc.nextLine().trim();
        double price = readDouble(sc, "New Price: ");
        int quantity = readInt(sc, "New Quantity: ");
        dao.updateProduct(id, name, category, price, quantity);
        System.out.println("Product updated successfully!");
    }

    private static void deleteProduct(Scanner sc, ProductDao dao) {
        int id = readInt(sc, "Enter Product ID to delete: ");
        if (dao.getProduct(id) == null) {
            System.out.println("No product found with ID " + id);
            return;
        }
        dao.deleteProduct(id);
        System.out.println("Product deleted successfully!");
    }

    private static void printRow(Product p) {
        System.out.println(String.format("| %-4d | %-20s | %-15s | %10.2f | %8d |",
                p.getId(), p.getName(), p.getCategory(), p.getPrice(), p.getQuantity()));
    }

    private static int readInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(sc.nextLine().trim());
    }

    private static double readDouble(Scanner sc, String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(sc.nextLine().trim());
    }
}
