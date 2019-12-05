package proc;

import conec.ConexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProcesaActualizar", urlPatterns = {"/ProcesaActualizar"})
public class ProcesaActualizar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int n = 0;
        String rut = request.getParameter("rut");
        int edad = Integer.parseInt(request.getParameter("edad"));
        int sexo = Integer.parseInt(request.getParameter("sexo"));
        PrintWriter out = response.getWriter();
        try {
            Class.forName(ConexionBD.getDriver());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcesaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection con = DriverManager.getConnection(ConexionBD.getUrl(),
                    ConexionBD.getUser(),
                    ConexionBD.getPass());
            Statement st = con.createStatement();
            String SQL = " update cliente "
                    + " set edad =" + edad + ","
                    + " sexo=" + sexo
                    + " where rut='" + rut + "';";
            n = st.executeUpdate(SQL);
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProcesaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (n == 0) {
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ProcesaInsertar</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("NO se actualizo el registro");
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
                out.println("SI se actualizo el registro");
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
