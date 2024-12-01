<%@page import="Logica.Entidades.IdiomaPaper"%>
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
    <title>Registrar Paper - Ubooks</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"> 
    
<link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="resources/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />

<link href="resources/css/font-awesome.css" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
    
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<link href="resources/css/pages/signin.css" rel="stylesheet" type="text/css">

</head>

<body style="font-family: Times New Roman">
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
	
		</div> <!-- /container -->
		
	</div> <!-- /navbar-inner -->
	
</div> <!-- /navbar -->



<div class="account-container register">
	
	<div class="content clearfix">
		
		<form action="SvAgregarPaper" method="POST" id="1">
		
			<h1>Registrar Paper</h1>			
			
			<div class="login-fields">
				
				<p>Importar un paper:</p>
                                
				<div class="field">
					<label for="doi">Doi:</label>	
					<input type="text" id="doi" name="doi" value="" placeholder="Doi" class="login" required/>
				</div> <!-- /field -->                                
				
				<div class="field">
					<label for="nombre">Nombre</label>
					<input type="text" id="nombre" name="nombre" value="" placeholder="Nombre" class="login" required/>
				</div> <!-- /field -->
                                
                                <p>Autor: <select name="id_lector">
                                <%
                                    ControladoraLogica controladoraLogica = new ControladoraLogica();        
                                    List<Lector> autores = controladoraLogica.obtenerAutores();
                                            for (Lector autor : autores) {
                                %>
                                <option value=<%=autor.getId()%>><%=autor.getNombre()+" "+autor.getApellido()%></option>
                                <% }%>
                                </select>  </p><!-- /field -->    
                               <p>Genero: <select name="genero"> 
                                <%
                                            for (GeneroPaper genero : GeneroPaper.values()) {
                                        %>
                                <option><%=genero%></option>
                                <% }%>
                                </select></p> <!-- /field -->
                                
                                <p>Idioma: <select name="idioma">
                                <%
                                            for (IdiomaPaper idioma : IdiomaPaper.values()) {
                                        %>
                                <option><%=idioma%></option>
                                <% }%>
                                </select> </p> <!-- /field -->                                
                                
				<div class="field">
					<label for="resumen">Resumen:</label>
					<input type="text" id="resumen" name="resumen" value="" placeholder="Resumen" class="login" required/>
				</div> <!-- /field -->
				
			</div> <!-- /login-fields -->
			
			<div class="login-actions">					
				<button class="button btn btn-primary btn-large">Registrar</button>
				
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
