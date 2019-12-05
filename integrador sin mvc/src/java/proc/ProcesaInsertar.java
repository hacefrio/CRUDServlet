package proc;

import conec.ConexionBD;
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

@WebServlet(name = "ProcesaInsertar", urlPatterns = {"/ProcesaInsertar"})
public class ProcesaInsertar extends HttpServlet {
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int n = 0;
        String rut = request.getParameter("txtRut"); //Recupera rut
        int edad = Integer.parseInt(request.getParameter("txtEdad")); //Recupera edad
        int sexo = Integer.parseInt(request.getParameter("txtSexo")); //Recupera sexo
        PrintWriter out = response.getWriter();
        String error="";
        try {
            Class.forName(ConexionBD.getDriver()); //Declara el driver a usar
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcesaInsertar.class.getName()).log(Level.SEVERE, null, ex);
            error=error+ex.getMessage();
        }
        try {
            Connection con = DriverManager.getConnection(ConexionBD.getUrl(),
                    ConexionBD.getUser(),
                    ConexionBD.getPass()); //Crea el objeto de conexión
            Statement st = con.createStatement(); //Crear el objeto para proceso
            String SQL = " insert into cliente (rut,edad,sexo) values ('"
                    + rut + "',"
                    + edad + ","
                    + sexo + ");"; //Crear la SQL para insertar el registro
            n = st.executeUpdate(SQL); //Ejecuta el proceso de insertar el registro
            st.close(); //cierra el objeto
            con.close(); //cierra el objeto
        } catch (SQLException ex) {
            Logger.getLogger(ProcesaInsertar.class.getName()).log(Level.SEVERE, null, ex);
            error=error+ex.getMessage();
        }
        if (n == 0) {
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ProcesaInsertar</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("NO se inserto el registro");
                out.println(error);
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
                out.println("SI se inserto el registro");
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
