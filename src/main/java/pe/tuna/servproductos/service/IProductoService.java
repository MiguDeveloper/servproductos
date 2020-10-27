package pe.tuna.servproductos.service;

import pe.tuna.servproductos.models.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> findAll();

    public Producto findById(Long id);

}
