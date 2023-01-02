package com.uva.users.controlador;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.uva.users.Excepcion.VinoExcepcion;
import com.uva.users.Repository.VinoRepository;
import com.uva.users.model.Vino;



@RestController
@RequestMapping("ejemploVinos")
public class ejemploRest {
    private final VinoRepository repository;

    ejemploRest(VinoRepository repository) {
        this.repository = repository;
    }

    // No se ha incluido el atributo path. Si en el ejemplo que tienes está,
    // añadirle
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String newVino(@RequestBody Vino newVino) {
        try {
            repository.save(newVino);
            return "Nuevo registro creado";
        } catch (Exception e) {
            // Se deja esta parte comentada como alternativa a la gestión de errores
            // propuesta
            // throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Error al
            // crear el nuevo registro.");
            // Se usa este sistema de gestión de errores porque es más sencillo hacer que el
            // cliente reciba el mensaje de error
            throw new VinoExcepcion("Error al crear el nuevo registro.");
        }
    }

    @GetMapping(value = "/getVinos", produces = "application/JSON")
    public List<Vino> getVinos() {
        List<Vino> vinos = new ArrayList<Vino>();

        repository.findAll().forEach(vinoVisto -> vinos.add(vinoVisto));

        return vinos;

    }

    @GetMapping(value = "/getVinoId/{id}", produces = "application/JSON")
    public String getVinoById(@PathVariable int id) {
        Vino vino = repository.findById(id).orElseThrow(() -> new VinoExcepcion("Sin resultado"));
        JSONObject vinoJson = new JSONObject();
        vinoJson.put("nombreComercial", vino.getNombre_comercial());
        vinoJson.put("categoria", vino.getCategoria());
        vinoJson.put("denominacion", vino.getDenominacion());
        vinoJson.put("bodega_id", vino.getBodega_id());
        vinoJson.put("precio", vino.getPrecio());
        return vinoJson.toString();

    }

    @DeleteMapping(value = "/borrarVinoId/{id}")
    public String borrarVinoById(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "eliminado con exito";
        } else {
            return "no existe con ese id";
        }
        // illegal argument exception

    }

    @PutMapping(value = "/actualizarVinoId/{id}")
    public String actualizarVinoById(@PathVariable int id, @RequestBody Vino vino) {
        if (repository.existsById(id)) {
            Vino vinoCorrespondiente = repository.findById(id).get();
            vinoCorrespondiente.setCategoria(vino.getCategoria());
            vinoCorrespondiente.setDenominacion(vino.getDenominacion());
            vinoCorrespondiente.setNombre_comercial(vino.getNombre_comercial());
            vinoCorrespondiente.setPrecio(vino.getPrecio());
            vinoCorrespondiente.setBodega_id(vino.getBodega_id());
            repository.save(vinoCorrespondiente);
            return "actualizado correctamente";
        } else {
            return "no existe con ese id";
        }
        // illegal argument exception

    }











    // parte nueva practica 1.4

    @GetMapping("/VinoPorNombre/{nombre}")
    public Vino getVinoPorNombre_Comercial(@PathVariable String nombre) {
        Vino vino = repository.findByNombreComercial(nombre)
                .orElseThrow(() -> new VinoExcepcion("no se ha encontrado el vino de nombre " + nombre));
        return vino;
    }

    @GetMapping("/VinoPorPrecio")
    public List<Vino> getVinoPorPrecio(@RequestParam Float precio1, @RequestParam Float precio2) {
        List<Vino> vinos = repository.findByPrecioBetween(precio1, precio2);
        return vinos;
    }

   /*  @DeleteMapping("/BorrarPorDenominacion_Categoria")
    public String deletePorDenominacion_Categoria(@RequestBody String json) {
        
        try {
            JSONObject jsonObjeto = new JSONObject(json);
            String denominacion = jsonObjeto.getString("denominacion");
            String categoria = jsonObjeto.getString("categoria");
            boolean existe = repository.existsVinoByDenominacionAndCategoria(denominacion, categoria);
            if (existe) {
                long borrados = repository.deleteByDenominacionAndCategoria(denominacion, categoria);
                return "Numero de registros borrados: " + borrados;
            } else {
                System.out.println("No existen vinos de la categoría y denominación");
            }
        } catch (Exception e) {
            System.out.println(e);
            return "Error al ejecutar el método deletePorDenominacion_Categoria";
        }

        return "fin";
    }*/

    @DeleteMapping("/BorrarPorDenominacion_Categoria")
    public List<Vino> deletePorDenominacion_Categoria(@RequestBody String json) {
        
        try {
            JSONObject jsonObjeto = new JSONObject(json);
            String denominacion = jsonObjeto.getString("denominacion");
            String categoria = jsonObjeto.getString("categoria");
            boolean existe = repository.existsVinoByDenominacionAndCategoria(denominacion, categoria);
            if (existe) {
                List<Vino> borrados = repository.deleteByDenominacionAndCategoria(denominacion, categoria);
                return borrados;
            } 
        } catch (Exception e) {
            System.out.println(e);
            
        }

        List<Vino>vacia=new ArrayList<Vino>();
        return vacia;
    }

    @GetMapping("/CantidadPorDenominacion/{denominacion}")
    public String cantidadPorDenominacion(@PathVariable String denominacion) {
        
        int vinos = repository.countByDenominacion(denominacion);
        
        return "he encontrado "+vinos;
            
    }

    @GetMapping("/CantidadNoPorDenominacion/{denominacion}")
    public String cantidadNoPorDenominacion(@PathVariable String denominacion) {
        
        int vinos = repository.countByDenominacionIsNot(denominacion);
        
        return "he encontrado "+vinos+"  que no son";
            
    }



   

}
