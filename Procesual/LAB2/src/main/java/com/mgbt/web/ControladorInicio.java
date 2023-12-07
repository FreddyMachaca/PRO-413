package com.mgbt.web;

import com.mgbt.domain.Persona;
import com.mgbt.service.IPersonaService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

//Esta es la clase contorladora, el "Servlet"
@Controller
public class ControladorInicio {

    @Autowired
    private IPersonaService personaService;

    @GetMapping("/")
    public String inicio(Model model) {
        var personas = personaService.listarPersonas();
        model.addAttribute("personas", personas);

        var saldoTotal = calcularSaldoTotal(personas);
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());

        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona) {
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores) {
        //@Valid sirve para que el objeto Persona cumpla las validaciones de Hibernate
        if (errores.hasErrors()) {
            return "modificar";
        } else {
            personaService.guardar(persona);
            return "redirect:/";
        }
    }

    @PostMapping("/buscar")
    public String buscar(String nombre, Model model) {
        var personas = personaService.buscarPorNombre(nombre);
        model.addAttribute("personas", personas);
        var saldoTotal = calcularSaldoTotal(personas);
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());
        return "index";
    }

    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model) { //Spring automáticamente busca un objeto Persona existente con el parámetro incluído en el mapeo (idPersona)
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }

    @GetMapping("/eliminar/{idPersona}")
    public String eliminar(Persona persona) {
        personaService.eliminar(persona);
        return "redirect:/";
    }

    @GetMapping("/ver/{idPersona}")
    public String ver(Persona persona, Model model) {
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "ver";
    }
    
    private double calcularSaldoTotal(List<Persona> personas) {
        var saldoTotal = 0D;
        for (var p : personas) {
            saldoTotal += p.getSaldo();
        }
        return saldoTotal;
    }
}
