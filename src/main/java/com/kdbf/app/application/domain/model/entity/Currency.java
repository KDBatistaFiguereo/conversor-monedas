package com.kdbf.app.application.domain.model.entity;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Currency {

  String currencyCode;
  public Map<String, Double> exchangeRates;

}
