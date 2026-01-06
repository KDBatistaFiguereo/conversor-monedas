package com.kdbf.app;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.kdbf.app.config.ApiConfig;
import com.kdbf.app.config.CargarConfiguracion;
import com.kdbf.app.modelo.Moneda;
import com.kdbf.app.servicio.ConversorServicio;
import com.kdbf.app.servicio.fabrica.Peticiones;
import com.kdbf.app.servicio.helper.CrearUrl;

public class App {
  public static void main(String[] args) {
    HttpClient cliente = HttpClient.newHttpClient();
    Gson gson = new Gson();
    Peticiones peticiones = new Peticiones();
    ApiConfig apiConfig = CargarConfiguracion.cargarConfig();
    CrearUrl crearUrl = new CrearUrl();
    ConversorServicio conversorServicio = new ConversorServicio(
        cliente,
        gson,
        peticiones,
        crearUrl,
        apiConfig);
    List<String> listaTodasMonedas = listaCompletaMonedas(conversorServicio);
    Scanner teclado = new Scanner(System.in);

    mostrarMenu(conversorServicio, teclado, listaTodasMonedas);
    teclado.close();
  }

  public static void mostrarMenu(ConversorServicio servicio, Scanner teclado, List<String> listaTodasMonedas) {
    int opcion;
    String menu = """
        Programa de conversion de monedas. Elija una opcion:
          1 - Mostrar codigo monedas sugeridas.
          2 - Mostrar todos los codigos de monedas.
          3 - Convertir una cantidad de una moneda a otra(necesita codigo)
          4 - salir
        """;
    String monedasSugeridas = """
            ARS - Peso argentino
            BOB - Boliviano boliviano
            BRL - Real brasileño
            CLP - Peso chileno
            COP - Peso colombiano
            USD - Dólar estadounidense
        """;

    do {
      System.out.println(menu);

      opcion = teclado.nextInt();

      switch (opcion) {
        case 1:
          System.out.println(monedasSugeridas);
          break;
        case 2:
          System.out.println(listaTodasMonedas);
          break;
        case 3:
          realizarConversion(teclado, servicio, listaTodasMonedas);
          break;
        case 4:
          System.out.println("¡Adios!");
          break;
        default:
          System.out.println("Opcion no valida, intente otra vez");
      }
      pausar(teclado);
    } while (opcion != 4);
  }

  public static List<String> listaCompletaMonedas(ConversorServicio servicio) {
    Moneda moneda = servicio.pedirDatosMoneda("USD"); // cualquier moneda sirve
    return ConversorServicio.mostrarTodasMonedas(moneda);
  }

  public static void realizarConversion(Scanner teclado, ConversorServicio servicio, List<String> listaTodasMonedas) {
    double cantidadDinero;
    String codigoMonedaBase;
    Moneda monedaBase;
    String codigoMonedaObjetivo;
    Double nuevaCantidadDinero;
    do {
      System.out.println("Inserte la cantidad de dinero a convertir");
      cantidadDinero = teclado.nextDouble();
      // buffer.
      teclado.nextLine();

      if (cantidadDinero < 0) {
        System.out.println("Cantidad no valida");
        continue;
      }

      System.out.println("¿En que moneda esta la cantidad? (pj, ej: USD, DOP");
      codigoMonedaBase = teclado.nextLine().toUpperCase().trim();

      if (!validarCodigo(codigoMonedaBase, listaTodasMonedas)) {
        System.out.println("Codigo no valido");
        continue;
      } else {
        monedaBase = servicio.pedirDatosMoneda(codigoMonedaBase);
      }

      System.out.println("¿A qué moneda quiere convertir la cantidad? (EJ: DOP, ARG");
      codigoMonedaObjetivo = teclado.nextLine().toUpperCase().trim();
      if (!validarCodigo(codigoMonedaObjetivo, listaTodasMonedas)) {
        System.out.println("Codigo no valido");
        continue;
      }
      nuevaCantidadDinero = ConversorServicio.convertirCantidad(cantidadDinero, monedaBase, codigoMonedaObjetivo);
      System.out.println(
          cantidadDinero + " " +
              codigoMonedaBase + " es igual a " +
              nuevaCantidadDinero + " " + codigoMonedaObjetivo);
      break;
    } while (true);

  }

  public static boolean validarCodigo(String codigoMoneda, List<String> listaTodasMonedas) {

    if (codigoMoneda.length() > 3 || codigoMoneda.length() < 3) {
      return false;
    } else if (!listaTodasMonedas.contains(codigoMoneda)) {
      return false;
    }
    return true;
  }

  public static void pausar(Scanner teclado) {
    if (teclado.hasNextLine()) {
      teclado.nextLine();
    }
    System.out.println("PRESIONE ENTER PARA CONTINUAR.....");
    teclado.nextLine();
  }
}
