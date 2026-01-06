package com.kdbf.app.modelo;

import java.util.Map;

public class Moneda {

  // Codigo de la moneda con la que se trabaje
  public String codigoMoneda;
  // tasas de intervambio
  public Map<String, Double> tasas;

  public Moneda(String codigoMoneda, Map<String, Double> tasas) {
    this.codigoMoneda = codigoMoneda;
    this.tasas = tasas;
  }

  public String getCodigoMoneda() {
    return codigoMoneda;
  }

  public Map<String, Double> getTasas() {
    return tasas;
  }

  public void setTasas(Map<String, Double> tasas) {
    this.tasas = tasas;
  }

}
