package com.kdbf.app.config;

import io.github.cdimascio.dotenv.Dotenv;

public class CargarConfiguracion {

  public static ApiConfig cargarConfig() {
    final Dotenv dotenv = Dotenv.load();
    return new ApiConfig(
        dotenv.get("URL_BASE"),
        dotenv.get("LLAVE_API"));
  }
}
