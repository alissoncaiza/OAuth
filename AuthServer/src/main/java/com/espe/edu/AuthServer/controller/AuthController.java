package com.espe.edu.AuthServer.controller;

import com.espe.edu.AuthServer.dto.LoginRequest;
import com.espe.edu.AuthServer.dto.RegistroRequest;
import com.espe.edu.AuthServer.entity.Usuario;
import com.espe.edu.AuthServer.service.AuthService;
import com.espe.edu.AuthServer.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;

    public AuthController(AuthService authService, EmailService emailService) {
        this.authService = authService;
        this.emailService = emailService;
    }

    // 🔹 Endpoint para registrar usuarios
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistroRequest request) {
        return ResponseEntity.ok(authService.registrarUsuario(request));
    }

    // 🔹 Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // 🔹 Endpoint para validar el token
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(authService.validarToken(token));
    }

    // 🔹 Endpoint para enviar el correo de verificación (llamado por el Microservicio de Usuarios)
    @PostMapping("/enviarCorreo")
    public ResponseEntity<String> enviarCorreo(@RequestBody Usuario usuario) {
        try {
            emailService.enviarCorreoConfirmacion(usuario);
            return ResponseEntity.ok("Correo de verificación enviado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al enviar el correo: " + e.getMessage());
        }
    }

    // 🔹 Endpoint para verificar el correo (cuando el usuario hace clic en el enlace)
    @GetMapping("/verify")
    public ResponseEntity<String> verificarUsuario(@RequestParam String email) {
        return ResponseEntity.ok(authService.verificarUsuario(email));
    }
}
