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
	private String accounttype;
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userid) {
		this.userId = userid;
	}

	
}

