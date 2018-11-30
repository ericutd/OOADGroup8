package others;
 public class Admin {
	private int parkingSpotId;
	private int parkingLotId;
	private String LicenseNo;
	private boolean occupied;
	private String colorClass;
 	public int getParkingSpotId(){
		return parkingSpotId;
	}
	public void setParkingSpotId(int parkingSpotId){
		this.parkingSpotId = parkingSpotId;
	}
	public int getParkingLotId(){
		return parkingLotId;
	}
	public void setParkingLotId(int parkingLotId){
		this.parkingLotId = parkingLotId;
	}
	public String getLicenseNo(){
		return LicenseNo;
	}
	public void setLicenseNo(String LicenseNo){
		this.LicenseNo = LicenseNo;
	}
	public boolean getOccupied(){
		return occupied;
	}
	public void setOccupied(boolean occupied){
		this.occupied = occupied;
	}
	public String getColorPass(){
		return colorClass;
	}
	public void setColorPass(String colorClass){
		this.colorClass = colorClass;
	}
}
