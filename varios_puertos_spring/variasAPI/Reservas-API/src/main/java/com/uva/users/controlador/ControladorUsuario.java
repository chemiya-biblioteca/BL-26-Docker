package com.uva.users.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uva.users.excepcion.UsuarioExcepcion;
import com.uva.users.modelo.Usuario;
import com.uva.users.repository.UsuarioRepository;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*")
public class ControladorUsuario {
    private final UsuarioRepository repository;

    ControladorUsuario(UsuarioRepository repository) {
        this.repository = repository;
    }

    // crear usuario
    @PostMapping
    public String newUsuario(@RequestBody Usuario newUsuario) {
        try {
            repository.save(newUsuario);
            return "Nuevo usuario creado";
        } catch (Exception e) {

            return "no se ha podido crear el usuario";
        }
    }

    // obtener todos lo usuarios
    @GetMapping
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<Usuario>();

        repository.findAll().forEach(usuarioVisto -> usuarios.add(usuarioVisto));

        return usuarios;

    }

   

}
