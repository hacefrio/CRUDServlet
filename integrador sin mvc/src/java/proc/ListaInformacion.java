package proc;

import bean.Cliente;
import conec.ConexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListaInformacion", urlPatterns = {"/ListaInformacion"})
public class ListaInformacion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Vector<Cliente> dat = new Vector<Cliente>();
        PrintWriter out = response.getWriter();
        try {
            Class.forName(ConexionBD.getDriver());
        } catch (ClassNotFoundException ex) {
        }
        try {
            Connection con = DriverManager.getConnection(ConexionBD.getUrl(),
                    ConexionBD.getUser(),
                    ConexionBD.getPass());
            Statement st = con.createStatement();
            String SQL = " select rut, edad, sexo from cliente order by 1;";
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                String rutRecupera = rs.getString(1);
                int edadRecupera = rs.getInt(2);
                int sexoRecupera = rs.getInt(3);
                dat.add(new Cliente(rutRecupera, edadRecupera, sexoRecupera));
            }
            st.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
        }
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
