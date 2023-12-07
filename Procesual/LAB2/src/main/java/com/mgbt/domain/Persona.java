package com.mgbt.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

@Data //Esta etiqueta de lombok ahorra getter/setters y demás
@Entity
@Table(name = "persona")
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    
    @NotEmpty //Cadenas utilizan NotEmpty
    private String nombre;
    
    @NotEmpty
    private String apellido;
    
    @NotEmpty
    @Email //Valida si tiene un formato de email valido
    private String email;
    
    @NotEmpty
    private String telefono;
    
    @NotNull //Números utilizan NotNull
    private double saldo;
}