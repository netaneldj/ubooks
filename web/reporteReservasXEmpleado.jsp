<%@page import="java.util.ArrayList"%>
<%@page import="Logica.Entidades.Reserva"%>
<%@page import="Logica.Entidades.Empleado"%>
<%@page import="Logica.Entidades.Huesped"%>
<%@page import="Logica.Entidades.Habitacion"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Reservas por Empleado - Ubooks</title>
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
            /*
            HttpSession sesion = request.getSession();
            String nombreUsuario = (String) request.getSession().getAttribute("nombreUsuario");

            if (nombreUsuario == null) {
                response.sendRedirect("index.jsp");
            } else {
            */ 
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
                                <a href="reportes.jsp" class="">
                                    <i class="icon-chevron-left"></i>
                                    Regresar a la pagina de reportes
                                </a>

                            </li>
                        </ul>
                    </div><!--/.nav-collapse -->	
                </div> <!-- /container -->
            </div> <!-- /navbar-inner -->
        </div> <!-- /navbar -->


        <div class="container-fluid cata-flex">
            <div class="row">
                <div class="span6">
                    <div class="widget widget-table action-table">
                        <div class="widget-header"> <i class="icon-th-list"></i>
                            <h3>Reservas por Empleado</h3>
                        </div>
                         <%
                                    ControladoraLogica controladoraLogica = new ControladoraLogica();
                            %>
                        <div class="widget-content">
                            <table class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th><center>ID</center></th>
                                <th><center>Empleado</center></th>
                                <th><center>Huesped</center></th>
                                <th><center>Habitacion</center></th>
                                <th><center>Check-in</center></th>
                                <th><center>Check-out</center></th>
                                <th><center>Cantidad huespedes</center></th>
                                <th><center>Monto total</center></th>
                                </tr>
                                </thead>

                                <tbody>
                                    <tr>
                                        <%
                                            List<Reserva> reservas = controladoraLogica.obtenerReservas();
                                            for (Reserva reserva : reservas) {
                                                if (reserva.getEmpleado().getId() == Integer.parseInt(request.getParameter("id_empleado"))) {
                                        %>
                                        <td><center><%=reserva.getId()%></center></td>
                                <td><center><%=reserva.getEmpleado().getNombre()+" "+reserva.getEmpleado().getApellido()%></center></td>
                                <td><center><%=reserva.getHuesped().getNombre()+" "+reserva.getHuesped().getApellido()%></center></td>
                                <td><center><%=reserva.getHabitacion().getPiso()+" "+reserva.getHabitacion().getTipo()+" "+reserva.getHabitacion().getTematica()%></center></td>
                                <td><center><%=controladoraLogica.convertirDateAString(reserva.getCheckIn())%></center></td>
                                <td><center><%=controladoraLogica.convertirDateAString(reserva.getCheckOut())%></center></td>
                                <td><center><%=reserva.getNroHuespedes()%></center></td>
                                <td><center>$<%=reserva.getMontoTotal()%></center></td>
                                </tr>
                                <%  }
                                   }%>
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
        <%
            //}
        %>
    </body>
</html>
