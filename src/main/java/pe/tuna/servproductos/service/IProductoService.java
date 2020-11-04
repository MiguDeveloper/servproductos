package pe.tuna.servproductos.service;
import pe.tuna.servicommons.models.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> findAll();

    public Producto findById(Long id);
    public Producto save(Producto producto);
    public void deleteById(Long id);

}
