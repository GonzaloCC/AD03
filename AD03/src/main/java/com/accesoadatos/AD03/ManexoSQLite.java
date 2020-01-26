package com.accesoadatos.AD03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



public class ManexoSQLite {
	
	static Provincias provincias=new Provincias();
	 /*
    Este método crea unha nova base de datos en SQLLite co nome que se lle pasa como argunmento
    */
    public static void createDatabase(String filename){
    	File f = new File(filename);
        String databaseFile = "jdbc:sqlite:"+ filename;
        
        try{
            Connection connection = DriverManager.getConnection(databaseFile);
            if(connection != null){
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("A base de datos foi creada");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    /*
    Esta clase conéctase a base de datos SQLLite que se lle pasa o nome da base de datos
    */
    public static Connection connectDatabase(String filename){
        Connection connection = null;
        try
        {
            //Creamos a conexión a base de datos
            Properties propiedadesConex=new Properties();
            propiedadesConex.setProperty("foreign_keys", "true");
            connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
        
            System.out.println("Conexión realizada con éxito");
            return connection;
             
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    /*
    Este método desconectase dunha base de datos en SQLite a que se lle pasa a conexión
    */
    public static void desconnetDatabase(Connection connection){
        try{
            if(connection != null){
                connection.close();
                System.out.println("Desconexión realizada con éxito");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    /*
    Método que crea a tabla tenda nunha base de datos 
     */
    public static void createTableTenda(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS tenda (\n" +
                    "nome text PRIMARY KEY,\n"+
                    "cidade text NOT NULL,\n"+
                    "provincia text NOT NULL,\n"+
                    "FOREIGN KEY (provincia) \n" + 
                    "      REFERENCES provincia (id) \n" + 
                    "         ON DELETE CASCADE \n" + 
                    "         ON UPDATE NO ACTION\n" + 

                    ");";

            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa tendas creada con éxito");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    /*
    Método que crea a producto tenda nunha base de datos franquicia 
     */
    public static void createTableProducto(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS producto (\n" +
                    "nome text PRIMARY KEY,\n"+
                    "descripcion text NOT NULL,\n"+
                    "prezo real NOT NULL\n"+
                    ");";

            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa producto creada con éxito");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    /*
    Método que crea a producto tenda nunha base de datos franquicia 
     */
    public static void createTableProductoTenda(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS productotenda (\n" +
                    "nometenda text NOT NULL,\n"+
                    "nomeproducto text NOT NULL,\n"+
                    "stock integer NOT NULL,\n"+
                    "   PRIMARY KEY (nometenda, nomeproducto),\r\n" + 
                    "   FOREIGN KEY (nometenda) \r\n" + 
                    "      REFERENCES tenda (nome) \r\n" + 
                    "         ON DELETE CASCADE \r\n" + 
                    "         ON UPDATE NO ACTION,\r\n" + 
                    "   FOREIGN KEY (nomeproducto) \r\n" + 
                    "      REFERENCES producto (nome) \r\n" + 
                    "         ON DELETE CASCADE \r\n" + 
                    "         ON UPDATE NO ACTION"+
                    ");";

            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa productotenda creada con éxito");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    

    
    /*
    Método que crea a empregados tenda nunha base de datos franquicia  
     */
    public static void createTableEmpregados(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS empregado (\n" +
                    "nome text NOT NULL,\n"+
                    "apelido1 text NOT NULL,\n"+
                    "apelido2 text NOT NULL,\n"+
                    "PRIMARY KEY (nome, apelido1,apelido2)"+
                    ");";

            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa empregado creada con éxito");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    
    /*
    Método que crea a empregados tenda nunha base de datos franquicia  
     */
    public static void createTableEmpregadosTenda(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS empregadostenda (\n" +
                    "nome text NOT NULL,\n"+
                    "apelido1 text NOT NULL,\n"+
                    "apelido2 text NOT NULL,\n"+
                    "n_horas integer NOT NULL,\n"+
                    "tenda text NOT NULL,\n"+
                    "PRIMARY KEY (nome, apelido1,apelido2,tenda),"+
                    "FOREIGN KEY (tenda) \r\n" + 
                    "      REFERENCES tenda (nome) \r\n" + 
                    "         ON DELETE CASCADE \r\n" + 
                    "         ON UPDATE NO ACTION\r\n" +
                    ");";

            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa empregadotenda creada con éxito");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
        
    
    /*
    Método que crea a provincias tenda nunha base de datos franquicia  
     */
    public static void createTableProvincias(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS provincia (\n" +
                    "id text PRIMARY KEY,\n"+
                    "cidade text NOT NULL\n"+
                    ");";

            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa provincias creada con éxito");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
   
    
    /*
    Método que crea a tabla clientes nunha base de datos persoa  
     */
    public static void createTableClientes(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS clientes (\n" +
                    "email text PRIMARY KEY,\n"+
                    "nome text NOT NULL,\n"+
                    "apelido1 text NOT NULL,\n"+
                    "apelido2 text NOT NULL\n"+
                    ");";

            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa clientes creada con éxito");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    
 public static void provinciasJson() {
    	
    	File arquivo = new File("provincias.json");

        try {

            //Creamos un fluxo de entrada para o arquivo
            FileReader fluxoDatos;
            fluxoDatos = new FileReader(arquivo);

            //Creamos o bufer de entrada
            BufferedReader buferEntrada = new BufferedReader(fluxoDatos);

            //Imos lendo linea a linea
            StringBuilder jsonBuilder = new StringBuilder();
            String linea;
            while ((linea=buferEntrada.readLine()) != null) {
                jsonBuilder.append(linea).append("\n");
            }

            //Temos que cerrar sempre o ficheiro
            buferEntrada.close();

            //Construimos o String con todalas lineas lidas
            String json = jsonBuilder.toString();

            //Pasamos o json a clase ca cal se corresponde
            Gson gson = new Gson();
            //Type listType = new TypeToken<ArrayList<Provincia>>(){}.getType();
             provincias = gson.fromJson(json,Provincias.class);
           // Provincias provincias = gson.fromJson(json, Provincias.class);
            
             /*
            //Comprobamos que se leron ben os datos
            System.out.println("Provincias:");
            for(int i=0;i<provincias.getArrayProvincias().size();i++){
                Provincia provinciaAux = provincias.getArrayProvincias().get(i);
                System.out.println(i+"-"+provinciaAux.getNome() + " " + provinciaAux.getId());
            }
			*/

        } catch (FileNotFoundException e) {
            System.out.println("Non se encontra o arquivo");
        } catch (IOException e) {
            System.out.println("Erro de entrada saída");
        }
    }
 
 /*
 Este método inserta unha nova persoa na tboa Persoa
 */
 public static void insertProvincias(Connection con){
     try{
    	 provinciasJson();
         //Fixate que no código SQL o valor do nome e "?". Este valor engadiremolo despois
         String sql = "INSERT INTO provincia(id,cidade) VALUES(?,?)";
         PreparedStatement pstmt = con.prepareStatement(sql);

         //Aquí e cando engadimos o valor do nome
         for(Provincia p:provincias.getArrayProvincias()) {
         pstmt.setString(1, p.getId());
         pstmt.setString(2, p.getNome());
         pstmt.executeUpdate();
         System.out.println("Provincia engadida con éxito");
         }
     }
     catch(SQLException e){
         System.out.println(e.getMessage());
     }
 }
 
 /*
 Este método inserta unha nova tenda na taboa Tenda
 */
 public static void anadirTenda(Connection con){
     try{
    	 Scanner entrada= new Scanner(System.in);
     	System.out.println("Inserte  nome de tenda");
     	String nome=entrada.nextLine();
     	System.out.println("Inserte cidade de tenda");
     	String cidade=entrada.nextLine();
     	System.out.println("elixa o id dunha provincia");
     	printProvincias(con);
     	String id=entrada.nextLine();
     	int nProvincias=numeroProvincias(con);
     	if(Integer.parseInt(id)>0 && Integer.parseInt(id)<=nProvincias) {
     	String idP=selectProvincia(con,id);
     	
     	
         //Fixate que no código SQL o valor do nome e "?". Este valor engadiremolo despois
         String sql = "INSERT INTO tenda(nome,cidade,provincia) VALUES(?,?,?)";
         PreparedStatement pstmt = con.prepareStatement(sql);

         //Aquí e cando engadimos o valor do nome
         pstmt.setString(1, nome);
         pstmt.setString(2, cidade);
         pstmt.setString(3, idP);
         pstmt.executeUpdate();
         System.out.println("Tenda engadida con éxito");
     	 }else {
      		System.out.println("Numero incorrecto");
      	}
     }
     catch(SQLException e){
         System.out.println(e.getMessage());
     }catch(NumberFormatException e){
         System.out.println("Debe introducir un numero");
     }
 }
 
 /*
 Este método inserta un novo producto na taboa Producto
 */
 public static void anadirProducto(Connection con){
     try{
    	 Scanner entrada= new Scanner(System.in);
     	System.out.println("Inserte  nome do producto");
     	String nome=entrada.nextLine();
     	System.out.println("Inserte descripcion do producto");
     	String descripcion=entrada.nextLine();
     	Double prezo=0.0;
     	while(prezo<=0) {
         	System.out.println("Inserte prezo do producto");
         	prezo=entrada.nextDouble();
     	}
     	
         //Fixate que no código SQL o valor do nome e "?". Este valor engadiremolo despois
         String sql = "INSERT INTO producto(nome,descripcion,prezo) VALUES(?,?,?)";
         PreparedStatement pstmt = con.prepareStatement(sql);

         //Aquí e cando engadimos o valor do nome
         pstmt.setString(1, nome);
         pstmt.setString(2, descripcion);
         pstmt.setDouble(3,prezo);
         pstmt.executeUpdate();
         System.out.println("Producto engadido con éxito");
     }
     catch(SQLException e){
         System.out.println(e.getMessage());
     }catch(InputMismatchException e){
         System.out.println("Debe introducir un numero");
     }
 }
 
 public static void anadirProductoTenda(Connection con){
     try{
    	Scanner entrada= new Scanner(System.in);
    	System.out.println("Debe elixir tenda");
    	printTendas(con);
     	System.out.println("Inserte  numero da tenda");
     	int nTendas=numeroTendas(con);
     	String numerotenda=entrada.nextLine();
     	String nTen=selectTenda(con,numerotenda);
    	System.out.println("Debe elixir un producto");
    	printProductos(con);
    	int nProductos=numeroProductos(con);
     	System.out.println("Inserte  numero do producto");
     	String numeroproducto=entrada.nextLine();
     	String nPro=selectProducto(con,numeroproducto);
     	System.out.println("Inserte stock do producto");
     	int stock=entrada.nextInt();
         //Fixate que no código SQL o valor do nome e "?". Este valor engadiremolo despois
         String sql = "INSERT INTO productotenda(nometenda,nomeproducto,stock) VALUES(?,?,?)";
         PreparedStatement pstmt = con.prepareStatement(sql);
         //Aquí e cando engadimos o valor do nome
         pstmt.setString(1,nTen);
         pstmt.setString(2,nPro);
         pstmt.setInt(3,stock);
         pstmt.executeUpdate();

         System.out.println("Producto engadido con éxito");

     }
     catch(SQLException e){
         System.out.println(e.getMessage());
     }catch(InputMismatchException e){
         System.out.println("Debe introducir un numero");
     }catch(NumberFormatException e){
         System.out.println("Debe introducir un numero");
     }
 }
 
 
 public static void eliminarProductoTenda(Connection con){
     try{
    	 Scanner entrada= new Scanner(System.in);
    	System.out.println("Debe elixir tenda");
    	printTendas(con);
     	System.out.println("Inserte  nome da tenda");
     	String nometenda=entrada.nextLine();
    	System.out.println("Debe elixir un producto");
    	printProductosTenda(con,nometenda);
     	System.out.println("Inserte  nome do producto");
     	String nomeproducto=entrada.nextLine();
         //Fixate que no código SQL o valor do nome e "?". Este valor engadiremolo despois
     	String sql = "DELETE  FROM Productotenda WHERE nometenda = ? and nomeproducto=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, nometenda);
        pstmt.setString(2, nomeproducto);
        pstmt.executeUpdate();
        System.out.println("Producto da tenda borrado con éxito");
     }
     catch(SQLException e){
         System.out.println(e.getMessage());
     }
 }
 
 
 /*
 Este método inserta unha nova persoa na tboa Cliente
 */
 public static void anadirCliente(Connection con){
     try{
    	 Scanner entrada= new Scanner(System.in);
     	System.out.println("Inserte  nome do cliente");
     	String nome=entrada.nextLine();
     	System.out.println("Inserte apelido 1 do cliente");
     	String apelido1=entrada.nextLine();
     	System.out.println("Inserte apelido 2 do cliente");
     	String apelido2=entrada.nextLine();
     	System.out.println("Inserte email do cliente");
     	String email=entrada.nextLine();
         //Fixate que no código SQL o valor do nome e "?". Este valor engadiremolo despois
         String sql = "INSERT INTO clientes(email,nome,apelido1,apelido2) VALUES(?,?,?,?)";
         PreparedStatement pstmt = con.prepareStatement(sql);

         //Aquí e cando engadimos o valor do nome
         pstmt.setString(1, email);
         pstmt.setString(2, nome);
         pstmt.setString(3, apelido1);
         pstmt.setString(4, apelido2);
         pstmt.executeUpdate();
         System.out.println("Cliente engadido con éxito");
     }
     catch(SQLException e){
         System.out.println(e.getMessage());
     }
 }
 
 public static void anadirEmpregado(Connection con){
     try{
    	 Scanner entrada= new Scanner(System.in);
     	System.out.println("Inserte  nome do empleado");
     	String nome=entrada.nextLine();
     	System.out.println("Inserte apelido 1 do empleado");
     	String apelido1=entrada.nextLine();
     	System.out.println("Inserte apelido 2 do empleado");
     	String apelido2=entrada.nextLine();
         //Fixate que no código SQL o valor do nome e "?". Este valor engadiremolo despois
         String sql = "INSERT INTO empregado(nome,apelido1,apelido2) VALUES(?,?,?)";
         PreparedStatement pstmt = con.prepareStatement(sql);

         //Aquí e cando engadimos o valor do nome
         pstmt.setString(1, nome);
         pstmt.setString(2, apelido1);
         pstmt.setString(3, apelido2);
         pstmt.executeUpdate();
         System.out.println("Empregado engadido con éxito");
     }
     catch(SQLException e){
         System.out.println(e.getMessage());
     }
 }
 /*
 Este método borra unha tenda da base de datos
 */
 public static void deleteTenda(Connection con){
     try{ 
    	 Scanner entrada= new Scanner(System.in);
     	System.out.println("Debe elixir tenda");
     	printTendas(con);
      	System.out.println("Inserte  numero da tenda");
      	int nTendas=numeroTendas(con);
      	String numerotenda=entrada.nextLine();
      	String nTen=selectTenda(con,numerotenda);
         String sql = "DELETE FROM tenda WHERE nome = ?";
         PreparedStatement pstmt = con.prepareStatement(sql);
         pstmt.setString(1, nTen);
         pstmt.executeUpdate();
         System.out.println("Tenda borrada con éxito");
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
     }
 }
 
 /*
 Este método borra unha tenda da base de datos
 */
 public static void deleteProducto(Connection con){
     try{ 
    	 Scanner entrada= new Scanner(System.in);

     	System.out.println("Debe elixir un producto");
     	printProductos(con);
     	int nProductos=numeroProductos(con);
      	System.out.println("Inserte  numero do producto");
      	String numeroproducto=entrada.nextLine();
      	String nPro=selectProducto(con,numeroproducto);
         String sql = "DELETE FROM producto WHERE nome = ?";
         PreparedStatement pstmt = con.prepareStatement(sql);
         pstmt.setString(1, nPro);
         pstmt.executeUpdate();
         System.out.println("Producto borrado con éxito");
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
     }
 }
 
 public static void deleteEmpregado(Connection con){
     try{ 
    	 Scanner entrada= new Scanner(System.in);

     	System.out.println("Debe elixir un empregado");
     	printEmpregados(con);
      	System.out.println("Inserte  numero doempregado");
      	String numeroempregado=entrada.nextLine();
      	String nPro=selectEmpregado(con,numeroempregado);
         String sql = "DELETE FROM empregado WHERE nome = ?";
         PreparedStatement pstmt = con.prepareStatement(sql);
         pstmt.setString(1, nPro);
         pstmt.executeUpdate();
         System.out.println("Empregado borrado con éxito");
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
     }
 }
 
 /*
 Este método borra un cliente da base de datos
 */
 public static void deleteCliente(Connection con){
     try{ 
    	 Scanner entrada= new Scanner(System.in);
    	 System.out.println("Que cliente desexa eliminar?");
    	 printClientes(con);
       	System.out.println("Inserte  numero do cliente");
    	 String numerocliente=entrada.nextLine();
    	 String nCli=selectCliente(con,numerocliente);
         String sql = "DELETE FROM clientes WHERE email = ?";
         PreparedStatement pstmt = con.prepareStatement(sql);
         pstmt.setString(1, nCli);
         pstmt.executeUpdate();
         System.out.println("Cliente borrado con éxito");
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
     }
 }
 
 
 /*
 Método que imprime tódalas tendas
 */
 public static void printTendas(Connection con){
     try
     {
         Statement statement = con.createStatement();

         //Probamos a realizar unha consulta
         ResultSet rs = statement.executeQuery("select rowid,* from tenda");
         
         while(rs.next()){
             //imprimimos o nome de todolas persoas
             System.out.println(rs.getInt("rowid")+"-  Nome = " + rs.getString("nome")+" Cidade = " + rs.getString("cidade")+" Provincia = " + rs.getString("provincia"));
         }
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
     }
 }
 
// Método que imprime tódalas tendas
 
 public static void printProvincias(Connection con){
     try
     {

         Statement statement = con.createStatement();

         //Probamos a realizar unha consulta
         ResultSet rs = statement.executeQuery("select rowid,* from provincia");
         
         while(rs.next()){
             //imprimimos o nome de todolas provincias
             System.out.println(rs.getInt("rowid")+"- ID= " + rs.getString("id")+"  Cidade: "+rs.getString("cidade"));
         }
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
     }
 }
 
 
 public static int numeroProvincias(Connection con){
     try
     {

         Statement statement = con.createStatement();

         ResultSet rs = statement.executeQuery("select count(*) from provincia");
         return rs.getInt(1);
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
         return 0;
     }
 }
 
 public static String selectProvincia(Connection con,String id){
     try
     {
         String sql = "select id from provincia where rowid=?";
         PreparedStatement pstmt = con.prepareStatement(sql);
         

         //Probamos a realizar unha consulta
         //Aquí e cando engadimos o valor do nome
         pstmt.setString(1, id);
         ResultSet rs  = pstmt.executeQuery();
         return rs.getString("id");

     }
     catch(SQLException e){
         System.err.println(e.getMessage());
         return null;
     }
 }
 
 /*
 Método que imprime tódalas productos da franquicia
 */
 public static void printProductos(Connection con){
     try
     {

         Statement statement = con.createStatement();

         //Probamos a realizar unha consulta
         ResultSet rs = statement.executeQuery("select rowid,* from producto");
         
         while(rs.next()){
             //imprimimos o nome de todolas persoas
             System.out.println(rs.getInt("rowid")+"- Nome = " + rs.getString("nome"));
         }
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
     }
 }
 
 
 
 public static void printEmpregados(Connection con){
     try
     {

         Statement statement = con.createStatement();

         //Probamos a realizar unha consulta
         ResultSet rs = statement.executeQuery("select rowid,* from empregado");
         
         while(rs.next()){
             //imprimimos o nome de todolas persoas
             System.out.println(rs.getInt("rowid")+"- Nome = " + rs.getString("nome"));
         }
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
     }
 }
 
 public static int numeroProductos(Connection con){
     try
     {

         Statement statement = con.createStatement();

         //Probamos a realizar unha consulta
         ResultSet rs = statement.executeQuery("select count(*) from producto");
         return rs.getInt(1);
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
         return 0;
     }
 }
 
 public static String selectProducto(Connection con,String rowid){
     try
     {
         String sql = "select nome from producto where rowid=?";
         PreparedStatement pstmt = con.prepareStatement(sql);

         pstmt.setString(1, rowid);
         ResultSet rs  = pstmt.executeQuery();
         return rs.getString("nome");

     }
     catch(SQLException e){
         System.err.println(e.getMessage());
         return null;
     }
 }
 
 public static String selectEmpregado(Connection con,String rowid){
     try
     {
         String sql = "select nome from empregado where rowid=?";
         PreparedStatement pstmt = con.prepareStatement(sql);

         pstmt.setString(1, rowid);
         ResultSet rs  = pstmt.executeQuery();
         return rs.getString("nome");

     }
     catch(SQLException e){
         System.err.println(e.getMessage());
         return null;
     }
 }
 
 public static String selectProductoTenda(Connection con,String rowid){
     try
     {
         String sql = "select nomeproducto from productotenda where rowid=?";
         PreparedStatement pstmt = con.prepareStatement(sql);

         pstmt.setString(1, rowid);
         ResultSet rs  = pstmt.executeQuery();
         return rs.getString("nomeproducto");

     }
     catch(SQLException e){
         System.err.println(e.getMessage());
         return null;
     }
 }
 
 public static int numeroTendas(Connection con){
     try
     {

         Statement statement = con.createStatement();

         //Probamos a realizar unha consulta
         ResultSet rs = statement.executeQuery("select count(*) from tenda");
         return rs.getInt(1);
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
         return 0;
     }
 }
 
 public static String selectTenda(Connection con,String rowid){
     try
     {
         String sql = "select nome from tenda where rowid=?";
         PreparedStatement pstmt = con.prepareStatement(sql);

         pstmt.setString(1, rowid);
         ResultSet rs  = pstmt.executeQuery();
         return rs.getString("nome");
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
         return null;
     }
 }
 
 public static int numeroClientes(Connection con){
     try
     {

         Statement statement = con.createStatement();

         //Probamos a realizar unha consulta
         ResultSet rs = statement.executeQuery("select count(*) from clientes");
         return rs.getInt(1);
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
         return 0;
     }
 }
 
 public static String selectCliente(Connection con,String rowid){
     try
     {
         String sql = "select email from clientes where rowid=?";
         PreparedStatement pstmt = con.prepareStatement(sql);

         pstmt.setString(1, rowid);
         ResultSet rs  = pstmt.executeQuery();
         return rs.getString("email");
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
         return null;
     }
 }
 
 public static void mostrarStock(Connection con,String nometenda){
     try
     {	
    	 Scanner entrada=new Scanner(System.in);
    	 String sql=("select stock from productotenda where nometenda=? and nomeproducto=?");
    	 PreparedStatement pstmt  = con.prepareStatement(sql);
    	 System.out.println("Debe elixir un producto");
     	printProductosTenda(con,nometenda);
      	System.out.println("Inserte  numero do producto");
      	String numeroproducto=entrada.nextLine();
      	String nPro=selectProductoTenda(con,numeroproducto);
         // set the value
         pstmt.setString(1,nometenda);
         pstmt.setString(2,nPro);
         ResultSet rs  = pstmt.executeQuery();
         System.out.println("Tenda "+nometenda);
         while(rs.next()){          
             System.out.println(" Producto "+nPro+" stock= " + rs.getString("stock"));
         }

     }
     catch(SQLException e){
         System.err.println(e.getMessage());
     }
 }
 
 public static void actualizarStock(Connection con,String nometenda){
     try
     {	
    	 Scanner entrada=new Scanner(System.in);
    	 String sql=("UPDATE productotenda SET nometenda = ? ,nomeproducto = ?,stock=? WHERE nometenda =? and nomeproducto=?");
    	 PreparedStatement pstmt  = con.prepareStatement(sql);
    	 System.out.println("Debe elixir un producto");
     	printProductosTenda(con,nometenda);
      	System.out.println("Inserte  numero do producto");
      	String numeroproducto=entrada.nextLine();
      	String nPro=selectProductoTenda(con,numeroproducto);
    	 System.out.println("Introduce nievo Stock");
    	 int stock=entrada.nextInt();
         // set the value
         pstmt.setString(1,nometenda);
         pstmt.setString(2,nPro);
         pstmt.setInt(3, stock);
         pstmt.setString(4,nometenda);
         pstmt.setString(5,nPro);
         pstmt.executeUpdate();
         System.out.println("Stock do producto actualizado con éxito");


     }
     catch(SQLException e){
         System.err.println(e.getMessage());
     }
 }
 
 /*
 Método que imprime tódalas productos dunha determinada tenda
 */
 public static void printProductosTenda(Connection con,String nometenda){
     try
     {
    	 Scanner entrada=new Scanner(System.in);
    	 String sql=("select rowid,* from productotenda where nometenda=?");
    	 PreparedStatement pstmt  = con.prepareStatement(sql);

         // set the value
         pstmt.setString(1,nometenda);
         ResultSet rs  = pstmt.executeQuery();
         System.out.println(nometenda);
         while(rs.next()){          
             System.out.println(rs.getInt("rowid")+"- producto=" + rs.getString("nomeproducto"));
         }
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
     }
 
 }
 
 
 /*
 Método que imprime tódalos clientes
 */
 public static void printClientes(Connection con){
     try
     {

         Statement statement = con.createStatement();

         //Probamos a realizar unha consulta
         ResultSet rs = statement.executeQuery("select rowid,* from clientes");
         
         while(rs.next()){
             //imprimimos o nome de todolas persoas
             System.out.println(rs.getInt("rowid")+"- Nome= " + rs.getString("nome")+"- email= " + rs.getString("email"));
         }
     }
     catch(SQLException e){
         System.err.println(e.getMessage());
     }
 }
 
 public static void vacumm (Connection con) {
	 Statement stmt = null;
	 ResultSet rs = null;
	 try {
	     stmt = con.createStatement();
	     stmt.executeUpdate("VACUUM");
	     System.out.println("Vacumm realizado con exito");
	 } catch (SQLException e) {
		 System.err.println(e.getMessage());
	 }
 }
 
 
    

}
