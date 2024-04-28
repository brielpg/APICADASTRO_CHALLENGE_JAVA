package fiap.tds.repositories;

import fiap.tds.models.Usuario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class UsuarioRepository {
    public static final Logger LOGGER = LogManager.getLogger(UsuarioRepository.class);
    public static String URL_ORACLE;
    public static String USER;
    public static String PASSWORD;

    public static final String TABLE_NAME = "USUARIOS";

    public static final Map<String, String> TABLE_COLUMNS = Map.of(
        "ID", "ID",
        "NOME", "NOME",
        "SOBRENOME", "SOBRENOME",
        "CARGO", "CARGO",
        "EMAIL", "EMAIL",
        "TELEFONE", "TELEFONE",
        "EMPRESA", "EMPRESA",
        "NUMFUNCIONARIOS", "NUMFUNCIONARIOS",
        "PAIS", "PAIS",
        "IDIOMA", "IDIOMA"

    );

    public UsuarioRepository(){
        try(var inputStream = getClass().getClassLoader()
                .getResourceAsStream("database.properties"))
        {
            var properties = new Properties();
            properties.load(inputStream);
            URL_ORACLE = properties.getProperty("jdbc.url");
            USER = properties.getProperty("jdbc.username");
            PASSWORD = properties.getProperty("jdbc.password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // RETORNAR TODOS OS USUARIOS
    public List<Usuario> getAll(){
        var usuarios = new ArrayList<Usuario>();
        try(var connection = DriverManager.getConnection(URL_ORACLE, USER, PASSWORD);
            var preparedStatement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            var resultSet = preparedStatement.executeQuery()
        ){
          while (resultSet.next()){
              usuarios.add(new Usuario(
                      resultSet.getInt(TABLE_COLUMNS.get("ID")),
                      resultSet.getString(TABLE_COLUMNS.get("NOME")),
                      resultSet.getString(TABLE_COLUMNS.get("SOBRENOME")),
                      resultSet.getString(TABLE_COLUMNS.get("CARGO")),
                      resultSet.getString(TABLE_COLUMNS.get("EMAIL")),
                      resultSet.getString(TABLE_COLUMNS.get("TELEFONE")),
                      resultSet.getString(TABLE_COLUMNS.get("EMPRESA")),
                      resultSet.getInt(TABLE_COLUMNS.get("NUMFUNCIONARIOS")),
                      resultSet.getString(TABLE_COLUMNS.get("PAIS")),
                      resultSet.getString(TABLE_COLUMNS.get("IDIOMA"))
              ));
          }
        }
        catch (SQLException e) {
            LOGGER.error("Erro ao tentar pegar todos os usuarios {}",
                    e.getMessage());
        }
        LOGGER.info("Usuarios retornados com sucesso");
        return usuarios;
    }

    // RETORNAR USUARIO POR ID
    public Optional<Usuario> getById(int id){
        try(var connection = DriverManager.getConnection(URL_ORACLE, USER, PASSWORD);
            var preparedStatement = connection.prepareStatement("SELECT * FROM "+TABLE_NAME+" WHERE ID = ?");
        ){
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
           if (resultSet.next()){
               return Optional.of(new Usuario(
                       resultSet.getInt(TABLE_COLUMNS.get("ID")),
                       resultSet.getString(TABLE_COLUMNS.get("NOME")),
                       resultSet.getString(TABLE_COLUMNS.get("SOBRENOME")),
                       resultSet.getString(TABLE_COLUMNS.get("CARGO")),
                       resultSet.getString(TABLE_COLUMNS.get("EMAIL")),
                       resultSet.getString(TABLE_COLUMNS.get("TELEFONE")),
                       resultSet.getString(TABLE_COLUMNS.get("EMPRESA")),
                       resultSet.getInt(TABLE_COLUMNS.get("NUMFUNCIONARIOS")),
                       resultSet.getString(TABLE_COLUMNS.get("PAIS")),
                       resultSet.getString(TABLE_COLUMNS.get("IDIOMA"))
               ));
           }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // CADASTRAR USUARIO
    public void cadastrar(Usuario usuario){
        try(var connection = DriverManager.getConnection(URL_ORACLE, USER, PASSWORD);
            var preparedStatement = connection.prepareStatement(
                    "INSERT INTO %s(%s,%s,%s,%s,%s,%s,%s,%s,%s) VALUES(?,?,?,?,?,?,?,?,?)"
                            .formatted(TABLE_NAME,
                                    TABLE_COLUMNS.get("NOME"),
                                    TABLE_COLUMNS.get("SOBRENOME"),
                                    TABLE_COLUMNS.get("CARGO"),
                                    TABLE_COLUMNS.get("EMAIL"),
                                    TABLE_COLUMNS.get("TELEFONE"),
                                    TABLE_COLUMNS.get("EMPRESA"),
                                    TABLE_COLUMNS.get("NUMFUNCIONARIOS"),
                                    TABLE_COLUMNS.get("PAIS"),
                                    TABLE_COLUMNS.get("IDIOMA")))
        ) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getSobrenome());
            preparedStatement.setString(3, usuario.getCargo());
            preparedStatement.setString(4, usuario.getEmailCorporativo());
            preparedStatement.setString(5, usuario.getTelefoneCorporativo());
            preparedStatement.setString(6, usuario.getEmpresa());
            preparedStatement.setInt(7, usuario.getNumDeFuncionarios());
            preparedStatement.setString(8, usuario.getPais());
            preparedStatement.setString(9, usuario.getIdioma());
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    // ATUALIZAR CADASTRO DO USUARIO
    public void atualizar(int id, Usuario usuario){
        try(var connection = DriverManager.getConnection(URL_ORACLE, USER, PASSWORD);
        var preparedStatement = connection.prepareStatement(
                "UPDATE PRODUTO SET NOME = ?, SOBRENOME = ?, CARGO = ?, EMAIL = ?, TELEFONE = ?, EMPRESA = ?," +
                        "NUMFUNCIONARIOS = ?, PAIS = ?, IDIOMA = ? WHERE ID = ?"))
        {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getSobrenome());
            preparedStatement.setString(3, usuario.getCargo());
            preparedStatement.setString(4, usuario.getEmailCorporativo());
            preparedStatement.setString(5, usuario.getTelefoneCorporativo());
            preparedStatement.setString(6, usuario.getEmpresa());
            preparedStatement.setInt(7, usuario.getNumDeFuncionarios());
            preparedStatement.setString(8, usuario.getPais());
            preparedStatement.setString(9, usuario.getIdioma());
            preparedStatement.setInt(10, id);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    // DELETAR CADASTRO DO USUARIO
    public void deletar(int id){
        try(var connection = DriverManager.getConnection(URL_ORACLE, USER, PASSWORD);
            var preparedStatement = connection.prepareStatement("DELETE FROM "+TABLE_NAME+" WHERE ID = ?");
        ){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
