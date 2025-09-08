package com.practico1.app.business;

import com.practico1.app.data.UsuarioDataLocal;
import com.practico1.app.entity.UsuarioServiciosSalud;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Stateless  // Solo @Stateless, sin @LocalBean
public class UsuarioBusinessBean implements UsuarioBusinessLocal {
    
    @EJB
    private UsuarioDataLocal usuarioData;  // Inyectar la interfaz, no la clase
    
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
    
    @Override
    public void agregarUsuario(UsuarioServiciosSalud usuario) {
        // Reglas de negocio
        validarCedula(usuario.getCedula());
        validarEmail(usuario.getEmail());
        validarEdad(usuario.getFechaNacimiento());
        
        usuarioData.agregarUsuario(usuario);
    }
    
    @Override
    public List<UsuarioServiciosSalud> obtenerUsuarios() {
        return usuarioData.obtenerTodosUsuarios();
    }
    
    @Override
    public UsuarioServiciosSalud buscarPorCedula(String cedula) {
        return usuarioData.buscarPorCedula(cedula);
    }
    
    @Override
    public List<UsuarioServiciosSalud> buscarPorNombre(String nombre) {
        return usuarioData.buscarPorNombre(nombre);
    }
    
    private void validarCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("La cédula no puede estar vacía");
        }
        if (cedula.length() < 7 || cedula.length() > 8) {
            throw new IllegalArgumentException("La cédula debe tener entre 7 y 8 dígitos");
        }
        if (!cedula.matches("\\d+")) {
            throw new IllegalArgumentException("La cédula debe contener solo números");
        }
    }
    
    private void validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("El formato del email no es válido");
        }
    }
    
    private void validarEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede estar vacía");
        }
        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura");
        }
        // Solo validamos que no sea nula y que no sea futura
    }
}
