package com.kdbf.app.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Configuracion {

  // Static queriendo decir que la variable le pertenece a la clase
  private static final Dotenv DOTENV = Dotenv.load();

  public static String getUrlBase() {
    return DOTENV.get("URL_BASE");
  }

  public static String getLlaveApi() {
    return DOTENV.get("LLAVE_API");
  }
}
