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
    String id_lector = "0";
    Lector lector;
    Cookie[] cookies = request.getCookies();
    for(Cookie cookie : cookies){
        if(cookie.getName().equals("id_usuario")) { 
            id_usuario = cookie.getValue();
        } else if(cookie.getName().equals("id_lector")) { 
            id_lector = cookie.getValue();
        }
    }
    if (id_usuario.equals("0"))  
        response.sendRedirect("index.jsp");
    else
        nombreUsuario = controladoraLogica.obtenerUsuarioPorID(Integer.parseInt(id_usuario)).getNombreUsuario();
        lector = controladoraLogica.obtenerLectorPorID(Integer.parseInt(id_lector));
        
    String idPaper = request.getParameter("id");
    Paper paper = null;
    if (idPaper != null) {
        // Crear la cookie con el ID del paper
        Cookie paperCookie = new Cookie("paper_id", idPaper);
        paperCookie.setMaxAge(60 * 60); // 1 hora de duración
        response.addCookie(paperCookie);

        // obtener el paper
        paper = controladoraLogica.obtenerPaperPorID(Integer.parseInt(idPaper));
    }

    // Obtener lista de valoraciones del Paper
    List<Valoracion> valoraciones = controladoraLogica.obtenerValoracionesPorPaper(Integer.parseInt(idPaper));
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
                    <a href="inicio.jsp">
                        <i class="icon-home"></i>
                        <span>Inicio</span> 
                    </a>
                </li>                           
                <li class="active">
                    <a href="gestion.jsp">
                        <i class="icon-list-alt"></i>
                        <span>Gestión</span> 
                    </a>
                </li>                     
            </ul>
        </div>
    </div>
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
                        <div class="widget-content">
                            <form>
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
                            <% if (valoraciones != null && !valoraciones.isEmpty()) { %>
                                <ul class="list-group">
                                    <% for (Valoracion valoracion : valoraciones) { %>
                                        <li class="list-group-item">
                                            <strong><%= controladoraLogica.obtenerUsuarioPorID(valoracion.getIdUsuario()).getNombreUsuario() %>:</strong>
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
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<script src="resources/js/jquery-1.7.2.min.js"></script> 
<script src="resources/js/bootstrap.js"></script>

</html>
