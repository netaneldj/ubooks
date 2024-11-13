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
						<a href="index.jsp" class="">
							¿Ya tienes una cuenta? Ingresa ahora
						</a>
						
					</li>
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



<div class="account-container register">
	
	<div class="content clearfix">
		
		<form action="SvCrearGrupo" method="POST" id="1">
		
			<h1>Crear Grupo</h1>			
			
			<div class="login-fields">
				
				<p>Crear Grupo:</p>
                                
				<div class="field">
					<label for="nombre">Nombre:</label>	
					<input type="text" id="nombre" name="nombre" value="" placeholder="Nombre" class="login" required/>
				</div> <!-- /field -->                                
				
				<div class="field">
					<label for="nombre">Tema:</label>
					<input type="text" id="tema" name="tema" value="" placeholder="Tema" class="login" required/>
				</div> <!-- /field -->
				
			</div> <!-- /login-fields -->
			
			<div class="login-actions">					
				<button class="button btn btn-primary btn-large">Crear</button>
				
			</div> <!-- .actions -->
			
		</form>
		
	</div> <!-- /content -->
	
</div> <!-- /account-container -->


<!-- Text Under Box -->
<div class="login-extra">
	¿Ya tienes una cuenta? <a href="index.jsp">Ingresa ahora</a>
</div> <!-- /login-extra -->


<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/bootstrap.js"></script>

<script src="resources/js/signin.js"></script>
<script src="resources/js/signup.js"></script>
<%
        //    }
        %>
</body>

 </html>
