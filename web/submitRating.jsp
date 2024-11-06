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
        <title>Perfil - Ubooks</title>
        <!-- Meta and CSS links -->
        <style>
            /* Estilos CSS aquí */
        </style>
    </head>
<body>
    <%
        ControladoraLogica controladoraLogica = new ControladoraLogica();
        HttpSession sesion = request.getSession();
        String id_usuario = "0";
        String nombreUsuario = "";
        String id_lector = "0";
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
                response.sendRedirect("error.jsp"); // Redirige a una página de error específica si ocurre una excepción
            }
        }

        // Obtén el id del Paper y crea la cookie si está presente
        String idPaper = request.getParameter("idPaper");
        if (idPaper != null) {
            Cookie paperCookie = new Cookie("paper_id", idPaper);
            paperCookie.setMaxAge(60 * 60);
            response.addCookie(paperCookie);
        }

        // Maneja la valoración enviada
        String comentario = request.getParameter("comentario");
        String valoracionNumericaStr = request.getParameter("valoracion");
        int valoracionNumerica = valoracionNumericaStr != null ? Integer.parseInt(valoracionNumericaStr) : 0;

        if (valoracionNumerica > 0) {
            if (comentario == null && comentario.isEmpty()){
                comentario = "NULL";
            }
            controladoraLogica.insertarValoracion(Integer.parseInt(idPaper), Integer.parseInt(id_usuario), valoracionNumerica, comentario);
            request.setAttribute("mensajeExito", "Tu valoración ha sido enviada correctamente.");
            response.sendRedirect("submitedRating.jsp?id=" + idPaper);
        }

        // Mostrar mensaje de éxito si se envió una valoración
        String mensajeExito = (String) request.getAttribute("mensajeExito");
    %>

    <!-- Mostrar mensaje de éxito si está presente -->
    <% if (mensajeExito != null) { %>
        <div class="alert alert-success" role="alert">
            <strong>¡Éxito!</strong> <%= mensajeExito %>
        </div>
    <% } %>

    <!-- Contenido adicional del JSP -->
</body>
</html>
