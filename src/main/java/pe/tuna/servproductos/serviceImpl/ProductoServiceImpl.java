package pe.tuna.servproductos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.tuna.servicommons.models.Producto;
import pe.tuna.servproductos.repository.IProductoRepository;
import pe.tuna.servproductos.service.IProductoService;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
}
