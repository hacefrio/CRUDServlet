package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Acciones;

@WebServlet(name = "ProcesaEliminar", urlPatterns = {"/ProcesaEliminar"})
public class ProcesaEliminar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int n = 0;
        Acciones acciones=new Acciones();
        String rut = request.getParameter("txtRut"); //Lee el rut
        PrintWriter out = response.getWriter();
        n=acciones.Eliminar(rut);
        if (n == 0) {
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ProcesaInsertar</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("NO se elimino el registro");
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        } else {
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ProcesaInsertar</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("SI se elimino el registro");
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
