package dao;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ProductDAO {
	
	
	 private List<Product> products;

	 public ProductDAO() {
	        products = new ArrayList<>();
	        }
	 
	 
	 private int getNextAvailableId() {
			int nextId = 1;
			String query = "SELECT MAX(id) FROM products";
			try (Connection conn = DBConnection.getConnection();
			     Statement stmt = conn.createStatement();
			     ResultSet rs = stmt.executeQuery(query)) {
				if (rs.next()) {
					nextId = rs.getInt(1) + 1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return nextId;
		}

    public void addProduct(Product product) {
    	int id = getNextAvailableId();
        String query = "INSERT INTO products (id,name, category, quantity, price, expiry_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
        	stmt.setInt(1, id);
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getCategory());
            stmt.setInt(4, product.getQuantity());
            stmt.setDouble(5, product.getPrice());
            stmt.setDate(6, Date.valueOf(product.getExpiryDate()));
            stmt.executeUpdate();
            System.out.println("Product added successfully.");
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getDate("expiry_date").toLocalDate()
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void updateProduct(Product product) {
        String query = "UPDATE products SET name = ?, category = ?, quantity = ?, price = ?, expiry_date = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setInt(3, product.getQuantity());
            stmt.setDouble(4, product.getPrice());
            stmt.setDate(5, Date.valueOf(product.getExpiryDate()));  // Update expiry date
            stmt.setInt(6, product.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("No product found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteProduct(int id) {
        String query = "DELETE FROM products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("No product found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    public List<Product> getLowStockProducts(int threshold) {
        List<Product> lowStockProducts = new ArrayList<>();
        String query = "SELECT * FROM products WHERE quantity < ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, threshold);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getDate("expiry_date").toLocalDate()
                );
                lowStockProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lowStockProducts;
    }
    
  
    public List<Product> getExpiringProducts(int days) {
        List<Product> expiringProducts = new ArrayList<>();
        LocalDate now = LocalDate.now(); // Bugünün tarihi
        LocalDate expiryThreshold = now.plusDays(days); // Bugünden itibaren belirtilen gün sayısı

        // Veritabanından ürünleri getir
        String query = "SELECT * FROM products WHERE expiry_date <= ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setDate(1, Date.valueOf(expiryThreshold)); // Tarihi SQL formatına çevir
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getDate("expiry_date").toLocalDate() // Tarihi LocalDate formatına çevir
                );
                expiringProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expiringProducts;
    }

    
    public int getTotalProducts() {
        int total = 0;
        String query = "SELECT COUNT(*) FROM products";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public double getTotalInventoryValue() {
        double totalValue = 0;
        String query = "SELECT SUM(quantity * price) FROM products";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                totalValue = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalValue;
    }


}
