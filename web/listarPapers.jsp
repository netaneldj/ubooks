<%@page import="Logica.Entidades.Paper"%>
<%@page import="Logica.Entidades.Lector"%>
<%@page import="Logica.Entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>
    .align-middle {
        vertical-align: middle;
    }
    .name-btn-container {
        display: flex;
        align-items: center
    }
    .btn {
        margin-left: 10px; /* Espacio entre el nombre y el botón */
    }
</style>

<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Listar Papers - Ubooks</title>
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

    <body>
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
                                <a href="gestion.jsp" class="">
                                    <i class="icon-chevron-left"></i>
                                    Regresar a la pagina de gestion
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
                            <h3>Lista Papers</h3>
                        </div>
                        <div class="widget-content">
                            <table class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th><center>ID</center></th>
                                    <th><center>Doi</center></th>
                                    <th><center>Nombre</center></th>
                                    <th><center>Genero</center></th>
                                    <th><center>Autor</center></th>
                                    <th><center>Resumen</center></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <%
                                            ControladoraLogica controladoraLogica = new ControladoraLogica();
                                            List<Paper> papers = controladoraLogica.obtenerPapers();
                                            for (Paper paper : papers) {
                                        %>
                                <td><center><%=paper.getId()%></center></td>
                                <td><center><%=paper.getDoi()%></center></td>                            
                                <td class="align-middle">
                                    <div style="display: flex; justify-content: space-between; align-items: center;">
                                        <span style="margin-right: 10px;"><%= paper.getNombre() %></span>
                                        <form action="verPaper.jsp" method="get" style="display: inline;">
                                            <input type="hidden" name="id" value="<%= paper.getId() %>">
                                            <button type="submit" class="btn btn-small btn-primary" style="display: inline; padding: 2px 8px; font-size: 0.85em;">Ver</button>
                                        </form>
                                    </div>
                                </td>
                                <td><center><%=paper.getGenero()%></center></td>
                                <td><center><%=paper.getAutor().getNombre()+" "+paper.getAutor().getApellido()%></center></td>
                                <td><center><%=paper.getResumen()%></center></td>
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
