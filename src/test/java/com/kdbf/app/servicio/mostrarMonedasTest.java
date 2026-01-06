package com.kdbf.app.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.kdbf.app.modelo.Moneda;

public class mostrarMonedasTest {

  @Test
  public void corridaNormal() {
    Moneda moneda = new Moneda("USD", Map.of("ANG", 1.79, "AFN", 65.8430, "USD", 1.0));
    List<String> resultado = ConversorServicio.mostrarTodasMonedas(moneda);

    assertEquals(List.of("AFN", "ANG", "USD"), resultado);

  }

}
