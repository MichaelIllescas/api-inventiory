
package com.inventiory.Api.repository;

import com.inventiory.Api.Entity.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jonii
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
      boolean existsByNombreAndDescripcionAndMarca(String nombre, String descripcion, String marca);
      
      List<Producto> findByUsuarioId(Long userId);
}
