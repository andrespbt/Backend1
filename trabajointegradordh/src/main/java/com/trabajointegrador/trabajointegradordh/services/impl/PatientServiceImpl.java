package com.trabajointegrador.trabajointegradordh.services.impl;

import com.trabajointegrador.trabajointegradordh.dao.IDao;
import com.trabajointegrador.trabajointegradordh.dao.impl.PatientDAOH2;
import com.trabajointegrador.trabajointegradordh.model.Patient;
import com.trabajointegrador.trabajointegradordh.services.PatientService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    public IDao<Patient> patientDao;

    public PatientServiceImpl(IDao<Patient> patientDao) {
        this.patientDao = patientDao;
    }

    public Patient save(Patient patient) throws SQLException {
        patientDao.save(patient);
        return patient;
    }

    public void delete(Long id) throws SQLException {
        patientDao.delete(id);
    }

    public Patient search(Long id) throws SQLException {
        return patientDao.search(id);
    }

    public List<Patient> searchAll() throws SQLException {
        return patientDao.searchAll();
    }
}
