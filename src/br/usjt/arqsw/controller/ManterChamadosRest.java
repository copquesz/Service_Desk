package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;

/**
 * 
 * @author Lucas Copque - 816112862
 *
 */
@RestController
@Transactional
public class ManterChamadosRest {
	private FilaService filaService;
	private ChamadoService chamadoService;

	@Autowired
	public ManterChamadosRest(FilaService filaService, ChamadoService chamadoService) {
		this.filaService = filaService;
		this.chamadoService = chamadoService;
	}
	

	/**
	 * Acesso ao servico rest do método listar filas, que devolve um json com as informações das filas
	 * @param model
	 * @return lista de filas
	 */
	@RequestMapping(method = RequestMethod.GET, value = "rest/filas")
	public @ResponseBody List<Fila> listarFilas(Model model) {
		List<Fila> filas = null;
		try {
			filas =  filaService.listar();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return filas;
	}

	/**
	 * Acesso ao servico rest do método listar chamados, que devolve um json com as informações dos chamados passando uma fila como parâmetro
	 * @param model
	 * @return lista de chamados
	 */
	@RequestMapping(method = RequestMethod.GET, value = "rest/chamados/{id}")
	public @ResponseBody List<Chamado> listarChamados(@PathVariable("id") int id) {
		List<Chamado> chamados = null;
		
		try {
			Fila fila = filaService.carregar(id);			

			chamados = chamadoService.listar(fila);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return chamados;
	}

	/**
	 * Acesso ao servico rest do método criar chamado, que devolve um status http como resposta e um json com o chamado criado
	 * @param model
	 * @return chamado
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "rest/chamados")
	public ResponseEntity<Chamado> criarChamado(@RequestBody Chamado chamado) {
		try {
			chamadoService.criar(chamado);
			return new ResponseEntity<Chamado>(chamado, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Chamado>(chamado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Acesso ao servico rest do método fechar chamado, que devolve um status http como resposta e um json com o chamado alterado
	 * @param model
	 * @return chamado
	 */
	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "rest/chamado/fechar/{id}")
	public ResponseEntity<Chamado> fecharChamado(@PathVariable("id") int id) {
		Chamado chamado;
		try {
			chamado = new Chamado();
			chamado.setId(id);
			chamadoService.fecharChamado(chamado);
			chamado = chamadoService.carregar(id);
			return new ResponseEntity<Chamado>(chamado, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Chamado>(new Chamado(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}