<%@page import="Logica.Entidades.Lector"%>
<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <title>Perfil del Lector - Ubooks</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <style>
        body {
            background-color: #f0f2f5; /* Color de fondo similar a Facebook */
        }
        .container {
            max-width: 600px; /* Limitar el ancho del contenedor */
            background-color: #fff; /* Fondo blanco para el perfil */
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* Sombra para efecto de profundidad */
            padding: 20px;
            margin-top: 20px;
        }
        .profile-header {
            display: flex;
            align-items: center;
            border-bottom: 1px solid #ddd; /* Línea de separación */
            padding-bottom: 20px;
            margin-bottom: 20px;
        }
        .profile-header img {
            border-radius: 50%;
            width: 120px; /* Tamaño de la foto de perfil */
            height: 120px; /* Tamaño de la foto de perfil */
            margin-right: 20px;
        }
        .profile-header h2 {
            font-size: 2.5em; /* Aumentar el tamaño del texto del nombre */
            margin: 0;
            color: #1877f2; /* Color azul de Facebook */
        }
        .profile-info {
            margin-top: 20px;
        }
        .profile-info p {
            font-size: 1.1em; /* Tamaño de texto ligeramente mayor */
            margin: 10px 0; /* Espaciado entre líneas */
        }
        .btn {
            background-color: #1877f2; /* Botón azul de Facebook */
            color: white;
        }
        .btn:hover {
            background-color: #166fe5; /* Color al pasar el mouse */
        }
    </style>
</head>
<body>

<%
    ControladoraLogica controladoraLogica = new ControladoraLogica();
    HttpSession sesion = request.getSession();
    String id_usuario = "0";

    // Recuperar el valor de la cookie id_usuario
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("id_usuario")) { 
            id_usuario = cookie.getValue();
            break;
        }
    }

    // Verificar si el usuario está autenticado
    if (id_usuario.equals("0")) {
        response.sendRedirect("index.jsp");  // Redirigir al login si no hay sesión válida
    }

    // Obtener el ID del lector a partir de la consulta
    String idLectorParam = request.getParameter("id");
    int idLector = Integer.parseInt(idLectorParam);
    Lector lector = controladoraLogica.obtenerLectorPorID(idLector); // Obtener el lector por ID 
%>

<div class="container">
    <div class="profile-header">
        <img src="resources/images/default-profile.png" alt="Foto de perfil"> <!-- Cambia la ruta de la imagen según sea necesario -->
        <h2><%= lector.getNombre() + " " + lector.getApellido() %></h2>
    </div>
    <div class="profile-info">
        <p><strong>Email:</strong> <%= lector.getUsuario().getEmail() %></p>
        <p><strong>Género:</strong> <%= lector.getGenero() %></p>
        <!-- Puedes agregar más campos aquí según lo que desees mostrar -->
    </div>
    <a href="inicio.jsp" class="btn btn-primary">Volver a la lista de lectores</a>
</div>

<script src="resources/js/jquery-1.7.2.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
</body>
</html>
