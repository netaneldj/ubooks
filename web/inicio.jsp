<%@page import="java.util.List"%>
<%@page import="Logica.Entidades.Lector"%>
<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <title>Inicio - Ubooks</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
    <link href="resources/css/font-awesome.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <link href="resources/css/pages/dashboard.css" rel="stylesheet">
</head>
<body>

<%
    ControladoraLogica controladoraLogica = new ControladoraLogica();
    HttpSession sesion = request.getSession();
    String id_usuario = "0";
    String nombreUsuario = "";

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
    } else {
        nombreUsuario = controladoraLogica.obtenerUsuarioPorID(Integer.parseInt(id_usuario)).getNombreUsuario();
    }
%>

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
            </a>
            <a class="brand" href="inicio.jsp">Ubooks</a>
            <div class="nav-collapse">
                <ul class="nav pull-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i> <%= nombreUsuario %> <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="index.jsp">Cerrar sesión</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="main">
    <div class="main-inner">
        <div class="container">
            <div class="row">
                <div class="span6">
                    <div class="widget">
                        <div class="widget-header"> 
                            <i class="icon-user"></i>
                            <h3>Lectores</h3>
                        </div>
                        <div class="widget-content">
                            <ul class="messages_layout">
                                <%      
                                    List<Lector> lectores = controladoraLogica.obtenerLectores();
                                    for (Lector lector : lectores) {
                                %>
                                <li class="from_user leftLector">
                                    <a href="perfilLector.jsp?id=<%= lector.getId() %>" class="avatar">
                                        <img src="resources/img/mensaje_lector.png" alt="Avatar de lector"/>
                                    </a>
                                    <div class="message_wrap">
                                        <div class="info">
                                            <a class="name" href="perfilLector.jsp?id=<%= lector.getId() %>"><%= lector.getNombre() + " " + lector.getApellido() %></a>
                                        </div>
                                        <div class="text"><%= lector.getUsuario().getEmail() %></div>
                                    </div>
                                </li>
                                <% } %>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="span6">
                    <div class="widget">
                        <div class="widget-header"> 
                            <i class="icon-bookmark"></i>
                            <h3>Accesos rápidos</h3>
                        </div>
                        <div class="widget-content">
                            <div class="shortcuts"> 
                                <a href="gestion.jsp" class="shortcut">
                                    <i class="shortcut-icon icon-list-alt"></i><span class="shortcut-label">Gestión</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="footer">
    <div class="footer-inner">
        <div class="container">
            <div class="row">
                <div class="span12">
                    &copy; 2021 <a href="https://netaneldj.github.io/" target="_blank">Netanel David Jamilis</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="resources/js/jquery-1.7.2.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
</body>
</html>
