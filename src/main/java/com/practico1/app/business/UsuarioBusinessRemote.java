package com.practico1.app.business;

import com.practico1.app.entity.UsuarioServiciosSalud;
import java.util.List;
import jakarta.ejb.Remote;

/**
 * Interfaz remota para la lógica de negocio de usuarios.
 * Permite acceso desde aplicaciones remotas (requisito del proyecto).
 */
@Remote
public interface UsuarioBusinessRemote {
    void agregarUsuario(UsuarioServiciosSalud usuario);
    List<UsuarioServiciosSalud> obtenerUsuarios();
    UsuarioServiciosSalud buscarPorCedula(String cedula);
    List<UsuarioServiciosSalud> buscarPorNombre(String nombre);
}
