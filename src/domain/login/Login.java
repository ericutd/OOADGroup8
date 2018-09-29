package domain.login;

/**
 * 
 * @author mehra
 * This is the Customer before the validation.
 */
public class Login {
	private int username;
	private String password;
	
	public Login(int username, String pass){
		this.username = username;
		this.password = pass;
	}
	public int getUsername() {
		return username;
	}
	public void setUsername(int username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
