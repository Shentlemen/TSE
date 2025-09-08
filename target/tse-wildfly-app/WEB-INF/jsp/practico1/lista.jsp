<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${titulo}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 1200px; margin: 0 auto; }
        .menu { background: #f4f4f4; padding: 20px; border-radius: 5px; margin-bottom: 20px; }
        .menu a { display: inline-block; margin: 10px; padding: 10px 20px; background: #007bff; color: white; text-decoration: none; border-radius: 3px; }
        .menu a:hover { background: #0056b3; }
        .search-form { background: #e9ecef; padding: 15px; border-radius: 5px; margin-bottom: 20px; }
        .search-form input { padding: 8px; margin-right: 10px; width: 200px; }
        .search-form button { padding: 8px 15px; background: #28a745; color: white; border: none; border-radius: 3px; cursor: pointer; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f2f2f2; }
        .no-data { text-align: center; color: #666; font-style: italic; padding: 40px; }
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
        
        <div class="search-form">
            <form method="get" action="usuarios">
                <input type="hidden" name="action" value="search">
                <input type="text" name="nombre" placeholder="Buscar por nombre..." value="${nombreBuscado}">
                <button type="submit">Buscar</button>
            </form>
        </div>
        
        <c:if test="${not empty usuarios}">
            <table>
                <thead>
                    <tr>
                        <th>Cédula</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Fecha Nacimiento</th>
                        <th>Email</th>
                        <th>Teléfono</th>
                        <th>Activo</th>
                        <th>Fecha Registro</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="usuario" items="${usuarios}">
                        <tr>
                            <td>${usuario.cedula}</td>
                            <td>${usuario.nombre}</td>
                            <td>${usuario.apellido}</td>
                            <td>${usuario.fechaNacimiento}</td>
                            <td>${usuario.email}</td>
                            <td>${usuario.telefono}</td>
                            <td>${usuario.activo ? 'Sí' : 'No'}</td>
                            <td>${usuario.fechaRegistro}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <p><strong>Total: ${usuarios.size()} usuario(s)</strong></p>
        </c:if>
        
        <c:if test="${empty usuarios}">
            <div class="no-data">
                <c:choose>
                    <c:when test="${not empty nombreBuscado}">
                        No se encontraron usuarios con el nombre "${nombreBuscado}"
                    </c:when>
                    <c:otherwise>
                        No hay usuarios registrados
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
    </div>
</body>
</html>
