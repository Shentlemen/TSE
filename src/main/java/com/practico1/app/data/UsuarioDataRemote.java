package com.practico1.app.data;

import com.practico1.app.entity.UsuarioServiciosSalud;
import java.util.List;
import jakarta.ejb.Remote;

/**
 * Interfaz remota para el acceso a datos de usuarios.
 * Permite acceso desde aplicaciones remotas (requisito del proyecto).
 */
@Remote
public interface UsuarioDataRemote {
    void agregarUsuario(UsuarioServiciosSalud usuario);
    List<UsuarioServiciosSalud> obtenerTodosUsuarios();
    UsuarioServiciosSalud buscarPorCedula(String cedula);
    List<UsuarioServiciosSalud> buscarPorNombre(String nombre);
}