<%@page import="Logica.Entidades.Paper"%>
<%@page import="Logica.Entidades.Lector"%>
<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Actualizar Paper - Ubooks</title>
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
                            <form action="SvModificarPaper" method="POST" id="1">
                                <h1>Actualizar Paper</h1>			
                                <div class="login-fields">
                                    <p>Actualizar información:</p>
                                     <%
                                        ControladoraLogica controladoraLogica = new ControladoraLogica();
                                        String id_paper = "";
                                        Cookie[] cookies = request.getCookies();
                                        for(Cookie cookie : cookies){
                                                if(cookie.getName().equals("id_paper")) { 
                                                    id_paper = cookie.getValue();
                                                    break;
                                                }
                                            }
                                        Paper paper = controladoraLogica.obtenerPaperPorID(Integer.parseInt(id_paper));
                                     %>
                                    <div class="field">
                                        <label for="doi">Doi:</label>
                                        <input type="text" id="doi" name="doi" value="<%=paper.getDoi()%>" placeholder="Doi" class="login" required/>
                                    </div> <!-- /field -->
                                    
                                    <div class="field">
                                        <label for="nombre">Nombre:</label>
                                        <input type="text" id="nombre" name="nombre" value="<%=paper.getNombre()%>" placeholder="Nombre" class="login" required/>
                                    </div> <!-- /field -->

                                    <div class="field">
                                        <label for="autor">Autor:</label>	
                                        <input type="text" id="autor" name="autor" value="<%=paper.getAutor()%>" placeholder="Autor" class="login" required/>
                                    </div> <!-- /field -->
                                    
                                    <div class="field">
                                        <label for="resumen">Resumen:</label>	
                                        <input type="text" id="resumen" name="resumen" value="<%=paper.getResumen()%>" placeholder="Resumen" class="login" required/>
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
