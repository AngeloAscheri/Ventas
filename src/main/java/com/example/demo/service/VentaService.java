package com.example.demo.service;

import com.example.demo.models.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaService {

    List<Venta> getAll()throws Exception;
    Optional<Venta> byId(Long id) throws Exception;
    void horaVenta(Venta venta) throws Exception;
    boolean StockAndPrecioTotal(Venta venta)throws Exception;
    Venta save(Venta venta)throws Exception;
    String totalProductosVendidos()throws Exception;
    Optional<Venta> update (Venta venta, Long id)throws Exception;
    boolean delete(Long id)throws Exception;
}
