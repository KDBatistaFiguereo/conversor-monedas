package com.kdbf.app.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.kdbf.app.modelo.Moneda;
import com.kdbf.app.modelo.dto.MonedaDto;

public class DesdeDtoTest {

  @Test
  public void corridaNormal() {
    MonedaDto monedaDto = new MonedaDto(
        "success",
        "Sun, 04 Jan 2026 00:00:01 +0000",
        "Mon, 05 Jan 2026 00:00:01 +0000",
        "USD",
        Map.of("USD", 1.0, "ANG", 1.79));

    Moneda moneda = MonedaMapper.desdeDto(monedaDto);

    assertNotNull(moneda);
    assertEquals(monedaDto.codigoMoneda(), moneda.getCodigoMoneda());
    assertEquals(monedaDto.tasas(), moneda.getTasas());
  }

}
