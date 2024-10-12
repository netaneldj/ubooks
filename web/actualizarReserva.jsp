<%@page import="Logica.Entidades.Habitacion"%>
<%@page import="Logica.Entidades.Huesped"%>
<%@page import="Logica.Entidades.Empleado"%>
<%@page import="Logica.Entidades.Reserva"%>
<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Actualizar Reserva - Reservas Hotel</title>
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
                <div class="span5 ">
                    <div class="account-container register ">
                        <div class="content clearfix widget">
                            <form action="SvModificarReserva" method="POST" id="1">
                                <h1>Actualizar Reserva</h1>			
                                <div class="login-fields">
                                    <p>Actualizar información:</p>
                                    
                                    <%
                                        ControladoraLogica controladoraLogica = new ControladoraLogica();
                                        String id_reserva = "";
                                        Cookie[] cookies = request.getCookies();
                                        for(Cookie cookie : cookies){
                                                if(cookie.getName().equals("id_reserva")) { 
                                                    id_reserva = cookie.getValue();
                                                    break;
                                                }
                                            }
                                        Reserva reserva = controladoraLogica.obtenerReservaPorID(Integer.parseInt(id_reserva));
                                     %>

                                    <p>Huesped: <select name="id_huesped"> </p>
                                    <option value=<%=reserva.getHuesped().getId()%> selected><%=reserva.getHuesped().getNombre()+" "+reserva.getHuesped().getApellido()%></option>
                                    <%
                                                List<Huesped> huespedes = controladoraLogica.obtenerHuespedes();
                                                for (Huesped huesped : huespedes) {
                                                    if (huesped.getId() != reserva.getHuesped().getId()){
                                    %>
                                    <option value=<%=huesped.getId()%>><%=huesped.getNombre()+" "+huesped.getApellido()%></option>
                                    <% }}%>
                                    </select> <!-- /field -->
                                    
                                    <p>Habitación: <select name="id_habitacion"> </p>
                                    <option value=<%=reserva.getHabitacion().getId()%> selected><%=reserva.getHabitacion().getPiso()+" "+reserva.getHabitacion().getTipo()+" "+reserva.getHabitacion().getTematica()%></option>
                                    <%
                                                List<Habitacion> habitaciones = controladoraLogica.obtenerHabitaciones();
                                                for (Habitacion habitacion : habitaciones) {
                                                    if (habitacion.getId() != reserva.getHabitacion().getId()){
                                    %>
                                    <option value=<%=habitacion.getId()%>><%=habitacion.getPiso()+" "+habitacion.getTipo()+" "+habitacion.getTematica()%></option>
                                    <% }}%>
                                    </select> <!-- /field -->

                                    <div class="field">
					<label for="checkin">Fecha de check-in:</label>	
					<input type="date" form="1" name="checkin" min="2000-01-01" max="2099-12-31" value="<%=controladoraLogica.convertirDateAString2(reserva.getCheckIn())%>" placeholder="" class="login" />
				</div> <!-- /field -->
                                
                                <div class="field">
					<label for="checkout">Fecha de check-out:</label>	
					<input type="date" form="1" name="checkout" min="2000-01-01" max="2099-12-31" value="<%=controladoraLogica.convertirDateAString2(reserva.getCheckOut())%>" placeholder="" class="login" />
				</div> <!-- /field -->
                                
                                <div class="field">
					<label for="nro_huespedes">Cantidad huespedes:</label>	
					<input type="number" id="nro_huespedes" name="nro_huespedes" value="<%=reserva.getNroHuespedes()%>" placeholder="Cantidad huespedes:" class="login" />
				</div> <!-- /field -->

                                </div> <!-- /login-fields -->

                                <div class="login-actions">
                                    <button class="button btn btn-primary btn-large">Actualizar información</button>
                                </div> <!-- .actions -->
                            </form>
                        </div> <!-- /content -->
                    </div> <!-- /account-container -->

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
