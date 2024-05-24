package org.formation.service;

import java.util.HashMap;
import java.util.Map;

import org.formation.model.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
    public Map<String, String> login(User user) {
        Map<String, String> response = new HashMap<>();
        
        if (user.getLogin().equals("manager@proxibanque.fr") && user.getPassword().equals("manager")) {
        	response.put("role", "MANAGER");
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
