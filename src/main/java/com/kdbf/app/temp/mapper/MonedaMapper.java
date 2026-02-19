package com.kdbf.app.mapper;

import com.kdbf.app.modelo.Moneda;
import com.kdbf.app.modelo.dto.MonedaDto;

public class MonedaMapper {

  // Convertir MonedaDto a Moneda
  public static Moneda desdeDto(MonedaDto dto) {
    return new Moneda(
        dto.codigoMoneda(),
        dto.tasas());
  }
}
