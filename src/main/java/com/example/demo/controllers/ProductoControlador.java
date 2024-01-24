package com.example.demo.controllers;

import com.example.demo.models.Producto;

import com.example.demo.repository.RepositoryProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ProductoControlador {

    @Autowired
    private RepositoryProducto repo;

    @GetMapping
    public  String index(){
        return "Conectado";
    }

    @GetMapping("productos")
    public List<Producto> getProductos(){
        return repo.findAll();
    }

    @PostMapping("alta")
    public String post(@RequestBody Producto producto){
        repo.save(producto);
        return "Guardado";
    }

    @PutMapping("modificar/{id}")
    public String update(@PathVariable Long id, @RequestBody Producto producto){
        Producto updateProducto = repo.findById(id).get();
        updateProducto.setDescripcion(producto.getDescripcion());
        updateProducto.setCodigo(producto.getCodigo());
        updateProducto.setStock(producto.getStock());
        repo.save(updateProducto);
        return "Modificado";
    }

    @DeleteMapping("baja/{id}")
    public String delete(@PathVariable Long id){

        Producto deleteProducto = repo.findById(id).get();
        repo.delete(deleteProducto);
        return "Eliminado";
    }


}
