package com.kdbf.app.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.http.HttpClient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.google.gson.Gson;
import com.kdbf.app.config.ApiConfig;
import com.kdbf.app.modelo.Moneda;
import com.kdbf.app.servicio.fabrica.Peticiones;
import com.kdbf.app.servicio.helper.CrearUrl;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class PedirDatosMonedaTest {

  private MockWebServer mockServidor;
  private ConversorServicio servicio;

  @BeforeEach
  public void establecer() throws IOException {
    mockServidor = new MockWebServer();
    mockServidor.start(InetAddress.getByName("localhost"), 8090);

    ApiConfig apiConfig = new ApiConfig(mockServidor.url("/").toString(), "123456789");

    servicio = new ConversorServicio(
        HttpClient.newHttpClient(),
        new Gson(),
        new Peticiones(),
        new CrearUrl(),
        apiConfig);
  }

  @AfterEach
  public void cerrar() throws IOException {
    mockServidor.shutdown();
  }

  @Test
  public void corridaNormal() {
    String jsonRespuesta = """
        {
          "result":"success",
          "documentation":"https://www.exchangerate-api.com/docs",
          "terms_of_use":"https://www.exchangerate-api.com/terms",
          "time_last_update_unix":1767657601,
          "time_last_update_utc":"Tue, 06 Jan 2026 00:00:01 +0000",
          "time_next_update_unix":1767744001,
            "time_next_update_utc":"Wed, 07 Jan 2026 00:00:01 +0000",
            "base_code":"USD",
            "conversion_rates":{
              "USD":1,
              "AED":3.6725,
              "AFN":65.8430,
              "ALL":82.6381,
              "AMD":381.3473
            }
        }
        """;
    mockServidor.enqueue(new MockResponse()
        .setBody(jsonRespuesta)
        .setResponseCode(200)
        .addHeader("Content-Type", "application/json"));

    Moneda moneda = servicio.pedirDatosMoneda("USD");
    assertEquals("USD", moneda.getCodigoMoneda());
    assertEquals(1.0, moneda.getTasas().get("USD"));

  }

}
