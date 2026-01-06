package com.kdbf.app.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.kdbf.app.modelo.Moneda;

public class ConvertirCantidadTest {

  @Test
  public void corridaNormal() {
    Moneda moneda = new Moneda("USD", Map.of("USD", 1.0, "ANG", 1.79));
    String monedaObjetivo = "ANG";
    double cantidadDinero = 100;
    double resultado = ConversorServicio.convertirCantidad(cantidadDinero, moneda, monedaObjetivo);

    assertEquals(179, resultado, 1);

  }
}
