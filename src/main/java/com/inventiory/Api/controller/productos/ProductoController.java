package com.inventiory.Api.controller.productos;

import com.inventiory.Api.Entity.Categoria;
import com.inventiory.Api.Entity.Producto;
import com.inventiory.Api.Entity.User;
import com.inventiory.Api.Service.ProductoService;
import com.inventiory.Api.config.JwtService;
import com.inventiory.Api.exception.DuplicateProductException;
import com.inventiory.Api.repository.CategoriaRepository;
import com.inventiory.Api.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.coyote.BadRequestException;

@RestController
@RequestMapping("/api/productos")

public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/crearProductos")
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Producto producto, HttpServletRequest request) throws BadRequestException {

        User user = jwtService.extractUser(request);
        producto.setUsuario(user);
        Producto nuevoProducto = productoService.crearProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/verProductos")
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos(HttpServletRequest request) {
        User user = jwtService.extractUser(request);
        List<Producto> productos = productoService.obtenerTodosLosProductosPorUsuario((Long) user.getId());
        return ResponseEntity.ok(productos);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/obtenerCategorias")
    public ResponseEntity<List<Categoria>> obtenerCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return ResponseEntity.ok(categorias);
    }

}
