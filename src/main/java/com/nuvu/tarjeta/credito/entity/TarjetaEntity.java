package com.nuvu.tarjeta.credito.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TARJETA")
public class TarjetaEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "TARJETA_SEQ", sequenceName = "TARJETA_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "TARJETA_SEQ", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_TARJETA", nullable = false)
	private Long idTarjeta;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumns({
        @JoinColumn(name="TIPO_IDENTIFICACION", referencedColumnName = "TIPO_IDENTIFICACION"),
        @JoinColumn(name="IDENTIFICACION", referencedColumnName = "IDENTIFICACION")
    })
	private ClienteEntity cliente;
	
	@Column(name = "NUMERO_TARJETA")
	private String numeroTarjeta;
	
	@Column(name = "TIPO")
	private String tipo;
	
	@Column(name = "FECHA_VENCIMIENTO")
	private String fechaVencimiento;
	
	@Column(name = "CVV")
	private String cvv;
	
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_REGISTRO", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Bogota")
	private Date fechaRegistro;
    
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_ACTUALIZACION")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Bogota")
	private Date fechaActualizacion;

	public Long getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(Long idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public String getNumeroTarjeta() {		
		return String.format("%1$" + 10 + "s", numeroTarjeta.substring( numeroTarjeta.length()-4 )).replace(' ', '*');
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento.replaceAll("[0-9]", "*");
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getCvv() {
		return cvv.replaceAll("[0-9]", "*");
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TarjetaEntity [idTarjeta=");
		builder.append(idTarjeta);
		builder.append("][cliente=");
		builder.append(cliente);
		builder.append("][numeroTarjeta=");
		builder.append(numeroTarjeta);
		builder.append("][tipo=");
		builder.append(tipo);
		builder.append("][fechaVencimiento=");
		builder.append(fechaVencimiento);
		builder.append("][cvv=");
		builder.append(cvv);
		builder.append("]");
		return builder.toString();
	}
}
