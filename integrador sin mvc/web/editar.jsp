<html>
    <head>
        <%@page import="java.sql.*" %> 
        <%@page import="java.util.Vector" %>
        <%@page import="conec.*" %>
        <%@page import="bean.*" %>
        <%
            Vector<Cliente> dat = new Vector<Cliente>(); //Almacena los datos recuperados
            try {
                Class.forName(ConexionBD.getDriver());
            } catch (ClassNotFoundException ex) {

            }
            try {
                Connection con = DriverManager.getConnection(ConexionBD.getUrl(),
                        ConexionBD.getUser(),
                        ConexionBD.getPass());
                Statement st = con.createStatement();
                String SQL = " select rut, edad, sexo from cliente order by 1;"; //Consulta
                ResultSet rs = st.executeQuery(SQL); //Manda a ejecución la consulta
                while (rs.next()) { //Recupera los registros
                    String rutRecupera = rs.getString(1);
                    int edadRecupera = rs.getInt(2);
                    int sexoRecupera = rs.getInt(3);
                    //Almacena la información en el Vector
                    dat.add(new Cliente(rutRecupera, edadRecupera, sexoRecupera));
                }
                st.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {

            }
            String[] rut = new String[dat.size()]; //Almacena los rut recuperados
            int[] edad = new int[dat.size()]; //Almacena las edades recuperados
            int[] sexo = new int[dat.size()]; //Almacena el sexo recuperado
            //Almacena la información en los arreglos anteriores
            int i = 0;
            for (Cliente dato : dat) {
                rut[i] = dato.getRut();
                edad[i] = dato.getEdad();
                sexo[i] = dato.getSexo();
                i++;
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar</title>
        <script language="javascript" type="text/javascript">
            //Array para almacenar los datos desde java a javascript
            var rutJS = new Array();
            var edadJS = new Array();
            var sexoJS = new Array();
            var rangoEdad = new Array(20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
            //Función que se gatilla en la partida del formulario llamado desde el body del html
            function partida() {
                //Transfiere los datos desde java a Javascript
            <% for (i = 0; i < rut.length; i++) {%>
                rutJS[<%= i%>] = "<%= rut[i]%>";
                edadJS[<%= i%>] = "<%= edad[i]%>";
                sexoJS[<%= i%>] = "<%= sexo[i]%>";
            <% }%>
                //Asigna el largo de la lista desplegable de los rut
                document.getElementById("Select1").length = rutJS.length;
                for (i = 0; i < rutJS.length; i++) {
                    //Valor que se devuelve desde el select
                    document.getElementById("Select1").options[i].value = rutJS[i];
                    //Valor que se muestra en el select
                    document.getElementById("Select1").options[i].text = rutJS[i];
                }
                //Asigna el largo de la lista desplegable de los años
                document.getElementById("Select2").length = rangoEdad.length;
                for (i = 0; i < rangoEdad.length; i++) {
                    //Valor que se devuelve desde el select
                    document.getElementById("Select2").options[i].value = rangoEdad[i];
                    //Valor que se muestra en el select
                    document.getElementById("Select2").options[i].text = rangoEdad[i];
                }
                //Busca la edad del primer cliente y la muestra en la lista desplegable

                for (i = 0; i < rangoEdad.length; i++)
                    if ((i + 20) == edadJS[0])
                        document.getElementById("Select2").selectedIndex = i;
                //Busca el sexo del primer cliente y la muestra en los RadioButtom
                if (sexoJS[0] == 1)
                    document.getElementsByName("sexo").item(0).checked = true;
                if (sexoJS[0] == 0)
                    document.getElementsByName("sexo").item(1).checked = true;
            }
            //Función que se gatilla cada vez que se selecciona un nuevo rut llamado desde Select1
            function cambia() {
                //Recupera el índice desde el rut (0,1,2,...)
                var n = document.getElementById("Select1").selectedIndex;
                //Aquí se desarrollan dos procesos: 1: Busca el índice en el Array rangoEdad que tiene el
                //valor de edad buscado (edadJS[n]). 2: Ese índice es transferido a la lista desplegable
                //del la edad y así se muestra el valor asociado
                for (i = 0; i < rangoEdad.length; i++)
                    if ((i + 20) == edadJS[n])
                        document.getElementById("Select2").selectedIndex = i;
                //Recupera el valor del sexo buscado (sexoJS[n]) y dependiendo del valor lo muestra
                if (sexoJS[n] == 1)
                    document.getElementsByName("sexo").item(0).checked = true;
                if (sexoJS[n] == 0)
                    document.getElementsByName("sexo").item(1).checked = true;
            }
        </script>
        <meta charset="UTF-8">
        <title>Insertar</title>
        <meta name="viewport" content="width=device-width, initial-scale=1"><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
        <link rel="stylesheet" href="./style.css">

    </head>
    <body onload="partida();">
        <!-- partial:index.partial.html -->
        <div class="plus-btn-pos">
            <div class="plus-btn">
                <div class="r1"></div>
                <div class="r2"></div>
            </div>
        </div>
        <div class="content">
            <div>
                <h1>Complete los siguientes campos</h1><br><br><br>
                <form  name="frmActualizar" action="ProcesaActualizar" method="POST" >
                    <center>
                            <table style="width: 300px;">
                                <tr>
                                    <td align="left"><font size ="10" > Rut &nbsp: &nbsp</font> </td>
                                    <td >  
                                        <select style="font-size:30px;  " id="Select1" name="rut" onchange="cambia();">
                                            <option  style="font-size:30px; width:250px"></option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left"><font size ="10" >Edad: </font></td>
                                    <td>
                                        <select id="Select2" name="edad" style="font-size:30px;">
                                            <option></option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left"><font size ="10" >Sexo:</font> </td>
                                    <td>
                                        <input type="radio" name="sexo" value="1" />Mujer
                                        <input type="radio" name="sexo" value="0" />Hombre
                                    </td>
                                </tr>
                            </table>
                            <input type="submit" value="Editar" />
                        </form>
                    </center>
            </div>
        </div>
        <div class="menu-container">
            <div class="menu-sliders"></div>
            <div class="menu-sliders"></div>
            <div class="menu-sliders"></div>
            <div class="menu">
                <ul>
                    <li>
                        <a href="/Integrador/insertar.html" >Insertar</a>
                    </li>
                    <li>
                        <a href="/Integrador/eliminar.html" >Eliminar</a>
                    </li>
                    <li>
                        <a href="/Integrador/editar.jsp" >Editar</a>
                    </li>
                    <li>
                        <a href="/Integrador/listar.html" >Listar</a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- partial -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script  src="./script.js"></script>

    </body>
</html>