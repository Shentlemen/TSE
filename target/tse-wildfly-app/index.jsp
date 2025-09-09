<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Usuarios - Practico 1</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 800px; margin: 0 auto; }
        .menu { background: #f4f4f4; padding: 20px; border-radius: 5px; margin-bottom: 20px; }
        .menu a { display: inline-block; margin: 10px; padding: 10px 20px; background: #007bff; color: white; text-decoration: none; border-radius: 3px; }
        .menu a:hover { background: #0056b3; }
        h1 { color: #333; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Gestión de Usuarios de Servicios de Salud</h1>
        <p>Practico 1 - Jakarta EE con Session Beans</p>
        
        <div class="menu">
            <a href="practico1/usuarios?action=form">Agregar Usuario</a>
            <a href="practico1/usuarios?action=list">Listar Usuarios</a>
            <a href="practico1/usuarios?action=search">Buscar Usuarios</a>
        </div>
        
        <p>Seleccione una opción del menú para comenzar.</p>
    </div>
</body>
</html>
