package com.nuvu.tarjeta.credito.entity;

import java.io.Serializable;

public class ClienteEntityPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipoIdentificacion;
	private String identificacion;
	
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
	public String getIdentificacion() {
		return identificacion;
	}
	
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClienteEntityPK [tipoIdentificacion=");
		builder.append(tipoIdentificacion);
		builder.append("][identificacion=");
		builder.append(identificacion);
		builder.append("]");
		return builder.toString();
	}
	
}
