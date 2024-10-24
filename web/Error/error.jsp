<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Error - Ubooks</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes"> 

        <link href="../resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="../resources/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />

        <link href="../resources/css/font-awesome.css" rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
        <link href="../resources/css/style.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>

                    <a class="brand" href="../index.jsp">
                        Ubooks				
                    </a>		

                    <div class="nav-collapse">
                        <ul class="nav pull-right">
                            <li class="">						
                                <a href="../index.jsp" class="">
                                    <i class="icon-chevron-left"></i>
                                    Regresar al ingreso
                                </a>
                            </li>
                        </ul>
                    </div><!--/.nav-collapse -->	
                </div> <!-- /container -->
            </div> <!-- /navbar-inner -->
        </div> <!-- /navbar -->

        <div class="container">
            <div class="row">
                <div class="span12">

                    <div class="error-container">
                        <h1> (ง︡'-'︠)ง </h1>
                        <h2>Ubooks error!</h2>
                        <h2>404 - Si estas aqui es porque fallo la operacion que realizaste!</h2>
				<div class="error-details">
					Volve a intentar regresando a la pagina de <a href="../index.jsp"> INGRESO</a>
					
				</div> <!-- /error-details -->
                        <div class="error-actions">
                            <a href="../index.jsp" class="btn btn-large btn-primary">
                                <i class="icon-chevron-left"></i>
                                &nbsp;
                                Regresar al ingreso						
                            </a>
                        </div> <!-- /error-actions -->			
                    </div> <!-- /error-container -->			
                </div> <!-- /span12 -->
            </div> <!-- /row -->
        </div> <!-- /container -->
        <script src="../resources/js/jquery-1.7.2.min.js"></script>
        <script src="../resources/js/bootstrap.js"></script>
    </body>
</html>
