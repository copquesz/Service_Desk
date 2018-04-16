package br.usjt.arqsw.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.entity.Usuario;
import br.usjt.arqsw.service.UsuarioService;

/**
 * 
 * @author Lucas Copque - 816112862
 *
 */
@Controller
public class ManterUsuarioController {
	private UsuarioService usuarioService;
	private HttpSession session;

	@Autowired
	public ManterUsuarioController(UsuarioService usuarioService, HttpSession session) {
		this.usuarioService = usuarioService;
		this.session = session;
	}

	/**
	 * Faz a chamada da jsp inicial
	 * 
	 * @return index.jsp
	 */
	@RequestMapping("index")
	public String inicio() {
		return "index";
	}

	/**
	 * Direciona o usuario para a tela de login
	 * 
	 * @return Login.jsp
	 */
	@RequestMapping("login")
	public String login() {
		return "Login";
	}

	/**
	 * Método que valida os dados de login do usuário
	 * 
	 * @param usuario
	 * @param result
	 * @param model
	 * @return index.jsp
	 */
	@RequestMapping("fazer_login")
	public String fazerLogin(Usuario usuario, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors()) {
				model.addAttribute("mensagem", "falhou");
				System.out.println("Deu erro " + result.toString());
				return "index";
			}
			if (usuarioService.login(usuario)) {
				session.setAttribute("usuario", usuario);
				return "index";
			} else {
				model.addAttribute("mensagem", "falhou");
				return "Login";
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Erro";
		}

	}

}
