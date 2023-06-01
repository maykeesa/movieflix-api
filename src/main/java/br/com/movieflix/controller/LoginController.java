package br.com.movieflix.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.movieflix.form.LoginForm;
import br.com.movieflix.model.Login;

@RestController
@RequestMapping("/login")
public class LoginController {

	@PostMapping
	public ResponseEntity<Login> login(LoginForm form){
		return null;
	}
}
