package it.corso.ContoCorrente.dao;

import it.corso.ContoCorrente.dto.ContoCorrente;
import it.corso.ContoCorrente.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoContoCorrente extends DaoAbstract <ContoCorrente, Long> {

    @Override
    protected ContoCorrente resultsetToEntity(ResultSet rs) throws DaoException {
        ContoCorrente contoCorrente = null;
        try {
            contoCorrente=new ContoCorrente(rs.getLong("ContiCorrenti.numeroConto"),rs.getString("ContiCorrenti.CIN"), rs.getInt("ContiCorrenti.ABI"), rs.getInt("ContiCorrenti.CAB"),rs.getString("ContiCorrenti.nomeTitolare"), rs.getString("ContiCorrenti.cognomeTitolare"), rs.getString("ContiCorrenti.codiceFiscaleTitolare")  );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contoCorrente;
    }

    @Override
    protected String getInsert(ContoCorrente item) {
        return "INSERT INTO Banca.ContiCorrenti (numeroConto, CIN, ABI, CAB, nomeTitolare, cognomeTitolare, codiceFiscaleTitolare) VALUES ("+ item.getNumeroConto()+",'"+item.getCIN()+"',"+item.getABI()+","+item.getCAB()+",'"+item.getNomeTitolare()+"','"+item.getCognomeTitolare()+"','"+item.getCodiceFiscaleTitolare()+"')";
    }

    @Override
    protected String getFindByCodiceFiscale(String codiceFiscaleTitolare) {
        return "SELECT * FROM Banca.ContiCorrenti WHERE codiceFiscaleTitolare LIKE '%" +codiceFiscaleTitolare+"%'";
    }

    @Override
    protected String getFindByCognome(String text) {
        return "SELECT * FROM Banca.ContiCorrenti WHERE cognomeTitolare LIKE '%"+ text + "%'";
    }

    @Override
    protected String getSelect() {
        return "select * from banca.contiCorrenti";
    }
}
