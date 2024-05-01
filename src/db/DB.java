package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null; // Inicializa a conexão com o banco de dados como nula

    // Conectar com o banco de dados
    // No JDBC é instanciar um objeto do tipo Connection
    public static Connection getConnection () {
        if (conn == null) {
            try {
                Properties props = loadProperties(); // Criar um obejto properties já com os dados de conexão
                String url = props.getProperty("dburl"); // Guardar a url do banco de dados
                conn = DriverManager.getConnection(url, props); // Conecta com o banco de dados
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage()); // Lança uma excessão personalizada
            }
        }
        return conn;
    }

    //Fechar a conexão com o banco de dados
    public static void closeConnection() {
        // Testa se a conexão com o banco de dados está ativada
        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage()); // Lança uma excessão personalizada
            }
        }
    }

    //Ler o arquivo que tem a conexão com o banco de dados e guardar dentro de um objeto properties
    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties(); // Instanciar um objeto properties
            props.load(fs); // Faz a leitura do arquivo db.properties, guardando os dados dentro de um obejeto props
            return props;
        }
        catch (IOException e ) {
            throw new DbException(e.getMessage());
        }
    }

    // Fechar um Statement
    public static void  closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
    // Fechar um ResultSet
    public static void  closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}

/*
Arquivo db.properties:

user=root
password=12345678
dburl=jdbc:mysql://localhost:3306/coursejdbc
useSSL=false

 */
