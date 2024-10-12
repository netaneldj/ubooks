<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Gestión - Reservas Hotel</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes">    
        <link href="resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/bootstrap-responsive.min.css" rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
        <link href="resources/css/font-awesome.css" rel="stylesheet">
        <link href="resources/css/style.css" rel="stylesheet">
        <link href="resources/css/pages/plans.css" rel="stylesheet"> 
    </head>

    <body>
         <%
        ControladoraLogica controladoraLogica = new ControladoraLogica();
        HttpSession sesion = request.getSession();
        String id_usuario = "0";
        String nombreUsuario = "";
        Cookie[] cookies = request.getCookies();
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
                            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                        class="icon-user"></i> 
                                    <%=nombreUsuario%>
                                    <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="index.jsp" >Cerrar sesion</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div><!--/.nav-collapse -->	
                </div> <!-- /container -->
            </div> <!-- /navbar-inner -->
        </div> <!-- /navbar -->

        <div class="subnavbar">
            <div class="subnavbar-inner">
                <div class="container">
                    <ul class="mainnav">
                         <li class="active">
                            <a href="gestion.jsp">
                                <i class="icon-list-alt"></i>
                                <span>Gestion</span> 
                            </a>
                        </li>
                        <li class="active">
                            <a href="reportes.jsp">
                                <i class="icon-signal"></i>
                                <span>Reportes</span> 
                            </a>
                        </li>
                   </ul>
                </div> <!-- /container -->
            </div> <!-- /subnavbar-inner -->
        </div> <!-- /subnavbar -->

        <div class="main">
            <div class="main-inner">
                <div class="container">
                    <div class="row">
                        <div class="span12">
                            <div class="widget">
                                <div class="widget-header">
                                    <i class="icon-th-large"></i>
                                    <h3>Secciones a operar</h3>
                                </div> <!-- /widget-header -->

                                <div class="widget-content">
                                    <div class="pricing-plans plans-4">
                                        
                                        
                                        <!-- EMPLEADOS -->
                                        <div class="plan-container">
                                            <div class="plan green">
                                                <div class="plan-header">
                                                    <div class="plan-title">
                                                        Empleados del hotel	        		
                                                    </div> <!-- /plan-title -->

                                                    <div class="plan-price">
                                                        <a href="registrarEmpleado.jsp"><img src="./resources/img/empleado.png" style="max-width:100%;height:auto;"></a><span class="term"></span>
                                                    </div> <!-- /plan-price -->

                                                </div> <!-- /plan-header -->	        

                                                <div class="plan-features">
                                                    <ul>
                                                        <li><strong>Sección</strong> dedicada a la gestión de los empleados del hotel.</li>
                                                        <li>Aqui puedes</li>
                                                        <li>Crear, listar, modificar y borrar</li>
                                                        <li>empleados y usuarios.</li>
                                                    </ul>
                                                </div> <!-- /plan-features -->

                                                <div class="plan-actions">				
                                                    <a href="registrarEmpleado.jsp" class="btn">Registrar</a>				
                                                </div> <!-- /plan-actions -->
                                                <div class="plan-actions">				
                                                    <a href="listarEmpleados.jsp" class="btn">Listar</a>				
                                                </div> <!-- /plan-actions -->
                                                <div class="plan-actions">				
                                                    <a href="modificarEmpleado.jsp" class="btn">Modificar</a>				
                                                </div> <!-- /plan-actions -->
                                                <div class="plan-actions">				
                                                    <a href="eliminarEmpleado.jsp" class="btn">Eliminar</a>				
                                                </div> <!-- /plan-actions -->
                                            </div> <!-- /plan -->
                                        </div> <!-- /plan-container -->
                                        
                                        <!-- HUESPEDES -->
                                        <div class="plan-container">
                                            <div class="plan green">
                                                <div class="plan-header">

                                                    <div class="plan-title">
                                                        Huespedes del hotel	        		
                                                    </div> <!-- /plan-title -->

                                                    <div class="plan-price">
                                                        <a href="registrarHuesped.jsp"><img src="./resources/img/huesped.png" style="max-width:100%;height:auto;"></a><span class="term"></span>
                                                    </div> <!-- /plan-price -->

                                                </div> <!-- /plan-header -->	       

                                                <div class="plan-features">
                                                    <ul>
                                                        <li><strong>Sección</strong> dedicada a la gestion de los huespedes del hotel.</li>
                                                        <li>Aqui puedes</li>
                                                        <li>Crear, listar, modificar y borrar</li>
                                                        <li>huespedes.</li>
                                                    </ul>
                                                </div> <!-- /plan-features -->

                                                <div class="plan-actions">				
                                                    <a href="registrarHuesped.jsp" class="btn">Registrar</a>				
                                                </div> <!-- /plan-actions -->
                                                <div class="plan-actions">				
                                                    <a href="listarHuespedes.jsp" class="btn">Listar</a>				
                                                </div> <!-- /plan-actions -->
                                                <div class="plan-actions">				
                                                    <a href="modificarHuesped.jsp" class="btn">Modificar</a>				
                                                </div> <!-- /plan-actions -->
                                                <div class="plan-actions">				
                                                    <a href="eliminarHuesped.jsp" class="btn">Eliminar</a>				
                                                </div> <!-- /plan-actions -->
                                            </div> <!-- /plan -->
                                        </div> <!-- /plan-container -->
                                        
                                        <!-- RESERVAS -->
                                        <div class="plan-container">
                                            <div class="plan green">
                                                <div class="plan-header">
                                                    <div class="plan-title">
                                                        Reservas del hotel	        		
                                                    </div> <!-- /plan-title -->
                                                    <div class="plan-price">
                                                        <a href="registrarReserva.jsp"><img src="./resources/img/reserva.png" style="max-width:100%;height:auto;"></a><span class="term"></span>
                                                    </div> <!-- /plan-price -->
                                                </div> <!-- /plan-header -->	          

                                                <div class="plan-features">
                                                    <ul>					
                                                        <li><strong>Sección</strong> dedicada a la gestion de las reservas del hotel.</li>
                                                        <li>Aqui puedes</li>
                                                        <li>Crear, listar, modificar y borrar</li>
                                                        <li>reservas.</li>
                                                    </ul>
                                                </div> <!-- /plan-features -->

                                                <div class="plan-actions">				
                                                    <a href="registrarReserva.jsp" class="btn">Registrar</a>				
                                                </div> <!-- /plan-actions -->
                                                <div class="plan-actions">				
                                                    <a href="listarReservas.jsp" class="btn">Listar</a>				
                                                </div> <!-- /plan-actions -->
                                                <div class="plan-actions">				
                                                    <a href="modificarReserva.jsp" class="btn">Modificar</a>				
                                                </div> <!-- /plan-actions -->
                                                <div class="plan-actions">				
                                                    <a href="eliminarReserva.jsp" class="btn">Eliminar</a>				
                                                </div> <!-- /plan-actions -->
                                            </div> <!-- /plan -->
                                        </div> <!-- /plan-container -->
                                        
                                        <!-- HABITACIONES -->
                                        <div class="plan-container">
                                            <div class="plan green">
                                                <div class="plan-header">
                                                    <div class="plan-title">
                                                        Habitaciones del hotel	        		
                                                    </div> <!-- /plan-title -->
                                                    <div class="plan-price">
                                                        <a href="registrarHabitacion.jsp"><img src="./resources/img/habitacion.png" style="max-width:100%;height:auto;"></a><span class="term"></span>
                                                    </div> <!-- /plan-price -->
                                                </div> <!-- /plan-header -->	          

                                                <div class="plan-features">
                                                    <ul>					
                                                        <li><strong>Sección</strong> dedicada a la gestion de las habitaciones del hotel.</li>
                                                        <li>Aqui puedes</li>
                                                        <li>Crear, listar, modificar y borrar</li>
                                                        <li>habitaciones.</li>
                                                    </ul>
                                                </div> <!-- /plan-features -->

                                                <div class="plan-actions">				
                                                    <a href="registrarHabitacion.jsp" class="btn">Registrar</a>				
                                                </div> <!-- /plan-actions -->
                                                <div class="plan-actions">				
                                                    <a href="listarHabitaciones.jsp" class="btn">Listar</a>				
                                                </div> <!-- /plan-actions -->
                                                <div class="plan-actions">				
                                                    <a href="modificarHabitacion.jsp" class="btn">Modificar</a>				
                                                </div> <!-- /plan-actions -->
                                                <div class="plan-actions">				
                                                    <a href="eliminarHabitacion.jsp" class="btn">Eliminar</a>				
                                                </div> <!-- /plan-actions -->
                                            </div> <!-- /plan -->
                                        </div> <!-- /plan-container -->
                                    </div> <!-- /pricing-plans -->
                                </div> <!-- /widget-content -->
                            </div> <!-- /widget -->					
                        </div> <!-- /span12 -->     	
                    </div> <!-- /row -->
                </div> <!-- /container -->
            </div> <!-- /main-inner -->
        </div> <!-- /main -->

        <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="resources/js/jquery-1.7.2.min.js"></script>
        <script src="resources/js/bootstrap.js"></script>
        <script src="resources/js/base.js"></script>   
    </body>
</html>
