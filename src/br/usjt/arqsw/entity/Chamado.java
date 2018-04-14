package br.usjt.arqsw.entity;

import java.util.Date;

/**
 * 
 * @author Lucas Copque - 816112862
 *
 */
public class Chamado {

	private int id;
	private String descricao;
	private String status;
	private Date abertura;
	private Date fechamento;
	private Fila fila;
	
	public static final String ABERTO = "Aberto";
	public static final String FECHADO = "Fechado";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getAbertura() {
		return abertura;
	}

	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}

	public Date getFechamento() {
		return fechamento;
	}

	public void setFechamento(Date fechamento) {
		this.fechamento = fechamento;
	}

	public Fila getFila() {
		return fila;
	}

	public void setFila(Fila fila) {
		this.fila = fila;
	}

}
