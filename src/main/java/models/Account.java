package models;

public class Account {

	private String username;
	private String password;
	private String saldo;

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
		this.saldo = "0";
	}

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

	
	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", saldo=" + saldo + "]";
	}

	

}