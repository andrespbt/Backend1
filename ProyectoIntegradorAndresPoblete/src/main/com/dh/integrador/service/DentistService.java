package main.com.dh.integrador.service;

import main.com.dh.integrador.dao.IDao;
import main.com.dh.integrador.model.Appointment;
import main.com.dh.integrador.model.Dentist;

import java.sql.SQLException;
import java.util.List;

public class DentistService {

    private IDao<Dentist> dentistDao;

    public DentistService() {
    }

    public DentistService(IDao<Dentist> dentistDao) {
        this.dentistDao = dentistDao;
    }

    public void setDentistDao(IDao<Dentist> dentistDao) {
        this.dentistDao = dentistDao;
    }

    public Dentist save(Dentist dentist) throws SQLException {
        dentistDao.save(dentist);
        return dentist;
    }

    public void delete(Long id) throws SQLException {
        dentistDao.delete(id);
    }

    public Dentist search(Long id) throws SQLException {
        return dentistDao.search(id);
    }

    public List<Dentist> searchAll() throws SQLException {
        return dentistDao.searchAll();
    }
}
