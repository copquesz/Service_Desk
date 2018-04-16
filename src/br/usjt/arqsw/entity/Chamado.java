package br.usjt.arqsw.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Lucas Copque - 816112862
 */

@Entity
@Table(name = "chamado")
public class Chamado implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String ABERTO = "Aberto";
	public static final String FECHADO = "Fechado";

	@Id
	@Column(name = "id_chamado")
	private int id;

	@NotNull
	@Size(min = 1, max = 100, message = "O chamado precisa ter uma descrição.")
	@Column(name = "descricao")
	private String descricao;

	@Column(name = "status")
	private String status;

	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(name = "dt_abertura")
	private Date abertura;

	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(name = "dt_fechamento")
	private Date fechamento;

	@Valid
	@JoinColumn(name = "id_fila")
	@ManyToOne
	private Fila fila;

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

	@Override
	public String toString() {
		return "Chamado [id=" + id + ", descricao=" + descricao + ", status=" + status + ", abertura=" + abertura
				+ ", fechamento=" + fechamento + ", fila=" + fila + "]";
	}
}