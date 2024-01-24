package com.example.demo.controllers;

import com.example.demo.models.Venta;
import com.example.demo.repository.RepositoryVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class VentaControlador {

    @Autowired
    private RepositoryVenta repo;

    @GetMapping
    public  String index(){
        return "Conectado";
    }

    @GetMapping("ventas")
    public List<Venta> getVentas(){
        return repo.findAll();
    }

    @PostMapping("alta")
    public String post(@RequestBody Venta venta){
        repo.save(venta);
        return "Guardado";
    }

    @PutMapping("modificar/{id}")
    public String update(@PathVariable Long id, @RequestBody Venta venta){
        Venta updateVenta = repo.findById(id).get();
        updateVenta.setTotal(venta.getTotal());
        updateVenta.setFecha(venta.getFecha());
        repo.save(updateVenta);
        return "Modificado";
    }

    public String delete(@PathVariable Long id){

        Venta deleteVenta = repo.findById(id).get();
        repo.delete(deleteVenta);
        return "Eliminado";
    }

}
