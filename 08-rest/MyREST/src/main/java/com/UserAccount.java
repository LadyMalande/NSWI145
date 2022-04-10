package com;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserAccount {

	private int id;
	private String fullname;
	private String login;
	private String passwd;
	private String email;
	private double balance;
	
	public UserAccount() {}
	
	
	
	
	public UserAccount(int id, String fullname, String login, String passwd, String email, double balance) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.login = login;
		this.passwd = passwd;
		this.email = email;
		this.balance = balance;
	}


	@XmlElement(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement(name="fullname")
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	@XmlElement(name="login")
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	@XmlElement(name="password")
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	@XmlElement(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@XmlElement(name="balance")
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
