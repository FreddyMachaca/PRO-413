package com.mgbt.dao;

import com.mgbt.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username); //Para seguridad/login
}
