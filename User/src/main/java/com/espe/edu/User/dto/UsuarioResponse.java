package com.espe.edu.User.dto;

import com.espe.edu.User.entity.Rol;

public class UsuarioResponse {
    private Long id;
    private String email;
    private String nombre;
    private boolean isVerified;
    private Rol rol; // 🔥 Nuevo campo para el rol

    public UsuarioResponse(Long id, String email, String nombre, boolean isVerified, Rol rol) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.isVerified = isVerified;
        this.rol = rol; // ✅ Asegurarse de incluir el rol
    }

    // Getters
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getNombre() { return nombre; }
    public boolean isVerified() { return isVerified; }
    public Rol getRol() { return rol; }
}
