<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="utf-8">
        <title>Ingreso - Ubooks</title>
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
            Cookie id_usuario = new Cookie("id_usuario", "0");
            response.addCookie(id_usuario);
        %>
        <div class="navbar navbar-fixed-top">	
            <div class="navbar-inner">		
                <div class="container">

                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>

                    <a class="brand" href="index.jsp">
                        Ubooks				
                    </a>		

                    <div class="nav-collapse">
                        <ul class="nav pull-right">
                            <li class="">						
                                <a href="registrarLector.jsp" class="">
                                    ¿Aún no está registrado?
                                </a>

                            </li>
                        </ul>				
                    </div><!--/.nav-collapse -->		
                </div> <!-- /container -->		
            </div> <!-- /navbar-inner -->	
        </div> <!-- /navbar -->

        <div class="account-container">	
            <div class="content clearfix">		
                <form action="SvIngresar" method="POST">		
                    <h1>Ingreso Usuario</h1>					
                    
                    <div class="login-fields">				
                        <p>Por favor ingrese sus credenciales</p>				
                        <div class="field">
                            <label for="nombreUsuario">Nombre de usuario:</label>
                            <input type="text" id="nombreUsuario" name="nombreUsuario" value="" placeholder="Nombre de usuario" class="login username-field" required/>
                        </div> <!-- /field -->

                        <div class="field">
                            <label for="contrasenia">Contraseña:</label>
                            <input type="password" id="contrasenia" name="contrasenia" value="" placeholder="Contraseña" class="login password-field" required/>
                        </div> <!-- /password -->				
                    </div> <!-- /login-fields -->

                    <div class="login-actions">				
                        <span class="login-checkbox">
                            <input id="Field" name="Field" type="checkbox" class="field login-checkbox" value="First Choice" tabindex="4" />
                            <label class="choice" for="Field">Recordar credenciales</label>
                        </span>									
                        <button class="button btn btn-success btn-large">Ingresar</button>				
                    </div> <!-- .actions -->

                </form>		
            </div> <!-- /content -->
        </div> <!-- /account-container -->

        <script src="resources/js/jquery-1.7.2.min.js"></script>
        <script src="resources/js/bootstrap.js"></script>
        <script src="resources/js/signin.js"></script>
    </body>
</html>




