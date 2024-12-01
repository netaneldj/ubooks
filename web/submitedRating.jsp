<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Valoración Enviada</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 50px;
            max-width: 600px;
        }
        .alert {
            font-size: 18px;
            text-align: center;
        }
        .btn {
            display: block;
            width: 100%;
            font-size: 16px;
            margin-top: 20px;
        }
    </style>
</head>
<body style="font-family: Times New Roman">
    <div class="container">
            <div class="alert alert-success" role="alert">
                <strong>¡Éxito! Gracias por enviarnos tu valoracion.</strong> 
            </div>
        <a href="verPaper.jsp?id=<%= request.getParameter("id_paper") %>" class="btn btn-primary">
            Volver al Paper
        </a>
    </div>
</body>
</html>
