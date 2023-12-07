package com.mgbt.service;

import com.mgbt.dao.IUsuarioDao;
import com.mgbt.domain.Rol;
import com.mgbt.domain.Usuario;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Esta clase tiene que ser una clase de servicio con el nombre "userDetailsService" para que lo reconozca la
//seguridad de Spring
@Service("userDetailsService")
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true) //Los métodos de esta clase deben ser transaccionales
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }

        var roles = new ArrayList<GrantedAuthority>(); //Este es el tipo que necesita Spring para poder manejar los roles

        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        //Este objeto de tipo User es de Spring
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

}
