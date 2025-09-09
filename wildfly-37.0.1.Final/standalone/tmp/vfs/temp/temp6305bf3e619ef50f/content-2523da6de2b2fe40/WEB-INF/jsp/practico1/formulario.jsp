<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${titulo}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 600px; margin: 0 auto; }
        .menu { background: #f4f4f4; padding: 20px; border-radius: 5px; margin-bottom: 20px; }
        .menu a { display: inline-block; margin: 10px; padding: 10px 20px; background: #007bff; color: white; text-decoration: none; border-radius: 3px; }
        .menu a:hover { background: #0056b3; }
        .form-container { background: #f8f9fa; padding: 30px; border-radius: 5px; }
        .form-group { margin-bottom: 20px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: bold; }
        .form-group input { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 3px; }
        .form-group input[type="date"] { width: 200px; }
        .btn { padding: 10px 20px; background: #28a745; color: white; border: none; border-radius: 3px; cursor: pointer; margin-right: 10px; }
        .btn:hover { background: #218838; }
        .btn-secondary { background: #6c757d; }
        .btn-secondary:hover { background: #545b62; }
        .mensaje { padding: 10px; margin: 10px 0; border-radius: 3px; }
        .success { background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
        .error { background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
    </style>
</head>
<body>
    <div class="container">
        <h1>${titulo}</h1>
        
        <div class="menu">
            <a href="usuarios?action=form">Agregar Usuario</a>
            <a href="usuarios?action=list">Listar Usuarios</a>
            <a href="usuarios?action=search">Buscar Usuarios</a>
        </div>
        
        <c:if test="${not empty mensaje}">
            <div class="mensaje ${tipoMensaje}">
                ${mensaje}
            </div>
        </c:if>
        
        <div class="form-container">
            <form method="post" action="usuarios">
                <input type="hidden" name="action" value="add">
                
                <div class="form-group">
                    <label for="cedula">Cédula *</label>
                    <input type="text" id="cedula" name="cedula" required>
                </div>
                
                <div class="form-group">
                    <label for="nombre">Nombre *</label>
                    <input type="text" id="nombre" name="nombre" required>
                </div>
                
                <div class="form-group">
                    <label for="apellido">Apellido *</label>
                    <input type="text" id="apellido" name="apellido" required>
                </div>
                
                <div class="form-group">
                    <label for="fechaNacimiento">Fecha de Nacimiento *</label>
                    <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>
                </div>
                
                <div class="form-group">
                    <label for="email">Email *</label>
                    <input type="email" id="email" name="email" required>
                </div>
                
                <div class="form-group">
                    <label for="telefono">Teléfono *</label>
                    <input type="text" id="telefono" name="telefono" required>
                </div>
                
                <button type="submit" class="btn">Agregar Usuario</button>
                <a href="usuarios?action=list" class="btn btn-secondary">Cancelar</a>
            </form>
        </div>
    </div>
</body>
</html>
