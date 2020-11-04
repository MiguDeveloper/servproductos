package pe.tuna.servproductos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.tuna.servicommons.models.Producto;
import pe.tuna.servproductos.service.IProductoService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private Environment environment;

    private static final String CONFIG_SERVER_PORT = "local.server.port";

    // usando la anotacion 'Value' podemos consultar parametros que tenemos en nuestro propertie
    @Value("${server.port}")
    private int port;

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        Map<String, Object> response = new HashMap<>();
        List<Producto> productos;

        try {
            productos = productoService.findAll().stream().map(producto -> {
                // producto.setPort(Integer.parseInt(environment.getProperty(CONFIG_SERVER_PORT)));
                producto.setPort(port);
                return producto;
            }).collect(Collectors.toList());
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

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        Producto producto = null;
        Map<String, Object> response = new HashMap<>();

        try {
            producto = productoService.findById(id);
        } catch (DataAccessException ex) {
            response.put("isSuccess", false);
            response.put("message", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //producto.setPort(Integer.parseInt(environment.getProperty(CONFIG_SERVER_PORT)));
        producto.setPort(port);
        response.put("isSuccess", true);
        response.put("message", "Producto OK");
        response.put("data", producto);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {

        Map<String, Object> response = new HashMap<>();
        Producto productoCreado = null;
        try {
            productoCreado = productoService.save(producto);
        } catch (DataAccessException ex) {
            response.put("isSuccess", false);
            response.put("message", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("isSuccess", true);
        response.put("message", "Producto creado con exito");
        response.put("data", productoCreado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            productoService.deleteById(id);
        } catch (DataAccessException ex) {
            response.put("isSuccess", false);
            response.put("message", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("isSuccess", true);
        response.put("message", "Producto eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {

        Map<String, Object> response = new HashMap<>();
        Producto prodUpdate = null;
        try {
            Producto productoCurrent = productoService.findById(id);
            productoCurrent.setNombre(producto.getNombre());
            productoCurrent.setPrecio(producto.getPrecio());

            prodUpdate = productoService.save(productoCurrent);
        } catch (DataAccessException ex) {
            response.put("isSuccess", false);
            response.put("message", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("isSuccess", true);
        response.put("message", "Producto actualizado con exito");
        response.put("data", prodUpdate);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
