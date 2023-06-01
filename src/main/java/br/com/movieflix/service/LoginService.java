package br.com.movieflix.service;

import javax.swing.JPasswordField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCryptParser;
import ch.qos.logback.core.pattern.color.BoldCyanCompositeConverter;

@Service
public class LoginService {

	@Autowired
	private AdministradorService adminSerice;
	
	@Autowired
	private FuncionarioService funcService;
	
	@Autowired
	private UsuarioService userService;
	
	/*
	public String logar() {
		
	}
	*/
	
	public static void encoderPassword(String senha) {
		System.out.println(new JPasswordField(senha));
	}
}
