<%@page import="Logica.Entidades.Grupo"%>
<%@page import="Logica.Entidades.Lector"%>
<%@page import="Logica.Entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Listar Lectores - Ubooks</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes"> 

        <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />

        <link href="resources/css/font-awesome.css" rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">

        <link href="resources/css/style.css" rel="stylesheet" type="text/css">
        <link href="resources/css/pages/signin.css" rel="stylesheet" type="text/css">
        <link href="resources/css/pages/dashboard.css" rel="stylesheet">
    </head>

    <body style="font-family: Times New Roman">
        <%
            String id_usuario = "0";
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("id_usuario")) { 
                    id_usuario = cookie.getValue();
                    break;
                }
            }
            if (id_usuario.equals("0"))  
                response.sendRedirect("index.jsp");
        %>
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>

                    <a class="brand" href="inicio.jsp">
                        Ubooks				
                    </a>		

                    <div class="nav-collapse">
                        <ul class="nav pull-right">
                            <li class="">						
                                <a href="inicio.jsp" class="">
                                    <i class="icon-chevron-left"></i>
                                    Regresar al inicio
                                </a>

                            </li>
                        </ul>
                    </div><!--/.nav-collapse -->	
                </div> <!-- /container -->
            </div> <!-- /navbar-inner -->
        </div> <!-- /navbar -->


        <div class="container-fluid cata-flex">
            <div class="row ">
               <div class="span9">
                    <div class="widget widget-table action-table">
                        <div class="widget-header"> <i class="icon-th-list"></i>
                            <h3>Lista Grupos</h3>
                        </div>
                        <div class="widget-content">
                            <table class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th><center>ID</center></th>
                                    <th><center>Nombre</center></th>
                                    <th><center>Tema</center></th>
                                    <th><center>Miembros</center></th>
                                    <th><center>Comentarios</center></th>
                                    <th><center></center></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <%
                                            ControladoraLogica controladoraLogica = new ControladoraLogica();
                                            List<Grupo> grupos = controladoraLogica.obtenerGrupos();
                                            for (Grupo grupo : grupos) {
                                        %>
                                        <td><center><%=grupo.getId()%></center></td>
                                <td><center><%=grupo.getNombre()%></center></td>
                                <td><center><%=grupo.getTema()%></center></td>
                                <td><center><%=grupo.getCantidadMiembros()%></center></td>
                                <td><center><%=grupo.getCantidadComentarios()%></center></td>
                                <td><center>
                                <%
                                    Lector lector = controladoraLogica.obtenerLectorPorID(Integer.parseInt(id_usuario));
                                    if(grupo.esMiembro(lector)){
                                    %>    
                                            <form action="SvVerGrupo" method="get" style="display: inline;">
                                                <input type="hidden" name="id" value="<%= grupo.getId() %>">
                                                <button type="submit" class="btn btn-small btn-primary" style="display: inline; padding: 2px 8px; font-size: 0.85em;">Entrar</button>
                                            </form> 
                                    <% }else{
                                    %>
                                            <form action="SvUnirmeGrupo" method="POST" style="display: inline;">
                                                <input type="hidden" name="lector" value="<%= lector.getId() %>">
                                                <input type="hidden" name="grupo" value="<%= grupo.getId() %>">
                                                <input type="hidden" name="paginaOriginal" value="<%= "listarGrupos.jsp" %>">
                                                <button type="submit" class="btn btn-small btn-primary" style="display: inline; padding: 2px 8px; font-size: 0.85em;">Unirme</button>
                                            </form>
                                <%}%>
                                </center></td>
                                </tr>
                                <% }%>
                                </tbody>
                            </table>
                        </div>
                    </div> 
                </div> 
            </div>
        </div>

        <!-- Text Under Box -->

        <script src="resources/js/jquery-1.7.2.min.js"></script>
        <script src="resources/js/bootstrap.js"></script>
        <script src="resources/js/signin.js"></script>
    </body>
</html>
