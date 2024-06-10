package org.formation.controller;

import java.util.Map;

import org.formation.model.User;
import org.formation.service.LoginService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe qui récupère les requêtes lancées par le front au moment de la
 * connexion et y répond en renvoyant le rôle de l'utlisateur
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	private LoginService service;

	public LoginController(LoginService service) {
		this.service = service;
	}

	@PostMapping("login")
	public Map<String, String> login(@RequestBody User user) {
		return service.login(user);
	}

	@GetMapping("logout")
	public Map<String, String> logout() {
		return service.logout();
	}

}
