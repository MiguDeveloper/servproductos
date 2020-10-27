package pe.tuna.servproductos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.tuna.servproductos.models.Producto;
import pe.tuna.servproductos.service.IProductoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping("/productos")
    public ResponseEntity<?> findAll() {
        Map<String, Object> response = new HashMap<>();
        List<Producto> productos;

        try {
            productos = productoService.findAll();
        } catch (DataAccessException ex) {
            response.put("isSuccess", false);
            response.put("message", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("isSuccess", true);
        response.put("message", "listado productos OK");
        response.put("data", productos);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id ){
        Producto producto= null;
        Map<String, Object> response = new HashMap<>();

        try{
            producto = productoService.findById(id);
        }catch (DataAccessException ex){
            response.put("isSuccess", false);
            response.put("message", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("isSuccess", true);
        response.put("message", "Producto OK");
        response.put("data", producto);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }



}