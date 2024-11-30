package com.a3_backend.controller;


import com.a3_backend.dto.CreateUsuarioRequest;
import com.a3_backend.dto.LoginUsuarioRequest;
import com.a3_backend.dto.LoginUsuarioResponse;
import com.a3_backend.model.Usuario;
import com.a3_backend.repository.UsuarioRepository;
import com.a3_backend.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    UsuarioRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginUsuarioRequest body){
        Usuario user = this.repository.findByLogin(body.getLogin()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.getSenha(), user.getSenha())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginUsuarioResponse(user.getNome(), token, user.getId()));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody CreateUsuarioRequest body){
        Optional<Usuario> user = this.repository.findByLogin(body.getLogin());

        if(user.isEmpty()) {
            Usuario newUser = new Usuario();
            newUser.setSenha(passwordEncoder.encode(body.getSenha()));
            newUser.setLogin(body.getLogin());
            newUser.setNome(body.getNome());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new LoginUsuarioResponse(newUser.getNome(), token, newUser.getId()));
        }
        return ResponseEntity.badRequest().build();
    }
}