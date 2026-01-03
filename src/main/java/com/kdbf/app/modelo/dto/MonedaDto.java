package com.kdbf.app.modelo.dto;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public record MonedaDto(
    @SerializedName("result") String resultado,
    @SerializedName("time_last_update_utc") String ultimaActualizacion,
    @SerializedName("time_next_update_utc") String proximaActualizacion,
    @SerializedName("base_code") String codigoMoneda,
    @SerializedName("conversion_rates") Map<String, Double> tasas) {
}
