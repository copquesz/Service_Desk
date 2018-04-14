package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.dao.ChamadoDAO;

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
		return dao.criar(chamado);

	}

	public Chamado carregar(int id) throws IOException {
		return dao.carregar(id);
	}

	public void fecharChamado(Chamado chamado) throws IOException {
		dao.fecharChamado(chamado);
	}

	public ArrayList<Chamado> listar(Fila fila) throws IOException {
		return dao.listar(fila);
	}

}
