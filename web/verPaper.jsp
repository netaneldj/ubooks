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

<div class="main">
    <div class="main-inner">
        <div class="container">
            <div class="row">
                <div class="span6">
                    <div class="widget" style="border: 1px solid #ddd; padding: 20px; border-radius: 8px;">
                        <div class="widget-header">
                            <i class="icon-user"></i>
                            <h3> Paper</h3>
                        </div>
                        <%
                        if(lector.siguePaper(paper)){
                        %>                                        
                             <button type="submit" class="btn btn-small btn-primary" style="display: inline; padding: 2px 8px; font-size: 0.85em; color:black ">Guardado</button>
                            <% }else{
                        %>    
                                <form action="SvGuardarPaper" method="POST" style="display: inline;">
                                    <input type="hidden" name="lector" value="<%= lector.getId() %>">
                                    <input type="hidden" name="paper" value="<%= paper.getId() %>">
                                    <input type="hidden" name="paginaOriginal" value="<%= "verPaper.jsp" %>">
                                    <button type="submit" class="btn btn-small btn-primary" style="display: inline; padding: 2px 8px; font-size: 0.85em;">Guardar</button>
                                </form>
                                <%}%>
         
                                    <%
                        if(lector.paperLeido(paper)){
                        %>                                        
                             <button type="submit" class="btn btn-small btn-primary" style="display: inline; padding: 2px 8px; font-size: 0.85em; color:black ">Ya leido</button>
                            <% }else{
                        %>    
                                <form action="SvGuardarPaperLeido" method="POST" style="display: inline;">
                                    <input type="hidden" name="lector" value="<%= lector.getId() %>">
                                    <input type="hidden" name="paper" value="<%= paper.getId() %>">
                                    <input type="hidden" name="paginaOriginal" value="<%= "verPaper.jsp" %>">
                                    <button type="submit" class="btn btn-small btn-primary" style="display: inline; padding: 2px 8px; font-size: 0.85em;">Marcar como Leido</button>
                                </form>
                                <%}%>        
                                
                                
                                
                        <div class="widget-content">
                            <form>
                                <span class="rating" style="direction: ltr;">
                                    <% for (int i = 0; i < 5; i++) { %>
                                        <span style="color: <%= i < promedioValoracionPaper ? "gold" : "#ddd" %>; font-size: 30px;">★</span>
                                    <% } %>
                                </span>
                           
                                <div class="field">
                                    <label for="nombre">Nombre:</label>
                                    <input type="text" id="nombre" name="nombre" value="<%=paper != null ? paper.getNombre() : ""%>" class="login" readonly/>
                                </div>
                                <div class="field">
                                    <label for="doi">DOI:</label>  
                                    <input type="text" id="doi" name="doi" value="<%=paper != null ? paper.getDoi() : ""%>" class="login" readonly/>
                                </div>
                                <div class="field">
                                    <label for="genero">Género:</label>  
                                    <input type="text" id="genero" name="genero" value="<%=paper != null ? paper.getGenero() : ""%>" class="login" readonly/>
                                </div>
                                <div class="field">
                                    <label for="autor">Autor:</label>  
                                    <input type="text" id="autor" name="autor" value="<%=paper != null ? paper.getAutor().getNombre() + " " + paper.getAutor().getApellido() : ""%>" class="login" readonly/>
                                </div>
                                <div class="field">
                                    <label for="resumen">Resumen:</label>
                                    <textarea id="resumen" name="resumen" class="login" readonly><%=paper != null ? paper.getResumen() : ""%></textarea>
                                </div>                                                                                                                    
                            </form>
                        </div>
                    </div>
                </div>

                <div class="span6">
                    <div class="widget" style="border: 1px solid #ddd; padding: 20px; border-radius: 8px;">
                        <div class="widget-header">
                            <i class="icon-star"></i>
                            <h3>Calificación</h3>
                        </div>
                        <div class="widget-content">
                            <form action="submitRating.jsp" method="post">
                                <div class="rating">
                                    <input type="radio" name="valoracion" id="star5" value="5" />
                                    <label for="star5">★</label>
                                    <input type="radio" name="valoracion" id="star4" value="4" />
                                    <label for="star4">★</label>
                                    <input type="radio" name="valoracion" id="star3" value="3" />
                                    <label for="star3">★</label>
                                    <input type="radio" name="valoracion" id="star2" value="2" />
                                    <label for="star2">★</label>
                                    <input type="radio" name="valoracion" id="star1" value="1" required />
                                    <label for="star1">★</label>
                                </div>
                                <div class="field">
                                    <label for="comentario">Comentario:</label>
                                    <textarea id="comentario" name="comentario" rows="4" class="login"></textarea>
                                </div>
                                <input type="hidden" name="idPaper" value="<%= paper != null ? paper.getId() : "" %>"/>
                                <button type="submit" class="btn btn-primary">Enviar valoración</button>
                            </form>
                        </div>
                    </div>

                    <!-- Panel de valoraciones de otros usuarios -->
                    <div class="widget" style="border: 1px solid #ddd; padding: 20px; border-radius: 8px; margin-top: 20px;">
                        <div class="widget-header">
                            <i class="icon-comment"></i>
                            <h3>Valoraciones de otros usuarios</h3>
                        </div>
                        <div class="widget-content">
                            <% if (misValoracionesPaginadas != null && !misValoracionesPaginadas.isEmpty()) { %>
                                <ul class="list-group">
                                    <% for (Valoracion valoracion : misValoracionesPaginadas) { %>
                                        <li class="list-group-item">
                                            <strong><%= controladoraLogica.obtenerLectorPorID(valoracion.getLector().getId()).getNombre()%>:</strong>
                                            <span class="rating">
                                                <% for (int i = 0; i < 5; i++) { %>
                                                    <span style="color: <%= i < valoracion.getValoracionNumerica() ? "gold" : "#ddd" %>;">★</span>
                                                <% } %>
                                            </span>
                                            <p><%= valoracion.getComentario() %></p>
                                        </li>
                                    <% } %>
                                </ul>
                            <% } else { %>
                                <p>No hay valoraciones aún.</p>
                            <% } %>
                        </div>
                        <div class="pagination">
                            <% if (paginaValoracion > 1) { %>
                                <a href="verPaper.jsp?paginaValoracion=<%= paginaValoracion - 1 %>">&laquo; Anterior</a>
                            <% } %>
                            <span>Página <%= paginaValoracion %> de <%= (int) Math.ceil((double) totalValoraciones / itemsPorPagina) %></span>
                            <% if (paginaValoracion < (int) Math.ceil((double) totalValoraciones / itemsPorPagina)) { %>
                                <a href="verPaper.jsp?paginaValoracion=<%= paginaValoracion + 1 %>">Siguiente &raquo;</a>
                            <% } %>
                        </div>  
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<script src="resources/js/jquery-1.7.2.min.js"></script> 
<script src="resources/js/bootstrap.js"></script>

</html>
