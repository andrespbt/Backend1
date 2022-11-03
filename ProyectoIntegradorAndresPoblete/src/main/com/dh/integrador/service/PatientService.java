package main.com.dh.integrador.service;

import main.com.dh.integrador.dao.IDao;
import main.com.dh.integrador.model.Patient;

import java.sql.SQLException;
import java.util.List;

public class PatientService {

    public IDao<Patient> patientDao;

    public PatientService() {
    }

    public PatientService(IDao<Patient> patientDao) {
        this.patientDao = patientDao;
    }

    public void setPatientDao(IDao<Patient> patientDao) {
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
