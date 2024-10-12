<%@page import="Logica.Entidades.TipoHabitacion"%>
<%@page import="Logica.Entidades.TematicaHabitacion"%>
<%@page import="Logica.Entidades.Habitacion"%>
<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Actualizar Habitacion - Reservas Hotel</title>
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
                            <form action="SvModificarHabitacion" method="POST">
                                <h1>Actualizar Habitacion</h1>			
                                <div class="login-fields">
                                    <p>Actualizar información:</p>
                                      <%
                                        ControladoraLogica controladoraLogica = new ControladoraLogica();
                                        String id_habitacion = "";
                                        Cookie[] cookies = request.getCookies();
                                        for(Cookie cookie : cookies){
                                                if(cookie.getName().equals("id_habitacion")) { 
                                                    id_habitacion = cookie.getValue();
                                                    break;
                                                }
                                            }
                                        Habitacion habitacion = controladoraLogica.obtenerHabitacionPorID(Integer.parseInt(id_habitacion));
                                     %>

                                    <p>Tematica: <select name="tematica"> </p>
                                    <option selected><%=habitacion.getTematica()%></option>
                                <%
                                            for (TematicaHabitacion tematica : TematicaHabitacion.values()) {
                                                if (!tematica.equals(habitacion.getTematica())){
                                        %>
                                <option><%=tematica%></option>
                                <% }}%>
                                </select> <!-- /field -->
                                
                                <p>Tipo habitación: <select name="tipo"> </p>
                                <option selected><%=habitacion.getTipo()%></option>
                                <%
                                            for (TipoHabitacion tipo : TipoHabitacion.values()) {
                                                if (!tipo.equals(habitacion.getTipo())){
                                        %>
                                <option><%=tipo%></option>
                                <% }}%>
                                </select> <!-- /field -->
				
                                <div class="field">
					<label for="piso">Piso:</label>	
					<input type="number" id="piso" name="piso" value="<%=habitacion.getPiso()%>" placeholder="Piso" class="login" />
				</div> <!-- /field -->
                                
                                <div class="field">
					<label for="precio">Precio:</label>	
					<input type="number" step="0.01" id="precio" name="precio" value="<%=habitacion.getPrecio()%>" placeholder="Precio" class="login" />
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
