package fundingchain.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SecurityService {
	
	String findLoggedInUsername();
	void logOut(HttpServletRequest request, HttpServletResponse response);
    void autologin(String username, String password);
}
