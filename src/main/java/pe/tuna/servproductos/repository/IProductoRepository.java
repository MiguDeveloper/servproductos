package pe.tuna.servproductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.tuna.servproductos.models.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
