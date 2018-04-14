package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.arqsw.dao.FilaDAO;
import br.usjt.arqsw.entity.Fila;


/**
 * @author Denilson Medrano
 * RA: 816122383
 */
 
public class FilaService {
	private FilaDAO dao;
	
	public FilaService() {
		dao = new FilaDAO();
	}
	
	public Fila carregar(int id) throws IOException {
		return dao.carregar(id);
	}
	
	public ArrayList<Fila> listar() throws IOException {
		return dao.listar();
	}
}
