package com.espe.edu.Producto.controller;

import com.espe.edu.Producto.dto.ProductoRequest;
import com.espe.edu.Producto.dto.ProductoResponse;
import com.espe.edu.Producto.service.ProductoService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductoResponse> obtenerTodos() {
        return productoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ProductoResponse obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id);
    }

    @PostMapping
    public ProductoResponse crearProducto(@RequestBody ProductoRequest request, Authentication auth) {
        return productoService.crearProducto(request);
    }

    @PutMapping("/{id}")
    public ProductoResponse actualizarProducto(@PathVariable Long id, @RequestBody ProductoRequest request) {
        return productoService.actualizarProducto(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
    }
}
