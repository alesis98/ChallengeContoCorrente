package it.corso.ContoCorrente;

import it.corso.ContoCorrente.dao.DaoContoCorrente;
import it.corso.ContoCorrente.dao.IDao;
import it.corso.ContoCorrente.dto.ContoCorrente;

import java.util.ArrayList;
import java.util.List;

public class TestContoCorrente extends TestAbstract<ContoCorrente, Long> {

    @Override
    protected IDao<ContoCorrente, Long> getDao() {
        return new DaoContoCorrente();
    }

    @Override
    protected ContoCorrente getElementCreate() {
        return new ContoCorrente(123456789111L,"1",  45645, 98791, "Francesco", "Pacchero", "PCHGLU27E68L178E");
    }

    @Override
    protected ContoCorrente getElementFindByCodiceFiscale() {
        return new ContoCorrente(123456789101L,"1",  12345, 67891, "Giulio", "Foglietta", "FGLGLU27U38L478E");
    }

    @Override
    protected String getCodiceFiscaleFind() {return "FGLGLU27U38L478E";}

    @Override
    protected String getCognome() { return "Fumagalli";}

    @Override
    protected List<ContoCorrente> getListFindByCognome() {
        List<ContoCorrente> lista = new ArrayList<>();
        lista.add(new ContoCorrente(123456789102L,"2",  12346, 67892, "Pippo", "Fumagalli", "PPOFMG27S38L789I"));
        return lista;
    }
}
