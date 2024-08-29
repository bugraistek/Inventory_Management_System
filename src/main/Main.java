package main;
import dao.ProductDAO;
import model.Product;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Main {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        Scanner scanner = new Scanner(System.in);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        int expiryNotificationDays = 7;
        int threshold = 10;
       
        List<Product> lowStockProducts = productDAO.getLowStockProducts(threshold);
        if (!lowStockProducts.isEmpty()) {
            System.out.println("Low Stock Alert! The following products are below the threshold of " + threshold + ":");
            for (Product product : lowStockProducts) {
                System.out.println(product);
            }
        }
        
        List<Product> expiringProducts = productDAO.getExpiringProducts(expiryNotificationDays);
        if (!expiringProducts.isEmpty()) {
            System.out.println("Expiry Alert! The following products will expire within the next " + expiryNotificationDays + " days:");
            for (Product product : expiringProducts) {
                System.out.println(product);
            }
        } else {
            System.out.println("No products are expiring within the next " + expiryNotificationDays + " days.");
        }
        
        
        
        int choice =0;
        do {
        	System.out.println("\nInventory Management System");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Check Low-Stock Products");
            System.out.println("6. Check Expiring Products");
            System.out.println("7. Total Number of Products"); 
            System.out.println("8. Total Inventory Value"); 
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            
            

            try {
            
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    // Add Product
                    System.out.print("Enter product name: ");
                    String name = scanner.next();
                    System.out.print("Enter product category: ");
                    String category = scanner.next();
                    System.out.print("Enter product quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter product expiry date (yyyy-MM-dd): ");
                    LocalDate expiryDate;
                    try {
                        String expiryDateString = scanner.next();
                        expiryDate = LocalDate.parse(expiryDateString, formatter); // Parse LocalDate
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format! Please use yyyy-MM-dd.");
                        break;
                    }
                    Product newProduct = new Product(0, name, category, quantity, price,expiryDate);
                    productDAO.addProduct(newProduct);
                    break;

                case 2:
                    // View Products
                    List<Product> products = productDAO.getProducts();
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;

                case 3:
                    // Update Product
                    System.out.print("Enter product ID to update: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter new product name: ");
                    name = scanner.next();
                    System.out.print("Enter new product category: ");
                    category = scanner.next();
                    System.out.print("Enter new quantity: ");
                    quantity = scanner.nextInt();
                    System.out.print("Enter new price: ");
                    price = scanner.nextDouble();
                    System.out.print("Enter new expiry date (yyyy-MM-dd): ");
                    try {
                        String expiryDateString = scanner.next();
                        expiryDate = LocalDate.parse(expiryDateString, formatter); // Parse LocalDate
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format! Please use yyyy-MM-dd.");
                        break;
                    }
                    Product updatedProduct = new Product(id, name, category, quantity, price,expiryDate);
                    productDAO.updateProduct(updatedProduct);
                    break;

                case 4:
                    // Delete Product
                    System.out.print("Enter product ID to delete: ");
                    id = scanner.nextInt();
                    productDAO.deleteProduct(id);
                    break;

                case 5:
                    // Check Low-Stock Products
                    lowStockProducts = productDAO.getLowStockProducts(threshold);
                    if (!lowStockProducts.isEmpty()) {
                        System.out.println("Low Stock Alert! The following products are below the threshold of " + threshold + ":");
                        for (Product product : lowStockProducts) {
                            System.out.println(product);
                        }
                    } else {
                        System.out.println("No products are below the threshold.");
                    }
                    break;

                case 6:
                    // Check Expiring Products
                    expiringProducts = productDAO.getExpiringProducts(expiryNotificationDays);
                    if (!expiringProducts.isEmpty()) {
                        System.out.println("Expiry Alert! The following products will expire within the next " + expiryNotificationDays + " days:");
                        for (Product product : expiringProducts) {
                            System.out.println(product);
                        }
                    } else {
                        System.out.println("No products are expiring within the next " + expiryNotificationDays + " days.");
                    }
                    break;

                    
                case 7:
                    System.out.println("Total Number of Products: " + productDAO.getTotalProducts());
                    break;

                case 8:
                    System.out.println("Total Inventory Value: " + productDAO.getTotalInventoryValue());
                    break;
                
                case 9:
                    // Exit
                    System.out.println("Exiting the system...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            } 
            
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid value.");
                scanner.next(); // Clear the invalid input
            }
            
            
        } while (choice != 9);

        scanner.close();
    }
}