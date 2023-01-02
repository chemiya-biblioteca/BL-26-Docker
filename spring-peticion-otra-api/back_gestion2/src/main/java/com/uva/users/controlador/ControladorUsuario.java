package com.uva.users.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.client.RestTemplate;

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

   

    @GetMapping("/buscarNombre/{nombre}")
    public Usuario buscarNombre(@PathVariable String nombre) {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios = repository.findByName(nombre);
        if (usuarios.isEmpty()) {
            return null;
        } else {
            return usuarios.get(0);
        }

    }

    //identificacion por usuario y contrasena
    //me imagino que en la realidad se hara de una manera mas segura
    @GetMapping("/identificacion/{nombre}/{contrasena}")
    public ResponseEntity<String> identificacion(@PathVariable String nombre,@PathVariable String contrasena) {
        RestTemplate restTemplate = new RestTemplate();
    String resourceUrl= "http://localhost:8080/users/identificacion/";
    ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + nombre+"/"+contrasena, String.class);
        return response;
    }


   
  

  

}
