package com.practico1.app.business;

import com.practico1.app.entity.UsuarioServiciosSalud;
import java.util.List;

public interface UsuarioBusinessLocal {
    void agregarUsuario(UsuarioServiciosSalud usuario);
    List<UsuarioServiciosSalud> obtenerUsuarios();
    UsuarioServiciosSalud buscarPorCedula(String cedula);
    List<UsuarioServiciosSalud> buscarPorNombre(String nombre);
}
