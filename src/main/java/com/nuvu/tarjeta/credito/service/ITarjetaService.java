package com.nuvu.tarjeta.credito.service;

import java.util.List;

import com.nuvu.tarjeta.credito.entity.TarjetaEntity;

public interface ITarjetaService {
	
	TarjetaEntity guardar( TarjetaEntity tarjeta );
	
	TarjetaEntity consultarByNumeroTarjeta( String numeroTarjeta );
	
	List<TarjetaEntity> consultarByTipoAndIdentificacion( String tipoIdentificacion, String identificacion );
}
