<%@page import="Logica.Entidades.ComentarioGrupo"%>
<%@page import="Logica.Entidades.GeneroPaper"%>
<%@page import="Logica.Entidades.Usuario"%>
<%@page import="Logica.Entidades.Lector"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  
 <head>
    <meta charset="utf-8">
    <title>Crear Grupo - Ubooks</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"> 
    
<link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="resources/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />

<link href="resources/css/font-awesome.css" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
    
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<link href="resources/css/pages/signin.css" rel="stylesheet" type="text/css">

</head>

<body>
	<%
            ControladoraLogica controladoraLogica = new ControladoraLogica();
            HttpSession sesion = request.getSession();
            String idComentario = request.getParameter("comentario");
            String idUsuario = request.getParameter("usuario");
            String paginaOriginal = request.getParameter("paginaOriginal");
            ComentarioGrupo comentario = controladoraLogica.obtenerComentarioGrupoPorId(Integer.valueOf(idComentario));
            
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
	
		</div> <!-- /container -->
		
	</div> <!-- /navbar-inner -->
	
</div> <!-- /navbar -->



<div class="account-container register">
	
	<div class="content clearfix">
		
		<form action="SvResponderDiscusiónGrupo" method="POST" id="1">
		
			<h1>Responder Discusion</h1>			
			
			<div class="login-fields">
				
				<p>Responder Discucion:</p>
                                
                                <p><%= comentario.getCreador().getNombre() %>:</p>
                                 <p><%= comentario.getTitulo() %></p>
                                  <p> <%= comentario.getComentario() %></p>
                                                              
				
				<div class="field">
					<label for="nombre">Respuesta:</label>
					<textarea id="desc" name="resp" rows="4" class="login"></textarea>
				</div> <!-- /field -->
                                
                               
				
			</div> <!-- /login-fields -->
                        
			<input type="hidden" name="comentario" value="<%= comentario.getId() %>">
                        <input type="hidden" name="lector" value="<%= idUsuario %>">
                        <input type="hidden" name="paginaOriginal" value="<%= paginaOriginal %>">
                        
			<div class="login-actions">					
				<button class="button btn btn-primary btn-large">responder</button>
				
			</div> <!-- .actions -->
			
		</form>
		
	</div> <!-- /content -->
	
</div> <!-- /account-container -->

<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/bootstrap.js"></script>

<script src="resources/js/signin.js"></script>
<script src="resources/js/signup.js"></script>
<%
        //    }
        %>
</body>

 </html>
