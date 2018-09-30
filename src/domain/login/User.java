package domain.login;
/**
 * 
 * @author mehra
 *
 */
public class User {

	private String username;
	private String password;
	private String name;
	private String email;
	private String accountType;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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

