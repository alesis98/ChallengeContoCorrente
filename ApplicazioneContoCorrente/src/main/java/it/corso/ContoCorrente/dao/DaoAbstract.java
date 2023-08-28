package it.corso.ContoCorrente.dao;

import it.corso.ContoCorrente.exception.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class DaoAbstract<T,K> implements IDao<T,K>{
    private static final String URL="URL";
    private static final String USERNAME="USERNAME";
    private static final String PASSWORD="PASSWORD";

    private String urlDB;
    private String username;
    private String password;

    private void setting() throws DaoException{
        Properties p=new Properties();
        try(InputStream input=getClass().getClassLoader().getResourceAsStream("./config.properties")){
            p.load(input);
            this.urlDB=p.getProperty(URL);
            this.username=p.getProperty(USERNAME);
            this.password=p.getProperty(PASSWORD);
        }catch(IOException e){
            throw new DaoException("Errore accesso al file config");
        }
    }

    protected Connection connect() throws DaoException{
        Connection c;
        try{
            if(this.urlDB==null){
                setting();
            }
            c= DriverManager.getConnection(urlDB,username,password);
        }catch(SQLException e){
            throw new DaoException(e.getMessage());
        }
        return c;
    }

    @Override
    public List<T> findByCognome(String text) throws DaoException{
        return executeQuery(getFindByCognome(text));
    }
    @Override
    public List<T> read() throws DaoException{
        return executeQuery(getSelect());
    }

    @Override
    public T findByCodiceFiscale(String codiceFiscaleTitolare) throws DaoException{
        return executeQueryById(getFindByCodiceFiscale(codiceFiscaleTitolare));
    }

    private List<T> executeQuery(String query) throws DaoException{
        try(Connection conn=this.connect()){
            ResultSet rs=conn.createStatement().executeQuery(query);
            return transformData(rs);
        }catch(SQLException sql){
            sql.printStackTrace();
            throw new DaoException("errore in fase di recupero delle informazioni dalla tabella. executeQuery");
        }
    }

    private T executeQueryById(String query) throws DaoException{
        try(Connection conn=this.connect()){
            ResultSet rs=conn.createStatement().executeQuery(query);
            return transformData(rs).get(0);
        }catch(SQLException sql){
            sql.printStackTrace();
            throw new DaoException("errore in fase di recupero delle informazioni dalla tabella executeQueryById.");
        }
    }

    @Override
    public int create(T item) throws DaoException{
        try(Connection conn=this.connect()){
            Statement stmt=conn.createStatement();
            int result=stmt.executeUpdate(getInsert(item),Statement.RETURN_GENERATED_KEYS);
            return result;
        }catch(SQLException sql){
            sql.printStackTrace();
            throw new DaoException("errore in fase di inserimento nella tabella.");
        }
    }

    protected List<T> transformData(ResultSet rs) throws DaoException{
        List<T> result=new ArrayList<>();
        try{
            while(rs.next()){
                result.add(resultsetToEntity(rs));
            }
        }catch(SQLException sql){
            sql.printStackTrace();
            throw new DaoException("errore in fase di aggiornamento nella tabella.");
        }
        return result;
    }

    protected abstract T resultsetToEntity(ResultSet rs) throws DaoException;

    protected abstract String getInsert(T item);

    protected abstract String getFindByCodiceFiscale(String codiceFiscaleTitolare);

    protected abstract String getFindByCognome(String text);

    protected abstract String getSelect();
}

