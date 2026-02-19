package com.kdbf.app.application.port.in;

import com.kdbf.app.application.domain.model.entity.Currency;

public interface GetCurrencyUseCase {
  public Currency getCurrency(String currency);

}
