package com.microservices.controller;

import com.microservices.client.ProductoResponse;
import com.microservices.model.Producto;
import com.microservices.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> list = productoService.findAll();
        return ResponseEntity.ok(list);
    }

    // Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        if (producto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(producto);
    }

    // Crear producto
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        Producto creado = productoService.save(producto);
        // Location header opcional
        URI location = URI.create(String.format("/api/productos/%s", creado.getId()));
        return ResponseEntity.created(location).body(creado);
    }

    // Actualizar (reusa save del service: setea id y guarda)
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        Producto existente = productoService.findById(id);
        if (existente == null) return ResponseEntity.notFound().build();

        producto.setId(id);
        Producto actualizado = productoService.save(producto);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Producto existente = productoService.findById(id);
        if (existente == null) return ResponseEntity.notFound().build();

        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener producto + categoría (ProductoResponse)
    @GetMapping("/{id}/categoria")
    public ResponseEntity<ProductoResponse> obtenerConCategoria(@PathVariable Long id) {
        ProductoResponse resp = productoService.findProductoConCategoria(id);
        if (resp == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resp);
    }
}
