package Warehouse;

public class Product {
	
	private String name;
	private int size;

	public Product(String name, int size){
		this.name=name;
		this.size=size;
	   }
	
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return size;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean equals(Object obj){
		
		if(obj == null){
			return false;}
		else if (this.getName().equals(((Product) obj).getName()) && this.getSize() == ((Product) obj).getSize()) {
		    return true;
		  } else {
			return false;}
		
	}
	public int hashCode(){
		
		return (int)this.size * name.hashCode();
	}


	@Override
	public String toString() {
		return name + "(nr" + size + ")";
	}


	
	
}
