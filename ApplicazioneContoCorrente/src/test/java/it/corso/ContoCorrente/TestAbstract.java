package it.corso.ContoCorrente;

import it.corso.ContoCorrente.dao.IDao;
import it.corso.ContoCorrente.exception.DaoException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public abstract class TestAbstract<T,K>{
    private static final String URL="URL";
    private static final String USERNAME="USERNAME";
    private static final String PASSWORD="PASSWORD";

    private static String urlDB;
    private static String username;
    private static String password;

    private static void setting() throws DaoException{
        Properties p=new Properties();
        try(InputStream input=ClassLoader.getSystemResourceAsStream("./config.properties")){
            p.load(input);
            urlDB=p.getProperty(URL);
            username=p.getProperty(USERNAME);
            password=p.getProperty(PASSWORD);
        }catch(IOException e){
            throw new DaoException("Errore accesso al file config");
        }
    }

    protected static Connection connect() throws DaoException{
        Connection c;
        try{
            if(urlDB==null){
                setting();
            }
            c= DriverManager.getConnection(urlDB,username,password);
        }catch(SQLException e){
            throw new DaoException(e.getMessage());
        }
        return c;
    }

    @BeforeAll
    public static void initDB() throws DaoException{
        try(Connection c=connect(); BufferedReader br=new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("ContoCorrente.sql")))){
            String query="", line="";
            while(line!=null){
                query+=line;
                line=br.readLine();
            }
            String[] queries=query.split(";");
            for(String s: queries){
                c.createStatement().executeUpdate(s);
            }
        }catch(SQLException|DaoException|IOException e){
            throw new DaoException(e.getMessage());
        }
    }

    @Test
    public void testCreate() throws DaoException{
        IDao<T,K> dao=getDao();
        T element=getElementCreate();
        Assertions.assertEquals(1,dao.create(element),"Errore nel metodo create. Generata Exception");
        Assertions.assertTrue(dao.read().contains(element),"Errore nel metodo create. Dati non corretti");
    }

    @Test
    public void testFindByCodiceFiscale() throws DaoException{
        IDao<T,K> dao=getDao();
        T expected= getElementFindByCodiceFiscale();
        String codiceFiscaleTitolare= getCodiceFiscaleFind();
        Assertions.assertDoesNotThrow(()->dao.findByCodiceFiscale(codiceFiscaleTitolare),"Errore nel metodo findByCodiceFiscale. Generata Exception");
        Assertions.assertEquals(dao.findByCodiceFiscale(codiceFiscaleTitolare),expected,"Errore nel metodo findByCodiceFiscale. Dati non corretti");
    }

    @Test
    public void testFindByCognome() throws DaoException{
        IDao<T,K> dao=getDao();
        List<T> expected= getListFindByCognome();
        String text= getCognome();
        List<T> actual=dao.findByCognome(text);
        Assertions.assertDoesNotThrow(()->dao.findByCognome(text),"Errore nel metodo findByCognome. Generata Exception");
        for(T t: expected){
            Assertions.assertTrue(actual.contains(t),"Errore nel metodo findByCognome. Dati non corretti");
        }
        for(T t: actual){
            Assertions.assertTrue(expected.contains(t),"Errore nel metodo findByCognome. Dati non corretti");
        }
    }

    protected abstract IDao<T,K> getDao();
    protected abstract T getElementCreate();
    protected abstract T getElementFindByCodiceFiscale();
    protected abstract String getCodiceFiscaleFind();
    protected abstract String getCognome();
    protected abstract List<T> getListFindByCognome();




}
