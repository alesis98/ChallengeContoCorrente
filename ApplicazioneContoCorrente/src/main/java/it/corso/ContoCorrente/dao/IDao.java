package it.corso.ContoCorrente.dao;

import it.corso.ContoCorrente.exception.DaoException;

import java.util.List;

public interface IDao<T,INDICE>{
    List<T> read()throws DaoException;
    T findByCodiceFiscale(String codiceFiscaleTitolare) throws DaoException;
    int create(T element) throws DaoException;
    List<T> findByCognome(String text)throws DaoException;

}