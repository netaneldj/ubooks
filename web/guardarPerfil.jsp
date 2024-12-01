<%-- 
    Document   : guardarPerfil
    Created on : 19 nov 2024, 16:39:38
    Author     : vzaca
--%>

<%@page import="java.util.Objects"%>
<%@page import="Logica.Entidades.Paper"%>
<%@page import="Logica.Entidades.GeneroPaper"%>
<%@page import="Logica.Entidades.IdiomaPaper"%>
<%@page import="Logica.Entidades.Usuario"%>
<%@page import="java.util.Calendar"%>
<%@page import="Logica.Entidades.Lector"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controlador.ControladoraLogica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Mi perfil - Ubooks</title>
        <!-- Meta and CSS links -->
        <style>
            /* Estilos CSS aquí */
        </style>
    </head>
<body style="font-family: Times New Roman">
    <%   
        ControladoraLogica controladoraLogica = new ControladoraLogica();
        HttpSession sesion = request.getSession();
        String id_usuario = "0";
        String nombreUsuario = "";
        String id_lector = "0";
        String id_paper = "";
        Lector lector = null; // Inicializamos lector
        Cookie[] cookies = request.getCookies();

        // Obtiene id_usuario y id_lector de las cookies
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id_usuario")) {
                    id_usuario = cookie.getValue();
                } else if (cookie.getName().equals("id_lector")) {
                    id_lector = cookie.getValue();
                } 
            }
        }
        
        // Verificar si id_usuario es válido, sino redirige a index.jsp
        if (id_usuario.equals("0")) {
            response.sendRedirect("index.jsp");
        } else {
            try {
                nombreUsuario = controladoraLogica.obtenerUsuarioPorID(Integer.parseInt(id_usuario)).getNombreUsuario();
                lector = controladoraLogica.obtenerLectorPorID(Integer.parseInt(id_lector));
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp"); 
            }
        }

        // Maneja la valoración enviada
        String nuevaBiografia = request.getParameter("biografia");
        controladoraLogica.actualizarBiografia(lector, nuevaBiografia);
        request.setAttribute("mensajeExito", "Tu biografia ha sido actulizada correctamente.");
        response.sendRedirect("perfilLector.jsp" );
    %>

    <!-- Contenido adicional del JSP -->
</body>
</html>
