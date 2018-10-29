package permit;

import java.sql.Date;

public class PermitDAO {

	private int id;
	
	private int ownerId;
	
	private Double price;
	
	private String colourClass;
	
	private String expirationDate;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getColourClass() {
		return colourClass;
	}

	public void setColourClass(String colourClass) {
		this.colourClass = colourClass;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	private PermitDAO() {
		
	}
	
	public static PermitDAO getInstance() {
		return new PermitDAO();
	}
	
	@Override
	public String toString() {
		return "[ Owner ID: " + ownerId + " Price: " + price + " Color: " + colourClass + " Exp.Date " + expirationDate + " ] ";
	}
	
	public static PermitDAO builder(int ownerId, Double price, String color, String date) {
		PermitDAO permit = getInstance();
		permit.setOwnerId(ownerId);
		permit.setPrice(price);
		permit.setColourClass(color);
		permit.setExpirationDate(date);
		return permit;
	}
}
