package com.kdbf.app.servicio;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.google.gson.Gson;
import com.kdbf.app.config.Configuracion;
import com.kdbf.app.mapper.MonedaMapper;
import com.kdbf.app.modelo.Moneda;
import com.kdbf.app.modelo.dto.MonedaDto;
import com.kdbf.app.servicio.fabrica.Peticiones;

public class ConversorServicio {
  private final String urlTotal = Configuracion.getUrlBase() + Configuracion.getLlaveApi();
  private final HttpClient cliente = HttpClient.newHttpClient();
  private final Gson gson = new Gson();

  // /**
  // * Metodo que muestra todas las equivalencias de una moneda dada
  // *
  // * @param llave Es la llave de la api
  // * @param moneda Es el tipo de moneda (p. ej. DOP, USD)
  // * @return Toda las conversiones posibles a todas las monedas
  // */
  // public Map<String, Double> mostrarCambios(String llave, String moneda) {
  // return null;
  // }

  /**
   * @param endpoint Endpoint de la api
   * @return retorna la url con llave y endpoint apropiado
   */
  public String agregarEndpoint(String endpoint) {
    return this.urlTotal + endpoint;
  }

  public Moneda pedirDatosMoneda(String codigoMoneda) {
    HttpRequest peticion = Peticiones.crearPeticionGet(agregarEndpoint("/latest/" + codigoMoneda));
    HttpResponse<String> respuesta;
    MonedaDto monedaDto;
    Moneda moneda;

    try {
      respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
      monedaDto = gson.fromJson(respuesta.body(), MonedaDto.class);
      moneda = MonedaMapper.desdeDto(monedaDto);
      return moneda;

    } catch (Exception e) {
      throw new RuntimeException("Api no disponible", e);
    }

  }

}
