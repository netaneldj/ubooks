<%@page import="Logica.Entidades.Empleado"%>
<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Actualizar Empleado - Reservas Hotel</title>
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
            <div class="row">
                <div class="span5">
                    <div class="account-container register ">
                        <div class="content clearfix widget">
                            <form action="SvModificarEmpleado" method="POST" id="1">
                                <h1>Actualizar Empleado</h1>			
                                <div class="login-fields">
                                    <p>Actualizar información:</p>
                                     <%
                                        ControladoraLogica controladoraLogica = new ControladoraLogica();
                                        String id_empleado = "";
                                        Cookie[] cookies = request.getCookies();
                                        for(Cookie cookie : cookies){
                                                if(cookie.getName().equals("id_empleado")) { 
                                                    id_empleado = cookie.getValue();
                                                    break;
                                                }
                                            }
                                        Empleado empleado = controladoraLogica.obtenerEmpleadoPorID(Integer.parseInt(id_empleado));
                                     %>
                                    <div class="field">
                                        <label for="nombre">Nombre:</label>
                                        <input type="text" id="nombre" name="nombre" value="<%=empleado.getNombre()%>" placeholder="Nombre" class="login" required/>
                                    </div> <!-- /field -->

                                    <div class="field">
                                        <label for="apellido">Apellido:</label>	
                                        <input type="text" id="apellido" name="apellido" value="<%=empleado.getApellido()%>" placeholder="Apellido" class="login" required/>
                                    </div> <!-- /field -->
                                    
                                    <div class="field">
                                        <label for="dni">DNI:</label>	
                                        <input type="number" id="dni" name="dni" value="<%=empleado.getDni()%>" placeholder="DNI" class="login" required/>
                                    </div> <!-- /field -->
                                    
                                    <div class="field">
                                        <label for="direccion">Dirección:</label>	
                                        <input type="text" id="direccion" name="direccion" value="<%=empleado.getDireccion()%>" placeholder="Dirección" class="login" required/>
                                    </div> <!-- /field -->
                                    
                                    <div class="field">
					<label for="nacimiento">Fecha de nacimiento:</label>	
					<input type="date" form="1" name="nacimiento" min="1900-01-01" max="2099-12-31" value="<%=controladoraLogica.convertirDateAString2(empleado.getNacimiento())%>" placeholder="" class="login" required/>
                                    </div> <!-- /field -->
                                    
                                    <div class="field">
                                        <label for="cargo">Cargo:</label>	
                                        <input type="text" id="cargo" name="cargo" value="<%=empleado.getCargo()%>" placeholder="Cargo" class="login" required/>
                                    </div> <!-- /field -->
                                    
                                    <div class="field">
                                        <label for="nombreUsuario">Nombre de usuario:</label>	
                                        <input type="text" id="nombreUsuario" name="nombreUsuario" value="<%=empleado.getUsuario().getNombreUsuario()%>" placeholder="Nombre de usuario" class="login" required/>
                                    </div> <!-- /field -->
                                    
                                    <div class="field">
                                        <label for="contrasenia">Contraseña:</label>	
                                        <input type="password" id="contrasenia" name="contrasenia" value="<%=empleado.getUsuario().getContrasenia()%>" placeholder="Contraseña" class="login" onkeyup='verificarContrasenia();' required/>
                                    </div> <!-- /field -->
                                    
                                    <div class="field">
					<label for="confirmar_contrasenia">Confirmar contraseña:</label>
					<input type="password" id="confirmar_contrasenia" name="confirmar_contrasenia" value="<%=empleado.getUsuario().getContrasenia()%>" placeholder="Confirmar contraseña" class="login" onkeyup='verificarContrasenia();' required/>
                                        <span id='mensaje'></span>
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
        <script src="resources/js/signup.js"></script>
        <%
        //    }
        %>
    </body>
</html>
