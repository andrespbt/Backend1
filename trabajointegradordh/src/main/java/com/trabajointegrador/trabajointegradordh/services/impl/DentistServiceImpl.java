package com.trabajointegrador.trabajointegradordh.services.impl;
import com.trabajointegrador.trabajointegradordh.dao.IDao;
import com.trabajointegrador.trabajointegradordh.dao.impl.DentistDAOH2;
import com.trabajointegrador.trabajointegradordh.model.Dentist;
import com.trabajointegrador.trabajointegradordh.services.DentistService;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service
public class DentistServiceImpl implements DentistService {
    private IDao<Dentist> dentistDao;

    public DentistServiceImpl(IDao<Dentist> dentistDao) {
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
