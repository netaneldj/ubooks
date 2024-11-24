<%@page import="java.util.Collections"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Objects"%>
<%@page import="Logica.Entidades.Paper"%>
<%@page import="Logica.Entidades.Valoracion"%>
<%@page import="Logica.Entidades.Lector"%>
<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page import="java.util.List"%>
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
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
    <link href="resources/css/font-awesome.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <link href="resources/css/pages/dashboard.css" rel="stylesheet">
    <style>
        .rating {
            display: flex;
            direction: rtl;
            justify-content: flex-start;
            margin-bottom: 15px;
        }
        .rating input {
            display: none;
        }
        .rating label {
            font-size: 30px;
            color: #ddd;
            cursor: pointer;
        }
        .rating input:checked ~ label,
        .rating input:hover ~ label {
            color: gold;
        }
        .rating label:hover,
        .rating label:hover ~ label {
            color: gold;
        }
        .list-group-item .rating {
            direction: ltr;
            font-size: 20px;
        }
    </style>
</head>

<%
    ControladoraLogica controladoraLogica = new ControladoraLogica();
    HttpSession sesion = request.getSession();
    String id_usuario = "0";
    String nombreUsuario = "";
    String id_paper = "";
    Paper paper = null;
    Lector lector = null;
    Cookie[] cookies = request.getCookies();
    for(Cookie cookie : cookies){
        if(cookie.getName().equals("id_usuario")) { 
            id_usuario = cookie.getValue();
        } else if(cookie.getName().equals("id_paper")) { 
                id_paper = cookie.getValue();
        }
    }
    if (id_usuario.equals("0")) {  
        response.sendRedirect("index.jsp");
    } else {
        nombreUsuario = controladoraLogica.obtenerUsuarioPorID(Integer.parseInt(id_usuario)).getNombreUsuario();
        lector = controladoraLogica.obtenerLectorPorIdUsuario(Integer.parseInt(id_usuario));
        paper = controladoraLogica.obtenerPaperPorID(Integer.parseInt(id_paper));
    }    
     /****************** PAGINADO ******************/
    int itemsPorPagina = 2; // Número de items por página
    int paginaValoracion = request.getParameter("paginaValoracion") != null ? Integer.parseInt(request.getParameter("paginaValoracion")) : 1;
    int inicioValoracion = (paginaValoracion - 1) * itemsPorPagina;
    List<Valoracion> misValoraciones = controladoraLogica.obtenerValoracionesPorPaper(Integer.parseInt(id_paper));
    int totalValoraciones = misValoraciones.size();
    List<Valoracion> misValoracionesPaginadas = misValoraciones.subList(inicioValoracion, Math.min(inicioValoracion + itemsPorPagina, totalValoraciones));
    int promedioValoracionPaper = Objects.isNull(paper.getPromedioValoracionNumerica()) ? 0 : (int) Math.round(paper.getPromedioValoracionNumerica());
    List<Lector> ranking = controladoraLogica.obtenerLectores();
    Collections.sort(ranking, new Comparator<Lector>() {
            @Override
            public int compare(Lector l1, Lector l2) {
                return Integer.compare(l1.getPuntos(), l2.getPuntos());
            }
      });

%>

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container"> 
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a>
            <a class="brand" href="inicio.jsp">Ubooks</a>
            <div class="nav-collapse">
                <ul class="nav pull-right">
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-user"></i> 
                            <%=nombreUsuario%>
                            <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="perfilLector.jsp">Perfil</a></li>
                            <li><a href="index.jsp">Cerrar sesión</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="subnavbar">
    <div class="subnavbar-inner">
        <div class="container">
            <ul class="mainnav">
                <li class="active">
                    <div class="nav-collapse">
                        <ul class="nav">
                            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                        class="icon-book"></i><span>Lectores</span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="registrarLector.jsp" >Registrar</a></li>
                                    <li><a href="listarLectores.jsp" >Listar</a></li>
                                    <li><a href="buscarLectorPorNombre.jsp" >Buscar</a></li>
                                    <li><a href="modificarLector.jsp" >Modificar</a></li>
                                    <li><a href="eliminarLector.jsp" >Eliminar</a></li>
                                </ul>                                    
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="active">
                    <div class="nav-collapse">
                        <ul class="nav">
                            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                        class="icon-pencil"></i><span>Papers</span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="registrarPaper.jsp" >Registrar</a></li>
                                    <li><a href="listarPapers.jsp" >Listar</a></li>
                                    <li><a href="buscarPaper.jsp" >Buscar</a></li>
                                    <li><a href="modificarPaper.jsp" >Modificar</a></li>
                                    <li><a href="eliminarPaper.jsp" >Eliminar</a></li>
                                </ul>                                    
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="active">
                    <div class="nav-collapse">
                        <ul class="nav">
                            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                        class="icon-user"></i><span>Grupos</span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="registrarGrupo.jsp" >Registrar</a></li>
                                    <li><a href="listarGrupos.jsp" >Listar</a></li>
                                </ul>                                    
                            </li>
                        </ul>
                    </div>
                </li>                        
            </ul>
        </div>
        <!-- /container --> 
    </div>
    <!-- /subnavbar-inner --> 
</div>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Ranking</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <style>
        table {
            width: 50%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            text-align: center;
            padding: 8px;
        }
        th {
            background-color: #f4f4f4;
        }
    </style>
</head>
<body>
    <h1 style="text-align: center;">Ranking por Puntos</h1>
    <table>
        <thead>
            <tr>
                <th>Posición</th>
                <th>Nombre</th>
                <th>Puntos</th>
            </tr>
        </thead>
        <tbody>
            <%
                int posicion = 1;
                for (Lector un_lector : ranking) {
            %>
                <tr>
                    <td><%= posicion++ %></td>
                    <td><%= un_lector.getNombre() %></td>
                    <td><%= un_lector.getPuntos() %></td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>


<script src="resources/js/jquery-1.7.2.min.js"></script> 
<script src="resources/js/bootstrap.js"></script>

</html>
