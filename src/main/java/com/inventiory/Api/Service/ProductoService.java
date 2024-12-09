package com.inventiory.Api.Service;

import com.inventiory.Api.Entity.Producto;
import com.inventiory.Api.exception.CreationFailedException;
import com.inventiory.Api.exception.DuplicateProductException;
import com.inventiory.Api.exception.ResourceNotFoundException;
import com.inventiory.Api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;

@Service
public class ProductoService {

   @Autowired
    private ProductoRepository productoRepository;

   

    public Producto crearProducto(Producto producto) {
        if (productoRepository.existsByNombreAndDescripcionAndMarca(
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getMarca())) {
            throw new DuplicateProductException(
                producto.getNombre(),
                producto.getMarca(),
                producto.getDescripcion()
            );
        }

        return productoRepository.save(producto);
    }



  
    public List<Producto> obtenerTodosLosProductosPorUsuario(Long idUsuario) {
        return productoRepository.findByUsuarioId(idUsuario);
    }

}
