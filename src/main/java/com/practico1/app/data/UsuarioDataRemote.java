package com.practico1.app.data;

import com.practico1.app.entity.UsuarioServiciosSalud;
import java.util.List;
import jakarta.ejb.Remote;

@Remote
public interface UsuarioDataRemote {
    void agregarUsuario(UsuarioServiciosSalud usuario);
    List<UsuarioServiciosSalud> obtenerTodosUsuarios();
    UsuarioServiciosSalud buscarPorCedula(String cedula);
    List<UsuarioServiciosSalud> buscarPorNombre(String nombre);
}