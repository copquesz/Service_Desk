package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.ArrayList;


import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.dao.ChamadoDAO;


/**
 * @author Denilson Medrano
 * RA: 816122383
 */
 
public class ChamadoService {
	private ChamadoDAO dao;
	
	public ChamadoService() {
		dao = new ChamadoDAO();
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
