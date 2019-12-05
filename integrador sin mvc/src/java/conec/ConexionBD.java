package conec;

public class ConexionBD {

    private static String url = "jdbc:mysql://localhost/dgac"; //Se conecta a la base de datos dgac
    private static String user = "root"; //El usuario es “root”
    private static String pass = ""; //La clave del usuario root es “root”

    public static String getDriver() {
        String driver = "com.mysql.jdbc.Driver"; //Declara el Driver a usar
        return driver;
    }

    public static String getUrl() { //Retorna la url con la ubicación de la base de datos
        return url;
    }

    public static String getUser() { //Retorna el usuario de conexión a la base de datos
        return user;
    }

    public static String getPass() { //Retorna el password del usuario de conexión a la base de datos
        return pass;
    }
}
