package com.nuvu.tarjeta.credito.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CLIENTE")
@IdClass(ClienteEntityPK.class)
public class ClienteEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "TIPO_IDENTIFICACION")
	private String tipoIdentificacion;
	
	@Id
	@Column(name = "IDENTIFICACION")
	private String identificacion;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "DIRECCION")
	private String direccion;
	
	@Column(name = "TELEFONO")
	private Long telefono;
	
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
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<TarjetaEntity> tarjetaList;
    

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<TarjetaEntity> getTarjetaList() {
		return tarjetaList;
	}

	public void setTarjetaList(List<TarjetaEntity> tarjetaList) {
		this.tarjetaList = tarjetaList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClienteEntity [tipoIdentificacion=");
		builder.append(tipoIdentificacion);
		builder.append("][identificacion=");
		builder.append(identificacion);
		builder.append("][nombre=");
		builder.append(nombre);
		builder.append("][direccion=");
		builder.append(direccion);
		builder.append("][telefono=");
		builder.append(telefono);
		builder.append("][fechaRegistro=");
		builder.append(fechaRegistro);
		builder.append("][fechaActualizacion=");
		builder.append(fechaActualizacion);
		builder.append("][tarjetaList=");
		builder.append(tarjetaList);
		builder.append("]");
		return builder.toString();
	}
}
