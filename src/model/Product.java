package model;
import java.time.LocalDate;

public class Product {
	
	private int id;
	private String name;
	private String category;
	private int quantity;
	private double price; 
	private LocalDate expiryDate;
	
	public Product(int id, String name, String category, int quantity, double price, LocalDate expiryDate) 
	
	{
		
		this.id = id; 
		this.name = name; 
		this.category = category; 
		this.quantity = quantity;
	    this.price = price;
	    this.expiryDate = expiryDate;
	}
	
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", category=" + category +
               ", quantity=" + quantity + ", price=" + price + ", expiryDate=" + expiryDate + "]";
    }

}
