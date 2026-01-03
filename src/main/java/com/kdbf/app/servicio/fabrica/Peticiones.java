package com.kdbf.app.servicio.fabrica;

import java.net.URI;
import java.net.http.HttpRequest;

//esta clase es para generar fabricas de funciones
public class Peticiones {

  public static HttpRequest crearPeticionGet(String urlCompleta) {
    return HttpRequest.newBuilder()
        .uri(URI.create(urlCompleta))
        .GET()
        .build();
  }

}
