package com.uva.users.Controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/ejemploVinos")
public class ejemploREST {

    @GetMapping()
    public String getVinos(){
        return "mensaje desde getVinos";
    }

    @PutMapping("/id")
    public String putVinos(@RequestBody String body){
        return "contenido cuerpo de la peticion "+body;
    }

    @PostMapping("/envio")
    public String setVinos(@RequestBody String body){
        return "he recibido "+body;
    }

    @GetMapping(value="/getHTML", produces = MediaType.TEXT_HTML_VALUE)
    public String getHTML(){
        return """
        <html>
            <header>

            </header>

            <body>

                <p style='color:red;'>hola</p>


            </body>
            
        </html>
                """;
    }

    @GetMapping(value="/getJSON", produces = "application/json")
    public String getJson(){
        JSONObject devolver=new JSONObject();
        devolver.put("id",12);
        devolver.put("nombre","chema");
        return devolver.toString();
    }
    
}
