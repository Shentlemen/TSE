package com.practico1.app.data;

import com.practico1.app.entity.UsuarioServiciosSalud;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Local;

/**
 * Bean de datos para gestión de usuarios.
 * Singleton que mantiene los datos en memoria usando ConcurrentHashMap.
 * Implementa la interfaz local para acceso desde la capa de negocio.
 */
@Singleton(name = "UsuarioDataBean")
@Startup
@Local(UsuarioDataLocal.class)
public class UsuarioDataBean implements UsuarioDataLocal {
    
    private final ConcurrentMap<String, UsuarioServiciosSalud> usuarios = new ConcurrentHashMap<>();
    
    @Override
    public void agregarUsuario(UsuarioServiciosSalud usuario) {
        usuarios.put(usuario.getCedula(), usuario);
    }
    
    @Override
    public List<UsuarioServiciosSalud> obtenerTodosUsuarios() {
        return new ArrayList<>(usuarios.values());
    }
    
    @Override
    public UsuarioServiciosSalud buscarPorCedula(String cedula) {
        return usuarios.get(cedula);
    }
    
    @Override
    public List<UsuarioServiciosSalud> buscarPorNombre(String nombre) {
        // Validar que el nombre no sea null o vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            return new ArrayList<>(); // Retornar lista vacía si no hay criterio de búsqueda
        }
        
        return usuarios.values().stream()
                .filter(usuario -> usuario.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}