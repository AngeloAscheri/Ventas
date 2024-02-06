package com.example.demo.controllers;

import com.example.demo.models.Producto;

import com.example.demo.repository.RepositoryProducto;
import com.example.demo.service.log.LogProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ProductoControlador {

    @Autowired
    private LogProducto repo;

    @GetMapping
    public  String index(){
        return "Conectado";
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try {
            return  ResponseEntity.status(HttpStatus.OK).
                    body(repo.findAll());

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("{\"error\":\"Error.\"}");
        }
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try {
            return  ResponseEntity.status(HttpStatus.OK).
                    body(repo.findById(id));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("{\"error\":\"Error.\"}");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody Producto entity){
        try {
            return  ResponseEntity.status(HttpStatus.OK).
                    body(repo.save(entity));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("{\"error\":\"Error.\"}");
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Producto  entity){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repo.update(id,entity));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error.\"}");
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(repo.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error.\"}");
        }

    }


}
