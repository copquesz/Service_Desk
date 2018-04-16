package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw.dao.ChamadoDAO;
import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author Lucas Copque - 816112862
 *
 */
@Service
public class ChamadoService {
	private ChamadoDAO dao;

	@Autowired
	public ChamadoService(ChamadoDAO dao) {
		this.dao = dao;
	}

	public Chamado criar(Chamado chamado) throws IOException {
		chamado.setStatus(Chamado.ABERTO);
		chamado.setAbertura(new Date());
		chamado.setFechamento(null);
		return dao.criar(chamado);

	}

	public Chamado carregar(int id) throws IOException {
		return dao.carregar(id);
	}

	public void fecharChamado(Chamado chamado) throws IOException {
		chamado.setFechamento(new Date());
		chamado.setStatus(Chamado.FECHADO);
		dao.fecharChamado(chamado);
	}

	public ArrayList<Chamado> listar(Fila fila) throws IOException {
		return dao.listar(fila);
	}

}
