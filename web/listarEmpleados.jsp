<%@page import="Logica.Entidades.Empleado"%>
<%@page import="Logica.Entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Listar Empleados - Reservas Hotel</title>
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
                        Reservas Hotel				
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
                            <h3>Lista Empleados</h3>
                        </div>
                        <div class="widget-content">
                            <table class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th><center>ID</center></th>
                                <th><center>Nombre</center></th>
                                <th><center>Apellido</center></th>
                                <th><center>DNI</center></th>
                                <th><center>Dirección</center></th>
                                <th><center>Fecha de nacimiento</center></th>
                                <th><center>Cargo</center></th>
                                <th><center>Nombre de usuario</center></th>
                                <th><center>Contraseña</center></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <%
                                            ControladoraLogica controladoraLogica = new ControladoraLogica();
                                            List<Empleado> empleados = controladoraLogica.obtenerEmpleados();
                                            for (Empleado empleado : empleados) {
                                        %>
                                        <td><center><%=empleado.getId()%></center></td>
                                <td><center><%=empleado.getNombre()%></center></td>
                                <td><center><%=empleado.getApellido()%></center></td>
                                <td><center><%=empleado.getDni()%></center></td>
                                <td><center><%=empleado.getDireccion()%></center></td>
                                <td><center><%=controladoraLogica.convertirDateAString(empleado.getNacimiento())%></center></td>
                                <td><center><%=empleado.getCargo()%></center></td>
                                <td><center><%=empleado.getUsuario().getNombreUsuario()%></center></td>
                                <td><center><%=empleado.getUsuario().getContrasenia()%></center></td>
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
