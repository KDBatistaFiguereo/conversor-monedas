package com.kdbf.app.servicio.helper;

import com.kdbf.app.config.ApiConfig;

public class CrearUrl {
  public String construirUrl(ApiConfig apiConfig, String endpoint) {
    return apiConfig.urlBase() + "/" + apiConfig.llaveApi() + "/" + endpoint;
  }
}
