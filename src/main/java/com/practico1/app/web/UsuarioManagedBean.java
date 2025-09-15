package com.practico1.app.web;

import com.practico1.app.business.UsuarioBusinessLocal;
import com.practico1.app.entity.UsuarioServiciosSalud;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Named("usuarioBean")
@ViewScoped
public class UsuarioManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private UsuarioBusinessLocal usuarioBusiness;

    // Propiedades para el formulario
    private UsuarioServiciosSalud nuevoUsuario;
    private String cedula;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String email;
    private String telefono;
    private boolean activo = true;

    // Propiedades para la lista
    private List<UsuarioServiciosSalud> usuarios;
    private List<UsuarioServiciosSalud> usuariosFiltrados;

    // Propiedades para búsqueda
    private String nombreBusqueda;

    @PostConstruct
    public void init() {
        nuevoUsuario = new UsuarioServiciosSalud();
        usuarios = new ArrayList<>();
        usuariosFiltrados = new ArrayList<>();
        cargarUsuarios();
    }

    public void cargarUsuarios() {
        usuarios = usuarioBusiness.obtenerUsuarios();
        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }
        usuariosFiltrados = new ArrayList<>(usuarios);
    }

    public void agregarUsuario() {
        try {
            // Validar cédula
            if (cedula == null || cedula.trim().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cédula es obligatoria"));
                return;
            }
            
            // Crear el usuario con las propiedades individuales
            UsuarioServiciosSalud usuario = new UsuarioServiciosSalud(
                cedula, nombre, apellido, fechaNacimiento, email, telefono
            );
            usuario.setActivo(activo);
            
            usuarioBusiness.agregarUsuario(usuario);
            
            // Recargar lista
            cargarUsuarios();
            
            // Mostrar mensaje de éxito
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario agregado correctamente"));
            
            // Limpiar formulario después del éxito
            limpiarFormulario();
                
        } catch (IllegalArgumentException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error inesperado: " + e.getMessage()));
        }
    }

    public void buscarUsuarios() {
        if (nombreBusqueda != null && !nombreBusqueda.trim().isEmpty()) {
            usuariosFiltrados = usuarioBusiness.buscarPorNombre(nombreBusqueda);
        } else {
            usuariosFiltrados = new ArrayList<>(usuarios);
        }
    }

    public void mostrarTodos() {
        nombreBusqueda = null;
        usuariosFiltrados = new ArrayList<>(usuarios);
    }

    public void inicializarNuevoUsuario() {
        nuevoUsuario = new UsuarioServiciosSalud();
    }

    public void limpiarBusqueda() {
        nombreBusqueda = null;
        usuariosFiltrados = new ArrayList<>(usuarios);
    }

    private void limpiarFormulario() {
        nuevoUsuario = new UsuarioServiciosSalud();
        cedula = null;
        nombre = null;
        apellido = null;
        fechaNacimiento = null;
        email = null;
        telefono = null;
        activo = true;
    }

    // Getters y Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<UsuarioServiciosSalud> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioServiciosSalud> usuarios) {
        this.usuarios = usuarios;
    }

    public List<UsuarioServiciosSalud> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    public void setUsuariosFiltrados(List<UsuarioServiciosSalud> usuariosFiltrados) {
        this.usuariosFiltrados = usuariosFiltrados;
    }


    public UsuarioServiciosSalud getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(UsuarioServiciosSalud nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public String getNombreBusqueda() {
        return nombreBusqueda;
    }

    public void setNombreBusqueda(String nombreBusqueda) {
        this.nombreBusqueda = nombreBusqueda;
    }


    @FacesConverter(forClass = LocalDate.class)
    public static class LocalDateConverter implements Converter<LocalDate> {
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public LocalDate getAsObject(FacesContext context, UIComponent component, String value) {
            if (value == null || value.trim().isEmpty()) {
                return null;
            }
            try {
                return LocalDate.parse(value, FORMATTER);
            } catch (Exception e) {
                throw new IllegalArgumentException("Formato de fecha inválido: " + value, e);
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, LocalDate value) {
            if (value == null) {
                return "";
            }
            return value.format(FORMATTER);
        }
    }
}
