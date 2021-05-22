package com.nuvu.tarjeta.credito.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvu.tarjeta.credito.entity.ClienteEntity;
import com.nuvu.tarjeta.credito.entity.TarjetaEntity;
import com.nuvu.tarjeta.credito.repository.ClienteRepository;
import com.nuvu.tarjeta.credito.repository.TarjetaRepository;
import com.nuvu.tarjeta.credito.service.ITarjetaService;

@Service
public class TarjetaService implements ITarjetaService{

	@Autowired
	private TarjetaRepository tarjetaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public TarjetaEntity guardar( TarjetaEntity tarjeta ) {		
		return tarjetaRepository.save(tarjeta);
	}
	
	public TarjetaEntity consultarByNumeroTarjeta( String numeroTarjeta  ) {
		return tarjetaRepository.findByNumeroTarjeta(numeroTarjeta);		
	}
	
	public List<TarjetaEntity> consultarByTipoAndIdentificacion( String tipoIdentificacion, String identificacion  ) {
		
		ClienteEntity cliente = clienteRepository.findByTipoIdentificacionAndIdentificacion(tipoIdentificacion, identificacion);		
		return tarjetaRepository.findByCliente(cliente);		
	}
}
