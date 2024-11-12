<%@page import="Logica.Entidades.Grupo"%>
<%@page import="Logica.Entidades.Paper"%>
<%@page import="Logica.Entidades.GeneroPaper"%>
<%@page import="Logica.Entidades.IdiomaPaper"%>
<%@page import="Logica.Entidades.Usuario"%>
<%@page import="java.util.Calendar"%>
<%@page import="Logica.Entidades.Lector"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Perfil - Ubooks</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <link href="resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/bootstrap-responsive.min.css" rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
              rel="stylesheet">
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
        String id_lector = "";
        Lector lector;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("id_usuario")) { 
                id_usuario = cookie.getValue();
            } else if(cookie.getName().equals("id_lector")) { 
                id_lector = cookie.getValue();
            }
        }
        if (id_usuario.equals("0"))  
            response.sendRedirect("index.jsp");
        else
            nombreUsuario = controladoraLogica.obtenerUsuarioPorID(Integer.parseInt(id_usuario)).getNombreUsuario();
            lector = controladoraLogica.obtenerLectorPorID(Integer.parseInt(id_lector));
        /****************** PAGINADO ******************/
        int itemsPorPagina = 2; // Número de items por página
        int paginaLector = request.getParameter("paginaLector") != null ? Integer.parseInt(request.getParameter("paginaLector")) : 1;
        int inicioLector = (paginaLector - 1) * itemsPorPagina;
        List<Lector> misLectores = controladoraLogica.obtenerLectores();
        int totalLectores = misLectores.size();
        List<Lector> misLectoresPaginados = misLectores.subList(inicioLector, Math.min(inicioLector + itemsPorPagina, totalLectores));
        
        int paginaAutor= request.getParameter("paginaAutor") != null ? Integer.parseInt(request.getParameter("paginaAutor")) : 1;
        int inicioAutor = (paginaAutor - 1) * itemsPorPagina;
        List<Lector> misAutores = controladoraLogica.obtenerAutores();
        int totalAutores = misAutores.size();
        List<Lector> misAutoresPaginados = misAutores.subList(inicioAutor, Math.min(inicioAutor + itemsPorPagina, totalAutores));
        
        int paginaPaper= request.getParameter("paginaPaper") != null ? Integer.parseInt(request.getParameter("paginaPaper")) : 1;
        int inicioPaper = (paginaPaper - 1) * itemsPorPagina;
        List<Paper> misPapers = controladoraLogica.obtenerPapers();
        int totalPapers = misPapers.size();
        List<Paper> misPapersPaginados = misPapers.subList(inicioPaper, Math.min(inicioPaper + itemsPorPagina, totalPapers));
        
        int paginaGrupo= request.getParameter("paginaGrupo") != null ? Integer.parseInt(request.getParameter("paginaGrupo")) : 1;
        int inicioGrupo = (paginaGrupo - 1) * itemsPorPagina;
        List<Grupo> misGrupos = controladoraLogica.obtenerGruposPorIdUsuario(Integer.valueOf(id_lector));
        int totalGrupos = misGrupos.size();
        List<Grupo> misGruposPaginados = misGrupos.subList(inicioGrupo, Math.min(inicioGrupo + itemsPorPagina, totalGrupos));
        %>
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container"> <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"><span
                            class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span> </a><a class="brand" href="inicio.jsp">Ubooks </a>
                    <div class="nav-collapse">
                        <ul class="nav pull-right">
                            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                        class="icon-user"></i> 
                                    <%=nombreUsuario%>
                                    <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="perfilLector.jsp" >Perfil</a></li>
                                    <li><a href="index.jsp" >Cerrar sesion</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <!--/.nav-collapse --> 
                </div>
                <!-- /container --> 
            </div>
            <!-- /navbar-inner --> 
        </div>
        <!-- /navbar -->
        <div class="subnavbar">
            <div class="subnavbar-inner">
                <div class="container">
                    <ul class="mainnav">
                        <li class="active">
                            <a href="inicio.jsp">
                                <i class="icon-home"></i>
                                <span>Inicio</span> 
                            </a>
                        </li>                           
                        <li class="active">
                            <a href="gestion.jsp">
                                <i class="icon-list-alt"></i>
                                <span>Gestion</span> 
                            </a>
                        </li>                     
                    </ul>
                </div>
                <!-- /container --> 
            </div>
            <!-- /subnavbar-inner --> 
        </div>
        <!-- /subnavbar -->
        <div class="main">
            <div class="main-inner">
                <div class="container">
                    <div class="row">
                        <div class="span6">
                            <div class="widget" style="border: 1px solid #ddd; padding: 20px; border-radius: 8px;">
                                <div class="widget-header">
                                    <i class="icon-user"></i>
                                    <h3> Perfil Lector</h3>
                                </div>
                                <div class="widget-content">
                                    <form>
                                        <div class="field">
                                            <label for="nombre">Nombre:</label>
                                            <input type="text" id="nombre" name="nombre" value="<%=lector.getNombre()%>" class="login" readonly/>
                                        </div>
                                        <div class="field">
                                            <label for="apellido">Apellido:</label>  
                                            <input type="text" id="apellido" name="apellido" value="<%=lector.getApellido()%>" class="login" readonly/>
                                        </div>
                                        <div class="field">
                                            <label for="nacimiento">Fecha de nacimiento:</label>  
                                            <input type="date" form="1" name="nacimiento" value="<%=controladoraLogica.convertirDateAString2(lector.getNacimiento())%>" class="login" readonly/>
                                        </div>
                                        <div class="field">
                                            <label for="email">Email:</label>  
                                            <input type="email" id="email" name="email" value="<%=lector.getUsuario().getEmail()%>" class="login" readonly/>
                                        </div>
                                        <div class="field">
                                            <label for="idioma">Idioma preferencia:</label>
                                            <select name="idioma" readonly>
                                                <option selected><%=lector.getIdioma()%></option>
                                            </select>
                                        </div>
                                        <div class="field">
                                            <label for="genero">Género preferencia:</label>
                                            <select name="genero" readonly>
                                                <option selected><%=lector.getGenero()%></option>
                                            </select>
                                        </div>
                                        <div class="field">
                                            <label for="autor">Soy autor:</label>
                                            <input type="checkbox" id="autor" name="autor" <%=lector.getEsAutor() ? "checked" : ""%> readonly/>
                                        </div>
                                        <div class="field">
                                            <label for="nombreUsuario">Nombre de usuario:</label>
                                            <input type="text" id="nombreUsuario" name="nombreUsuario" value="<%=lector.getUsuario().getNombreUsuario()%>" class="login" readonly/>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>                        
                        <!-- Columna de Lectores -->
                        <div class="span6">
                            <div class="widget">
                                <div class="widget-header">
                                    <i class="icon-book"></i>
                                    <h3> Mis Lectores</h3>
                                </div>
                                <div class="widget-content">
                                    <ul class="messages_layout widget-list">
                                        <%      
                                            for (Lector miLector : misLectoresPaginados) {
                                        %>
                                            <li class="from_user leftLector">
                                                <a href="listarLectores.jsp" class="avatar">
                                                    <img src="resources/img/mensaje_lector.png"/>
                                                </a>
                                                <div class="message_wrap"> 
                                                    <span class="arrow"></span>
                                                    <div class="info"> 
                                                        <a class="name"><%=miLector.getNombre() + " " + miLector.getApellido()%></a>
                                                        <div class="options_arrow">
                                                            <div class="dropdown pull-right"> 
                                                                <a class="dropdown-toggle" id="dLabel" role="button" data-toggle="dropdown" data-target="#" href="#">
                                                                    <i class="icon-caret-down"></i> 
                                                                </a>
                                                                <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                                                    <li><a href="registrarLector.jsp"><i class="icon-plus-sign icon-large"></i> Registrar</a></li>
                                                                    <li><a href="modificarLector.jsp"><i class="icon-edit icon-large"></i> Modificar</a></li>
                                                                    <li><a href="eliminarLector.jsp"><i class="icon-trash icon-large"></i> Borrar</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="text"><%=miLector.getUsuario().getEmail()%></div>
                                                </div>
                                            </li>
                                        <% } %>
                                    </ul>
                                </div>
                            </div>
                            <div class="pagination">
                                <% if (paginaLector > 1) { %>
                                    <a href="perfilLector.jsp?paginaLector=<%= paginaLector - 1 %>">&laquo; Anterior</a>
                                <% } %>
                                <span>Página <%= paginaLector %> de <%= (int) Math.ceil((double) totalLectores / itemsPorPagina) %></span>
                                <% if (paginaLector < (int) Math.ceil((double) totalLectores / itemsPorPagina)) { %>
                                    <a href="perfilLector.jsp?paginaLector=<%= paginaLector + 1 %>">Siguiente &raquo;</a>
                                <% } %>
                            </div>                                       
                        </div>           
                        <div class="span6">
                            <div class="widget">
                                <div class="widget-header">
                                    <i class="icon-pencil"></i>
                                    <h3> Mis Autores</h3>
                                </div>
                                <div class="widget-content">
                                    <ul class="messages_layout widget-list">
                                        <%      
                                            for (Lector miAutor : misAutoresPaginados) {
                                        %>
                                            <li class="from_user leftLector">
                                                <a href="listarLectores.jsp" class="avatar">
                                                    <img src="resources/img/mensaje_lector.png"/>
                                                </a>
                                                <div class="message_wrap"> 
                                                    <span class="arrow"></span>
                                                    <div class="info"> 
                                                        <a class="name"><%=miAutor.getNombre() + " " + miAutor.getApellido()%></a>
                                                        <div class="options_arrow">
                                                            <div class="dropdown pull-right"> 
                                                                <a class="dropdown-toggle" id="dLabel" role="button" data-toggle="dropdown" data-target="#" href="#">
                                                                    <i class="icon-caret-down"></i> 
                                                                </a>
                                                                <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                                                    <li><a href="registrarLector.jsp"><i class="icon-plus-sign icon-large"></i> Registrar</a></li>
                                                                    <li><a href="modificarLector.jsp"><i class="icon-edit icon-large"></i> Modificar</a></li>
                                                                    <li><a href="eliminarLector.jsp"><i class="icon-trash icon-large"></i> Borrar</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="text"><%=miAutor.getUsuario().getEmail()%></div>
                                                </div>
                                            </li>
                                        <% } %>
                                    </ul>
                                </div>
                            </div>
                            <div class="pagination">
                                <% if (paginaAutor > 1) { %>
                                    <a href="perfilLector.jsp?paginaAutor=<%= paginaAutor - 1 %>">&laquo; Anterior</a>
                                <% } %>
                                <span>Página <%= paginaAutor %> de <%= (int) Math.ceil((double) totalAutores / itemsPorPagina) %></span>
                                <% if (paginaAutor < (int) Math.ceil((double) totalAutores / itemsPorPagina)) { %>
                                    <a href="perfilLector.jsp?paginaAutor=<%= paginaAutor + 1 %>">Siguiente &raquo;</a>
                                <% } %>
                            </div>                                                                             
                        </div>   
                        <div class="span6">
                            <div class="widget">
                                <div class="widget-header">
                                    <i class="icon-bookmark"></i>
                                    <h3> Mis Papers</h3>
                                </div>
                                <div class="widget-content">
                                    <ul class="messages_layout widget-list">
                                        <%      
                                            for (Paper miPaper : misPapersPaginados) {
                                        %>
                                            <li class="from_user leftLector">
                                                <a href="listarPapers.jsp" class="avatar">
                                                    <img src="resources/img/mensaje_libro.png"/>
                                                </a>
                                                <div class="message_wrap"> 
                                                    <span class="arrow"></span>
                                                    <div class="info"> 
                                                        <a class="name"><%=miPaper.getNombre()%></a>
                                                        <div class="options_arrow">
                                                            <div class="dropdown pull-right"> 
                                                                <a class="dropdown-toggle" id="dLabel" role="button" data-toggle="dropdown" data-target="#" href="#">
                                                                    <i class="icon-caret-down"></i> 
                                                                </a>
                                                                <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                                                    <li><a href="registrarPaper.jsp"><i class="icon-plus-sign icon-large"></i> Registrar</a></li>
                                                                    <li><a href="modificarPaper.jsp"><i class="icon-edit icon-large"></i> Modificar</a></li>
                                                                    <li><a href="eliminarPaper.jsp"><i class="icon-trash icon-large"></i> Borrar</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="text"><%=miPaper.getAutor().getNombre()+ " " + miPaper.getAutor().getApellido()%></div>
                                                </div>
                                            </li>
                                        <% } %>
                                    </ul>
                                </div>
                            </div>
                            <div class="pagination">
                                <% if (paginaPaper > 1) { %>
                                    <a href="perfilLector.jsp?paginaPaper=<%= paginaPaper - 1 %>">&laquo; Anterior</a>
                                <% } %>
                                <span>Página <%= paginaPaper %> de <%= (int) Math.ceil((double) totalPapers / itemsPorPagina) %></span>
                                <% if (paginaPaper < (int) Math.ceil((double) totalPapers / itemsPorPagina)) { %>
                                    <a href="perfilLector.jsp?paginaPaper=<%= paginaPaper + 1 %>">Siguiente &raquo;</a>
                                <% } %>
                            </div>                                                                        
                        </div> 
                         <div class="span6">
                            <div class="widget">
                                <div class="widget-header">
                                    <i class="icon-bookmark"></i>
                                    <h3> Mis Grupos</h3>                                      
                                </div>
                                <div class="widget-content">
                                    <ul class="messages_layout widget-list">
                                        <%      
                                            for (Grupo miGrupo : misGruposPaginados) {
                                        %>
                                            <li class="from_user leftLector">
                                                <div class="message_wrap"> 
                                                    <span class="arrow"></span>
                                                    <div class="info"> 
                                                        <a class="name"><%=miGrupo.getNombre()%></a>
                                                        <div class="options_arrow">
                                                            <div class="dropdown pull-right"> 
                                                                <a class="dropdown-toggle" id="dLabel" role="button" data-toggle="dropdown" data-target="#" href="#">
                                                                    <i class="icon-caret-down"></i> 
                                                                </a>
                                                                <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                                                    <li><a href="SvVerGrupo?id=<%= miGrupo.getId() %>"><i class="icon-plus-sign icon-large"></i> Ingresar</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="text"><%=miGrupo.getTema()%></div>
                                                </div>
                                            </li>
                                        <% } %>
                                    </ul>
                                </div>
                            </div>
                            <div class="pagination">
                                <% if (paginaGrupo > 1) { %>
                                    <a href="perfilLector.jsp?paginaGrupo=<%= paginaGrupo - 1 %>">&laquo; Anterior</a>
                                <% } %>
                                <span>Página <%= paginaGrupo %> de <%= (int) Math.ceil((double) totalGrupos / itemsPorPagina) %></span>
                                <% if (paginaGrupo < (int) Math.ceil((double) totalGrupos / itemsPorPagina)) { %>
                                    <a href="perfilLector.jsp?paginaGrupo=<%= paginaGrupo + 1 %>">Siguiente &raquo;</a>
                                <% } %>
                            </div>                                    
                        </div>     
                    </div>
                </div>
            </div>
        </div>
        <!-- /main -->

        <!-- /footer --> 
        <div class="footer">
            <div class="footer-inner">
                <div class="container">
                    <div class="row">
                        <div class="span12">
                            &copy; 2021 <a href="https://netaneldj.github.io/" target="_blank"> Netanel David Jamilis </a>
                        </div> <!-- /span12 -->
                    </div> <!-- /row -->
                </div> <!-- /container -->
            </div> <!-- /footer-inner -->
        </div> <!-- /footer -->
        <!-- Le javascript
        ================================================== --> 
        <!-- Placed at the end of the document so the pages load faster --> 
        <script src="resources/js/jquery-1.7.2.min.js"></script> 
        <script src="resources/js/excanvas.min.js"></script> 
        <script src="resources/js/chart.min.js" type="text/javascript"></script> 
        <script src="resources/js/bootstrap.js"></script>
        <script language="javascript" type="text/javascript" src="resources/js/full-calendar/fullcalendar.min.js"></script>

        <script src="resources/js/base.js"></script> 
    </body>
</html>
