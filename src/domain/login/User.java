package domain.login;
/**
 * 
 * @author mehra
 *
 */
public class User {

	private int userId;
	private String password;
	private String name;
	private String email;
	private String accountType;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void getEmail(){
		return eamil;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void getAccountType(){
		return accountType;
	}
	public void setAccountType(String accountType){
		this.accountType = accountType;
	}
	
}

