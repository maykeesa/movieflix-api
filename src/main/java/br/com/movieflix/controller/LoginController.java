package br.com.movieflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.movieflix.dto.AdministradorDto;
import br.com.movieflix.dto.FuncionarioDto;
import br.com.movieflix.dto.LoginDto;
import br.com.movieflix.dto.UsuarioDto;
import br.com.movieflix.form.LoginForm;
import br.com.movieflix.model.Administrador;
import br.com.movieflix.model.Funcionario;
import br.com.movieflix.model.Usuario;
import br.com.movieflix.service.AdministradorService;
import br.com.movieflix.service.FuncionarioService;
import br.com.movieflix.service.LoginService;
import br.com.movieflix.service.UsuarioService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AdministradorService adminService;

	@Autowired
	private FuncionarioService funcService;

	@Autowired
	private UsuarioService userService;
	
	@PostMapping
	public ResponseEntity<LoginDto> login(LoginForm form){
		String role = loginService.logar(form);
		if(role.equals("admin")) {
			Administrador admin = this.adminService.getAdministradorByEmail(form.getEmail());
			return ResponseEntity.ok(new AdministradorDto(admin));
		}else if(role.equals("func")) {
			Funcionario func = this.funcService.getFuncionarioByEmail(form.getEmail());
			return ResponseEntity.ok(new FuncionarioDto(func));
		}else if(role.equals("user")) {
			Usuario user = this.userService.getUsuarioByEmail(form.getEmail());
			return ResponseEntity.ok(new UsuarioDto(user));
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
