<%@page import="Logica.Entidades.Paper"%>
<%@page import="Logica.Entidades.Lector"%>
<%@page import="Logica.Entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Modificar Paper - Ubooks</title>
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
            String id_usuario = "0";
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("id_usuario")) { 
                    id_usuario = cookie.getValue();
                    break;
                }
            }
            if (id_usuario.equals("0"))  
                response.sendRedirect("index.jsp");
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
                                <a href="inicio.jsp" class="">
                                    <i class="icon-chevron-left"></i>
                                    Regresar al inicio
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
                            <form action="SvModificarPaper" method="GET">
                                <h1>Modificar Paper</h1>			
                                <div class="login-fields">
                                    <p>Paper: <select name="id_paper"> </p>
                                    <%
                                        ControladoraLogica controladoraLogica = new ControladoraLogica();        
                                        List<Paper> papers = controladoraLogica.obtenerPapers();
                                                for (Paper paper : papers) {
                                    %>
                                    <option value=<%=paper.getId()%>><%=paper.getNombre()+" "+paper.getAutor().getNombre()+" "+paper.getAutor().getApellido()%></option>
                                    <% }%>
                                    </select> <!-- /field -->

                                </div> <!-- /login-fields -->

                                <div class="login-actions">
                                    <button class="button btn btn-primary btn-large">Modificar</button>
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
    </body>
</html>
