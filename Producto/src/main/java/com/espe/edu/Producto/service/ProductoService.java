package com.espe.edu.Producto.service;

import com.espe.edu.Producto.dto.ProductoRequest;
import com.espe.edu.Producto.dto.ProductoResponse;
import com.espe.edu.Producto.entity.Producto;
import com.espe.edu.Producto.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoResponse> obtenerTodos() {
        return productoRepository.findAll()
                .stream()
                .map(p -> new ProductoResponse(p.getId(), p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getStock()))
                .collect(Collectors.toList());
    }

    public ProductoResponse obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return new ProductoResponse(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getStock());
    }

    public ProductoResponse crearProducto(ProductoRequest request) {
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        productoRepository.save(producto);
        return new ProductoResponse(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getStock());
    }

    public ProductoResponse actualizarProducto(Long id, ProductoRequest request) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());

        productoRepository.save(producto);
        return new ProductoResponse(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getStock());
    }

    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }
}
