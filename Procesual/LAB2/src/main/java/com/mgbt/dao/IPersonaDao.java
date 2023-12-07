package com.mgbt.dao;

import com.mgbt.domain.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonaDao extends JpaRepository<Persona, Long> {
    
    public List<Persona> findByNombre(String nombre);
}
