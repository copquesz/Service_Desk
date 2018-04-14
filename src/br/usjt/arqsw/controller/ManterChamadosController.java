package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;

/**
 * 
 * @author Lucas Copque - 816112862
 *
 */
@Controller
public class ManterChamadosController {
	private FilaService filaService;
	private ChamadoService chamadoService;

	private List<Fila> listarFilas() throws IOException {
		return filaService.listar();
	}

	private List<Chamado> listarChamados(Fila fila) throws IOException {
		return chamadoService.listar(fila);
	}

	@Autowired
	public ManterChamadosController(FilaService filaService, ChamadoService chamadoService) {
		this.filaService = filaService;
		this.chamadoService = chamadoService;
	}

	/**
	 * Cria um chamado
	 * 
	 * @param model
	 * @return ChamadoCriar.jsp
	 */
	@RequestMapping("/criar_chamado")
	public String addChamado(@Valid Chamado chamado, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors()) {
				model.addAttribute("mensagem", "falhou");
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "ChamadoCriar";
			}
			chamado = chamadoService.criar(chamado);
			model.addAttribute("mensagem", "sucesso");
			model.addAttribute("chamado", chamado);
			model.addAttribute("filas", listarFilas());
			return "ChamadoCriar";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Erro";
		}
	}

	/**
	 * Fecha um chamado selecionado
	 * 
	 * @param chamado
	 * @param fila
	 * @param model
	 * @return ChamadoListarExibir.jsp
	 */
	@RequestMapping("/fechar_chamado")
	public String fecharChamado(Chamado chamado, Fila fila, Model model) {
		try {
			chamado = chamadoService.carregar(chamado.getId());
			fila = filaService.carregar(chamado.getFila().getId());
			chamadoService.fecharChamado(chamado);
			model.addAttribute("mensagem", "sucesso");
			model.addAttribute("chamados", listarChamados(chamado.getFila()));

			return "ChamadoListarExibir";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	/**
	 * Atribui uma lista de filas
	 * 
	 * @param model
	 * @return ChamadoListar.jsp
	 */
	@RequestMapping("/listar_filas_exibir")
	public String listarFilasExibir(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoListar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	/**
	 * Atribui uma lista de filas
	 * 
	 * @param model
	 * @return ChamadoCriar.jsp
	 */
	@RequestMapping("/listar_filas_criar_chamado")
	public String ListarFilasCriarChamado(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoCriar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	/**
	 * Atribui uma lista de chamados
	 * 
	 * @param fila
	 * @param result
	 * @param model
	 * @return ChamadosListarExibir.jsp
	 */
	@RequestMapping("/listar_chamados_exibir")
	public String listarChamadosExibir(@Valid Fila fila, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors()) {
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "ChamadoListar";
			}
			fila = filaService.carregar(fila.getId());
			model.addAttribute("chamados", listarChamados(fila));

			return "ChamadoListarExibir";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

}
