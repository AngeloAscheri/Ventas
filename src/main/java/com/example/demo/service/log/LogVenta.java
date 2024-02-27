package com.example.demo.service.log;

import com.example.demo.models.Producto;
import com.example.demo.models.Venta;
import com.example.demo.repository.RepositoryVenta;
import com.example.demo.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LogVenta implements VentaService {

    @Autowired
    private RepositoryVenta repo;
    @Autowired
    private LogProducto logProducto;
    @Override
    public List<Venta> getAll() throws Exception {
        try {
            return repo.findAll();
        } catch (Exception e){
            throw new Exception(e.getMessage() +
                    " "+ e.getCause());
        }
    }

    @Override
    public Optional<Venta> byId(Long id) throws Exception {

        try {
            return repo.findById(id);

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void horaVenta(Venta venta) throws Exception {

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://worldclockapi.com/api/json/utc/now";
            Map<String, String> mapa = restTemplate.getForObject(url, Map.class);
            venta.setFecha(mapa.get("currentDateTime"));


            if (mapa.get("currentDateTime")==null) {
                LocalDate localDate = LocalDate.now();
                venta.setFecha(localDate.toString());

            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public boolean StockAndPrecioTotal(Venta venta) throws Exception {

        try {
            Optional<Producto> prod= logProducto.byId(venta.getProducto().getProductoId());
            Long precioProd;
            if (prod.isPresent() && prod.get().getStock()>=venta.getCantidad()){
                prod.get().setStock(prod.get().getStock()-venta.getCantidad());
                logProducto.save(prod.get());
                precioProd=prod.get().getPrecio();
                venta.setTotal(precioProd*venta.getCantidad());
                return true;
            } else return false;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public String totalProductosVendidos() throws Exception {

        try {
            List<Venta> lista= repo.findAll();
            int parcial;
            int total=0;
            String mensaje= "Total de Articulos vendidos";
            for (Venta venta : lista) {
                parcial= venta.getCantidad();
                total+=parcial;
            }
            return mensaje + " " + total;

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<Venta> update(Venta comprobante, Long id) throws Exception {
        try {
            if (repo.existsById(id)){
                repo.save(comprobante);
            } else System.out.println("Error");

            return repo.findById(id);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Venta save(Venta entity) throws Exception {

        try {
            entity = repo.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public boolean delete(Long id) throws Exception {

        try {
            if (repo.existsById(id)){
                repo.deleteById(id);
                return true;}
            else throw new Exception("Error al borrar");
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
