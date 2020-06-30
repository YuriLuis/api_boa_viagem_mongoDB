package com.yuri.luis.api.boaviagem.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Viagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idViagem;

	private String destino;
	
	private String tipoDeViagem;
	
	private String dataChegada;
	
	private String dataPartida;
	
	private Usuario usuario;
	
	private double orcamento;
	
	private List<Gasto> gastos = new ArrayList<>();
	
	private int quantidadePessoa;
	
	public Viagem() {
		
	}

	public Viagem(String idViagem, String destino, String tipoDeViagem, String dataChegada, String dataPartida,
			double orcamento, int quantidadePessoa, Usuario usuario) {
		super();
		this.idViagem = idViagem;
		this.destino = destino;
		this.tipoDeViagem = tipoDeViagem;
		this.dataChegada = dataChegada;
		this.dataPartida = dataPartida;
		this.orcamento = orcamento;
		this.quantidadePessoa = quantidadePessoa;
		this.usuario = usuario;
	}

	public String getIdViagem() {
		return idViagem;
	}

	public void setIdViagem(String idViagem) {
		this.idViagem = idViagem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getTipoDeViagem() {
		return tipoDeViagem;
	}

	public void setTipoDeViagem(String tipoDeViagem) {
		this.tipoDeViagem = tipoDeViagem;
	}

	public String getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(String dataChegada) {
		this.dataChegada = dataChegada;
	}

	public String getDataPartida() {
		return dataPartida;
	}

	public void setDataPartida(String dataPartida) {
		this.dataPartida = dataPartida;
	}

	public double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(double orcamento) {
		this.orcamento = orcamento;
	}

	public int getQuantidadePessoa() {
		return quantidadePessoa;
	}

	public void setQuantidadePessoa(int quantidadePessoa) {
		this.quantidadePessoa = quantidadePessoa;
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	public List<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idViagem == null) ? 0 : idViagem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Viagem other = (Viagem) obj;
		if (idViagem == null) {
			if (other.idViagem != null)
				return false;
		} else if (!idViagem.equals(other.idViagem))
			return false;
		return true;
	}

}
