package com.kdbf.app.servicio;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.kdbf.app.config.ApiConfig;
import com.kdbf.app.mapper.MonedaMapper;
import com.kdbf.app.modelo.Moneda;
import com.kdbf.app.modelo.dto.MonedaDto;
import com.kdbf.app.servicio.fabrica.Peticiones;
import com.kdbf.app.servicio.helper.CrearUrl;

public class ConversorServicio {

  private final HttpClient cliente;
  private final Gson gson;
  private final Peticiones peticiones;
  private final CrearUrl crearUrl;
  private final ApiConfig apiConfig;

  public ConversorServicio(HttpClient cliente, Gson gson, Peticiones peticiones, CrearUrl crearUrl,
      ApiConfig apiConfig) {
    this.cliente = cliente;
    this.gson = gson;
    this.peticiones = peticiones;
    this.crearUrl = crearUrl;
    this.apiConfig = apiConfig;
  }

  public Moneda pedirDatosMoneda(String codigoMoneda) {
    final String urlCompleta = crearUrl.construirUrl(this.apiConfig, "latest/" + codigoMoneda);
    HttpRequest peticion = peticiones.crearPeticionGet(urlCompleta);
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

  /**
   * @param cantidadDinero La cantidad a convertir
   * @param monedaBase     Especifica en que moneda esta la cantidad a convertir
   * @param monedaObjetivo Es a la moneda a la que se convertira la cantidad
   * @return devuelve la cantidad de dinero equivalente en monedaObjetivo
   */
  public static double convertirCantidad(double cantidadDinero, Moneda monedaBase, String monedaObjetivo) {
    Double tasaDeCambio = monedaBase.getTasas().get(monedaObjetivo);
    return cantidadDinero * tasaDeCambio;
  }

}
