package Warehouse;

public class ProductLocation {
	private Product product;
	private String location;

	public ProductLocation(Product product, String location){
		this.product = product;
		this.location = location;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
