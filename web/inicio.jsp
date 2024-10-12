<%@page import="java.util.Calendar"%>
<%@page import="Logica.Entidades.Huesped"%>
<%@page import="Logica.Entidades.Reserva"%>
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
                <div class="container"> <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"><span
                            class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span> </a><a class="brand" href="inicio.jsp">Ubooks </a>
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
                                    <h3> Huespedes</h3>
                                </div>
                                <!-- /widget-header -->
                                <div class="widget-content">
                                    <ul class="messages_layout">
                                        <%      
                                                    List<Huesped> huespedes = controladoraLogica.obtenerHuespedes();
                                                            for (Huesped huesped : huespedes) {
                                                %>
                                                <li class="from_user left"> <a href="listarHuespedes.jsp" class="avatar"><img src="resources/img/mensaje_huesped.png"/></a>
                                                <div class="message_wrap"> <span class="arrow"></span>
                                                        <div class="info"> <a class="name"><%=huesped.getNombre()+" "+huesped.getApellido()%></a>
                                                        <div class="options_arrow">
                                                            <div class="dropdown pull-right"> <a class="dropdown-toggle " id="dLabel" role="button" data-toggle="dropdown" data-target="#" href="#"> <i class=" icon-caret-down"></i> </a>
                                                                <ul class="dropdown-menu " role="menu" aria-labelledby="dLabel">
                                                                    <li><a href="registrarHuesped.jsp"><i class=" icon-plus-sign icon-large"></i> Registrar</a></li>
                                                                    <li><a href="modificarHuesped.jsp"><i class=" icon-edit icon-large"></i> Modificar</a></li>
                                                                    <li><a href="borrarHuesped.jsp"><i class=" icon-trash icon-large"></i> Borrar</a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="text"><%=huesped.getProfesion()%></div>
                                                </div>
                                                </li>
                                                <% }%>
                                    </ul>
                                </div>
                                <!-- /widget-content --> 
                            </div>
                            <!-- /widget --> 
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
                                        <a href="gestion.jsp" class="shortcut"><i class="shortcut-icon icon-list-alt"></i><span class="shortcut-label">Gestión</span> </a>
                                        <a href="reportes.jsp" class="shortcut"><i class="shortcut-icon icon-signal"></i><span class="shortcut-label">Reportes</span>
                                        <!-- /shortcuts --> 
                                    </div>
                                    <!-- /widget-content --> 
                                </div>
                                <div class="widget widget-nopad">
                                    <div class="widget-header"> <i class="icon-list-alt"></i>
                                        <h3> Reservas</h3>
                                    </div>
                                    <!-- /widget-header -->
                                    <div class="widget-content">
                                        <ul class="news-items">
                                                <% 
                                                    Calendar calendario = Calendar.getInstance();
                                                    List<Reserva> reservas = controladoraLogica.obtenerReservas();
                                                            for (Reserva reserva : reservas) {
                                                                calendario.setTime(reserva.getCheckIn());
                                                %>
                                                <li>
                                                    <div class="news-item-date"> <span class="news-item-day"><%=calendario.get(Calendar.DAY_OF_MONTH)%></span> <span class="news-item-month"><%=ControladoraLogica.obtenerNombreDeNumeroMes(calendario.get(Calendar.MONTH))%></span> </div>
                                                    <div class="news-item-detail"> <%=reserva.getHuesped().getNombre()+" "+reserva.getHuesped().getApellido()%>
                                                        <p class="news-item-preview"><%="PISO "+reserva.getHabitacion().getPiso()+" - "+reserva.getHabitacion().getTipo()+" - "+reserva.getHabitacion().getTematica()%></p>
                                                    </div>
                                                </li>
                                                <% }%>
                                        </ul>
                                    </div>
                                    <!-- /widget-content --> 
                                </div>
                                <!-- /widget-nopad -->
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
