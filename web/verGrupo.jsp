<%@page import="Logica.Entidades.ComentarioGrupo"%>
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
        String id_grupo = "0";
        String nombreUsuario = "";
        String id_usuario = "";
        Grupo grupo;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("id_grupo")) { 
                id_grupo = cookie.getValue();
            }else  if(cookie.getName().equals("id_usuario")) { 
                id_usuario = cookie.getValue();
            }
        }
        grupo = controladoraLogica.obtenerGrupoPorId(Integer.valueOf( id_grupo));
        nombreUsuario = controladoraLogica.obtenerUsuarioPorID(Integer.valueOf(id_usuario)).getNombreUsuario();
        /****************** PAGINADO ******************/
        int itemsPorPagina = 9; // Número de items por página
        int paginaGrupo = request.getParameter("paginaGrupo") != null ? Integer.parseInt(request.getParameter("paginaGrupo")) : 1;
        int inicioLector = (paginaGrupo - 1) * itemsPorPagina;
        List<ComentarioGrupo> misCometarios = grupo.getComentarios();
        int totalComentarios = misCometarios.size();
        List<ComentarioGrupo> misCometariosPaginados = misCometarios.subList(inicioLector, Math.min(inicioLector + itemsPorPagina, totalComentarios));
        
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
            <div class="text", style="font-size:900%">
                <center><%= grupo.getNombre()%></center>
                 <br/>
                 <br/>
                 <br/>
               <div class="text", style="font-size:50%">
                <center><%= grupo.getTema()%></center> 
                </div>
            </div>
            
            <form action="crearDiscusionGrupo.jsp" method="POST" style="display: inline;">
                <input type="hidden" name="usuario" value="<%= id_usuario %>">
                <input type="hidden" name="grupo" value="<%= grupo.getId() %>">
                <input type="hidden" name="paginaOriginal" value="<%= "verGrupo.jsp" %>">
                <button type="submit" class="btn btn-small btn-primary" style="display: inline; padding: 2px 8px; font-size: 0.85em;">Crear discusión</button>
            </form>

            <div class="widget" style="border: 1px solid #ddd; padding: 20px; border-radius: 8px; margin-top: 20px;">
                        <div class="widget-header">
                            <i class="icon-comment"></i>
                            <h3>Discusiones</h3>
                        </div>
                        <div class="widget-content">
                            <% if (grupo.getCantidadComentarios()>0) { %>
                                <ul class="list-group">
                                    <% for (ComentarioGrupo comentario : misCometariosPaginados) { %>
                                        <li class="list-group-item" style="border: 1px solid #ddd; padding: 20px; border-radius: 8px; margin-top: 20px;">
                                            <p><%= comentario.getCreador().getNombre() %></p>
                                            <div class="text", style="font-size:200%"><%= comentario.getTitulo() %>:</div>
                                            <p  style="font-size:200%"><%= comentario.getComentario() %></p>
                                            <span class="rating" >
                                                <% for (int i = 0; i < comentario.getRespuestas().size(); i++) { 
                                                    //if(i==3) break;
                                                %>
                                                <p style="border: 1px solid #ddd; padding: 20px; border-radius: 8px; margin-top: 20px;"><%= comentario.getRespuestas().get(i) %></p>
                                                <% } %>
                                            </span>
                                            <form action="verGrupo.jsp" method="POST" style="text-align: right;clear:left;">
                                                <input type="hidden" name="usuario" value="<%= id_usuario %>">
                                                <input type="hidden" name="grupo" value="<%= grupo.getId() %>">
                                                <input type="hidden" name="paginaOriginal" value="<%= "verGrupo.jsp" %>">
                                                <button type="submit" class="btn btn-small btn-primary" style="display: inline; padding: 2px 8px; font-size: 0.85em;">Ver</button>
                                            </form>
                                            <form action="verGrupo.jsp" method="POST" style="text-align: right;clear:left;">
                                                <input type="hidden" name="usuario" value="<%= id_usuario %>">
                                                <input type="hidden" name="grupo" value="<%= grupo.getId() %>">
                                                <input type="hidden" name="paginaOriginal" value="<%= "verGrupo.jsp" %>">
                                                <button type="submit" class="btn btn-small btn-primary" style="display: inline; padding: 2px 8px; font-size: 0.85em;">Responder</button>
                                            </form>
                                            <br/>
                                        </li>
                                    <% } %>

                                </ul>
                            <% } else { %>
                                <p>No hay Discusiones.</p>
                            <% } %>
                        <div class="pagination">
                                <% if (paginaGrupo > 1) { %>
                                    <a href="verGrupo.jsp?paginaGrupo=<%= paginaGrupo - 1 %>">&laquo; Anterior</a>
                                <% } %>
                                <span>Página <%= paginaGrupo %> de <%= (int) Math.ceil((double) totalComentarios / itemsPorPagina) %></span>
                                <% if (paginaGrupo < (int) Math.ceil((double) totalComentarios / itemsPorPagina)) { %>
                                    <a href="verGrupo.jsp?paginaGrupo=<%= paginaGrupo + 1 %>" >Siguiente &raquo;</a>
                                <% } %>
                            </div>
                        
                        </div>
                    </div>
         </div>
        </div>
        <!-- /main -->

        <!-- /footer --> 
        
        <!-- Le javascript --->
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
