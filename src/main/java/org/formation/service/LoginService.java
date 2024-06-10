package org.formation.service;

import java.util.HashMap;
import java.util.Map;

import org.formation.model.User;
import org.springframework.stereotype.Service;

/**
 * Pour la V1 du login, on predefinit un couple login/password dedie au manager.
 * On poste une proposition de login/password, on vérifie s'il vaut bien ce qui est prévu.
 * 	Si oui, on renvoie le role MANAGER, sinon le role GUEST
 */
@Service
public class LoginService {
	
    public Map<String, String> login(User user) {
        Map<String, String> response = new HashMap<>();
        
        if (user.getLogin().equals("manager@proxibanque.fr") && user.getPassword().equals("manager@proxibanque.fr")) {
        	response.put("role", "MANAGER");
        	return response;
        }
        
        if (user.getLogin().equals("advisor@proxibanque.fr") && user.getPassword().equals("advisor@proxibanque.fr")) {
        	response.put("role", "ADVISOR");
        	return response;
        }
        
        response.put("role", "GUEST");
        return response;
    }
    
    public Map<String, String> logout() {
        Map<String, String> response = new HashMap<>();
        response.put("role", "GUEST");
        return response;
    }
    
}
