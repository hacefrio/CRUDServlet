package controlador;

import modelo.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Acciones;

@WebServlet(name = "ListaInformacion", urlPatterns = {"/ListaInformacion"})
public class ListaInformacion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Vector<Cliente> dat = new Vector<Cliente>();
        Acciones acciones = new Acciones();
        dat = acciones.Listar();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListaInformacion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table align='center' border='1'>");
            out.println("<tr><td>Rut</td><td>Años</td><td>Sexo</td></tr>");
            String sexoTipo = "";
            int i = 0;
            for (Cliente dato : dat) {
                if (dato.getSexo() == 1) {
                    sexoTipo = "Mujer";
                } else {
                    sexoTipo = "Hombre";
                }
                out.println("<tr><td>" + dato.getRut() + "</td><td>" + dato.getEdad()
                        + "</td><td>" + sexoTipo + "</td></tr>");
                i++;
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
