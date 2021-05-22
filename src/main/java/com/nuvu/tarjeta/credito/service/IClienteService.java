package com.nuvu.tarjeta.credito.service;

import com.nuvu.tarjeta.credito.entity.ClienteEntity;

public interface IClienteService {

	ClienteEntity guardar(ClienteEntity cliente);

	ClienteEntity consultarByTipoAndIdentificacion(String tipoIdentificacion, String identificacion);

}
