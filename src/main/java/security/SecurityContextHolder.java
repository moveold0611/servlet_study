package security;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SecurityContextHolder {
	
	public static List<Authentication> authentications = new ArrayList<>();
	public static void addAuth(Authentication authentication) {
		authentications.add(authentication);
	}
	
	public static boolean isAuthenticated(String token) {
		for(Authentication authentication : authentications) {
			if(Objects.equals(authentication.getToken(), token)) {
				return true;
			}
		}
		return false;
	}
	
	public static Authentication findAuthenticationByToken(String token) {
		for(Authentication authentication : authentications) {
			if(Objects.equals(authentication.getToken(), token)) {
				return authentication;
			}
		}
		return null;
	}
	
	public static void removeAuth(String token) {
		for(Authentication authentication : authentications) {
			if(Objects.equals(authentication.getToken(), token)) {
				authentications.remove(authentication);
				break;
			}
		}		
	}
	
	
}
