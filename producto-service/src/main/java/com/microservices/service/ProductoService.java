package com.microservices.service;

import com.microservices.client.CategoriaClient;
import com.microservices.client.ProductoResponse;
import com.microservices.dto.Categoria;
import com.microservices.model.Producto;
import com.microservices.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repositorio;
    private final CategoriaClient categoriaClient;

    public ProductoService(ProductoRepository repositorio, CategoriaClient categoriaClient) {
        this.repositorio = repositorio;
        this.categoriaClient = categoriaClient;
    }

    // ✔ Crear producto
    public Producto save(Producto producto) {
        return repositorio.save(producto);
    }

    // ✔ Obtener producto por ID
    public Producto findById(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    // ✔ Listar productos
    public List<Producto> findAll() {
        return repositorio.findAll();
    }

    // ✔ Eliminar producto
    public void delete(Long id) {
        repositorio.deleteById(id);
    }

    public ProductoResponse findProductoConCategoria(Long id) {

        Producto producto = findById(id);
        if (producto == null) return null;

        Categoria categoria = categoriaClient.obtenerCategoria(producto.getIdCategoria());

        ProductoResponse response = new ProductoResponse();
        response.setProducto(producto);
        response.setCategoria(categoria);

        return response;
    }


}
