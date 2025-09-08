package com.practico1.app.data;

import com.practico1.app.entity.UsuarioServiciosSalud;
import java.util.List;

public interface UsuarioDataLocal {
    void agregarUsuario(UsuarioServiciosSalud usuario);
    List<UsuarioServiciosSalud> obtenerTodosUsuarios();
    UsuarioServiciosSalud buscarPorCedula(String cedula);
    List<UsuarioServiciosSalud> buscarPorNombre(String nombre);
}