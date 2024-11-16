<%@page import="Logica.Entidades.Paper"%>
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
        <title>Inicio - Ubooks</title>
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
        Cookie[] cookies = request.getCookies();
        Lector lector = null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("id_usuario")) { 
                id_usuario = cookie.getValue();
                break;
            }
        }
        if (id_usuario.equals("0"))  
            response.sendRedirect("index.jsp");
        else
            nombreUsuario = controladoraLogica.obtenerUsuarioPorID(Integer.parseInt(id_usuario)).getNombreUsuario();
            lector = controladoraLogica.obtenerLectorPorIdUsuario(Integer.parseInt(id_usuario));
          /****************** PAGINADO ******************/
        int itemsPorPagina = 2; // Número de items por página
        int paginaRecomendacionPaperCalificacion = request.getParameter("paginaRecomendacionPaperCalificacion") != null 
            ? Integer.parseInt(request.getParameter("paginaRecomendacionPaperCalificacion")) 
            : 1;
        int inicioRecomendacionPaperCalificacion = (paginaRecomendacionPaperCalificacion - 1) * itemsPorPagina;

        List<Paper> misPapersRecomendadosPorCalificacion = controladoraLogica.obtenerPapersPorIdiomaOrdenadosPorCalificacion(lector.getIdioma());

        int totalPapersRecomendadosPorCalificacion = misPapersRecomendadosPorCalificacion.size();

        List<Paper> misPapersRecomendadosPorCalificacionPaginados = misPapersRecomendadosPorCalificacion.subList(
            inicioRecomendacionPaperCalificacion, 
            Math.min(inicioRecomendacionPaperCalificacion + itemsPorPagina, totalPapersRecomendadosPorCalificacion)
        );

        
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
                            <div class="nav-collapse">
                                <ul class="nav">
                                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                                class="icon-book"></i><span>Lectores</span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="registrarLector.jsp" >Registrar</a></li>
                                            <li><a href="listarLectores.jsp" >Listar</a></li>
                                            <li><a href="buscarLectorPorNombre.jsp" >Buscar</a></li>
                                            <li><a href="modificarLector.jsp" >Modificar</a></li>
                                            <li><a href="eliminarLector.jsp" >Eliminar</a></li>
                                        </ul>                                    
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li class="active">
                            <div class="nav-collapse">
                                <ul class="nav">
                                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                                class="icon-pencil"></i><span>Papers</span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="registrarPaper.jsp" >Registrar</a></li>
                                            <li><a href="listarPapers.jsp" >Listar</a></li>
                                            <li><a href="buscarPaper.jsp" >Buscar</a></li>
                                            <li><a href="modificarPaper.jsp" >Modificar</a></li>
                                            <li><a href="eliminarPaper.jsp" >Eliminar</a></li>
                                        </ul>                                    
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li class="active">
                            <div class="nav-collapse">
                                <ul class="nav">
                                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                                class="icon-user"></i><span>Grupos</span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="registrarGrupo.jsp" >Registrar</a></li>
                                            <li><a href="listarGrupos.jsp" >Listar</a></li>
                                        </ul>                                    
                                    </li>
                                </ul>
                            </div>
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
                            <!-- /widget -->
                            <div class="widget">
                                <div class="widget-header"> <i class="icon-user"></i>
                                    <h3> Recomendaciones mejor calificados</h3>
                                </div>
                                <!-- /widget-header -->
                                <div class="widget-content">
                                    <ul class="messages_layout">
                                        <%      
                                                            for (Paper misPapersPaginados : misPapersRecomendadosPorCalificacionPaginados) {
                                                %>
                                                <li class="from_user leftLector" <a href="listarLectores.jsp" class="avatar"><img src="resources/img/mensaje_libro.png"/></a>
                                                <div class="message_wrap"> <span class="arrow"></span>
                                                    <div class="info"> <a class="name"><%=misPapersPaginados.getNombre()%></a>
                                                        <span class="rating">
                                                            <% for (int i = 0; i < 5; i++) { %>
                                                            <span style="color: <%= i < (int) Math.round(controladoraLogica.obtenerPromedioValoracionPaper(misPapersPaginados.getId())) ? "gold" : "#ddd" %>;">★</span>
                                                            <% } %>
                                                        </span>
                                                    </div>
                                                         <div class="text"><%=misPapersPaginados.getAutor().getNombre() + " " + misPapersPaginados.getAutor().getApellido()%></div>
                                                </div>
                                                </li>
                                        <% }%>
                                    </ul>
                                </div>
                                <!-- /widget-content --> 
                            </div>
                            <!-- /widget --> 
                            <div class="pagination">
                                <% if (paginaRecomendacionPaperCalificacion > 1) { %>
                                    <a href="inicio.jsp?paginaRecomendacionPaperCalificacion=<%= paginaRecomendacionPaperCalificacion - 1 %>">&laquo; Anterior</a>
                                <% } %>
                                <span>Página <%= paginaRecomendacionPaperCalificacion %> de <%= (int) Math.ceil((double) totalPapersRecomendadosPorCalificacion / itemsPorPagina) %></span>
                                <% if (paginaRecomendacionPaperCalificacion < (int) Math.ceil((double) totalPapersRecomendadosPorCalificacion / itemsPorPagina)) { %>
                                    <a href="inicio.jsp?paginaRecomendacionPaperCalificacion=<%= paginaRecomendacionPaperCalificacion + 1 %>">Siguiente &raquo;</a>
                                <% } %>
                            </div>                                 
                        </div>
                        <!-- /span6 -->
                        <div class="span6">
                            <div class="widget">
                                <div class="widget-header"> <i class="icon-bookmark"></i>
                                    <h3> Accesos rapidos</h3>
                                </div>
                                <!-- /widget-header -->
                                <div class="widget-content">
                                    <div class="shortcuts"> 
                                        <a href="buscarLectorPorNombre.jsp" class="shortcut"><i class="shortcut-icon icon-book"></i><span class="shortcut-label">Buscar Lector</span> </a>
                                        <a href="buscarPaper.jsp" class="shortcut"><i class="shortcut-icon icon-pencil"></i><span class="shortcut-label">Buscar Paper</span> </a>
                                        <!-- /shortcuts --> 
                                    </div>
                                    <!-- /widget-content --> 
                                </div>
                            </div>
                            <!-- /widget -->
                        </div>
                        <!-- /span6 --> 
                    </div>
                    <!-- /row --> 
                </div>
                <!-- /container --> 
            </div>
            <!-- /main-inner --> 
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
