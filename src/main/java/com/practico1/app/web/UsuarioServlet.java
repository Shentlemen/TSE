package com.practico1.app.web;

import com.practico1.app.entity.UsuarioServiciosSalud;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import com.practico1.app.business.UsuarioBusinessLocal;
import java.util.List;

@WebServlet("/practico1/usuarios")
public class UsuarioServlet extends HttpServlet {
    
    @EJB
    private UsuarioBusinessLocal usuarioBusiness;
    
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action == null || action.isEmpty()) {
            action = "list";
        }
        
        switch (action) {
            case "list":
                listarUsuarios(request, response);
                break;
            case "search":
                buscarUsuarios(request, response);
                break;
            case "form":
                mostrarFormulario(request, response);
                break;
            default:
                listarUsuarios(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            agregarUsuario(request, response);
        } else {
            response.sendRedirect("usuarios");
        }
    }
    
    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<UsuarioServiciosSalud> usuarios = usuarioBusiness.obtenerUsuarios();
        request.setAttribute("usuarios", usuarios);
        request.setAttribute("titulo", "Lista de Usuarios");
        
        request.getRequestDispatcher("/WEB-INF/jsp/practico1/lista.jsp").forward(request, response);
    }
    
    private void buscarUsuarios(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        List<UsuarioServiciosSalud> usuarios = usuarioBusiness.buscarPorNombre(nombre);
        
        request.setAttribute("usuarios", usuarios);
        request.setAttribute("titulo", "Resultados de Búsqueda: " + nombre);
        request.setAttribute("nombreBuscado", nombre);
        
        request.getRequestDispatcher("/WEB-INF/jsp/practico1/lista.jsp").forward(request, response);
    }
    
    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setAttribute("titulo", "Agregar Usuario");
        request.getRequestDispatcher("/WEB-INF/jsp/practico1/formulario.jsp").forward(request, response);
    }
    
    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            String cedula = request.getParameter("cedula");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String fechaStr = request.getParameter("fechaNacimiento");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            
            LocalDate fechaNacimiento = LocalDate.parse(fechaStr, dateFormatter);
            
            UsuarioServiciosSalud usuario = new UsuarioServiciosSalud(
                cedula, nombre, apellido, fechaNacimiento, email, telefono
            );
            
            usuarioBusiness.agregarUsuario(usuario);
            request.setAttribute("mensaje", "Usuario agregado exitosamente!");
            request.setAttribute("tipoMensaje", "success");
            
        } catch (DateTimeParseException e) {
            request.setAttribute("mensaje", "Error: Formato de fecha inválido");
            request.setAttribute("tipoMensaje", "error");
        } catch (IllegalArgumentException e) {
            request.setAttribute("mensaje", "Error: " + e.getMessage());
            request.setAttribute("tipoMensaje", "error");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error inesperado: " + e.getMessage());
            request.setAttribute("tipoMensaje", "error");
        }
        
        mostrarFormulario(request, response);
    }
}
