package data;

import java.io.Serializable;

/**
 * ”√ªß¿‡
 * @author spacelis
 *
 */
public class User implements Serializable{

	private String username = "";
	private String password = "";
	private String smtpServer = "";
	private String popServer = "";
	
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
	public String getSmtpServer() {
		return smtpServer;
	}
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}
	public String getPopServer() {
		return popServer;
	}
	public void setPopServer(String popServer) {
		this.popServer = popServer;
	}
	@Override
	public boolean equals(Object obj) {
		User u = (User)obj;
		return u.username.equals(username)&&u.smtpServer.equals(smtpServer);
	}
	
}
