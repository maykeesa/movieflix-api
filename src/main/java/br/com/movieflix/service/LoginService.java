package br.com.movieflix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.movieflix.form.LoginForm;
import br.com.movieflix.model.Administrador;
import br.com.movieflix.model.Funcionario;
import br.com.movieflix.model.UserPassword;
import br.com.movieflix.model.Usuario;

@Service
public class LoginService {

	@Autowired
	private AdministradorService adminService;

	@Autowired
	private FuncionarioService funcService;

	@Autowired
	private UsuarioService userService;

	public String logar(LoginForm form) {
		String email = form.getEmail();
		String senha = form.getSenha();
		
		if (adminService.getAdministradorByEmail(email) != null) {
			Administrador admin = adminService.getAdministradorByEmail(email);
			if(this.verificaSenha(admin, senha)) {
				return "admin";
			}	
		} else if (funcService.getFuncionarioByEmail(email) != null) {
			Funcionario func = funcService.getFuncionarioByEmail(email);
			if(this.verificaSenha(func, senha)) {
				return "func";
			}
			
		} else if (userService.getUsuarioByEmail(email) != null) {
			Usuario user = userService.getUsuarioByEmail(email);
			if(this.verificaSenha(user, senha)) {
				return "user";
			}
		}
		
		return "Email ou senha inválida.";
	}
	
	public boolean verificaSenha(UserPassword login, String senha) {
		if(login.getSenha().equals(senha)) {
			return true;
		}
		
		return false;
	}

}
