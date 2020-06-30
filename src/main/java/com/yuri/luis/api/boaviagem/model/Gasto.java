package com.yuri.luis.api.boaviagem.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Gasto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idGasto;
	
	private String tipoGasto;
	
	private double valor;
	
	private String data;
	
	private String descricao;
	
	private String local;
	
	private String idViagem;
	
	public Gasto() {
		
	}

	public Gasto(String idGasto, String tipoGasto, double valor, String data, String descricao, String local) {
		super();
		this.idGasto = idGasto;
		this.tipoGasto = tipoGasto;
		this.valor = valor;
		this.data = data;
		this.descricao = descricao;
		this.local = local;
	}

	public String getIdGasto() {
		return idGasto;
	}

	public void setIdGasto(String idGasto) {
		this.idGasto = idGasto;
	}

	public String getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(String tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	public String getIdViagem() {
		return idViagem;
	}

	public void setIdViagem(String idViagem) {
		this.idViagem = idViagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGasto == null) ? 0 : idGasto.hashCode());
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
		Gasto other = (Gasto) obj;
		if (idGasto == null) {
			if (other.idGasto != null)
				return false;
		} else if (!idGasto.equals(other.idGasto))
			return false;
		return true;
	}
}
