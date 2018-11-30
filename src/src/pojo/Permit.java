package pojo;

public class Permit {

	public static enum PermitColor {
		VISITOR("Visitor"),
		GREEN("Green"),
		GOLD("Gold"),
		ORANGE("Orange"),
		PURPLE("Purple");
		
		String color;
		PermitColor(String color) {
			this.color = color;
		}
		
		public String getColor() {
			return this.color;
		}
	}

	private int permitId;

	private int ownerId;
	
	private double price;
	
	private PermitColor permitColor;
	
	public int getPermitId() {
		return permitId;
	}

	public void setPermitId(int permitId) {
		this.permitId = permitId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public PermitColor getPermitColor() {
		return permitColor;
	}

	public void setPermitColor(PermitColor color) {
		this.permitColor = color;
	}

	@Override
	public String toString() {
		return "Permit [permitId=" + permitId + ", ownerId=" + ownerId + ", price=" + price + ", permitColor="
				+ permitColor + "]";
	}
	
}
