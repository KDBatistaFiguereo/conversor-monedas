package com.kdbf.app.servicio.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.kdbf.app.config.ApiConfig;

public class generarUrlTest {

  @Test
  public void corridaNormal() {
    // La llave es imaginaria
    ApiConfig apiConfig = new ApiConfig("https://apiejemplo.com/v10", "SAomOfHFiZpW4kpx3LJV");
    CrearUrl crearUrl = new CrearUrl();
    String endpoint = "latest/USD";
    String urlCompleta = crearUrl.construirUrl(apiConfig, endpoint);

    assertEquals("https://apiejemplo.com/v10/SAomOfHFiZpW4kpx3LJV/latest/USD", urlCompleta);
  }

}
