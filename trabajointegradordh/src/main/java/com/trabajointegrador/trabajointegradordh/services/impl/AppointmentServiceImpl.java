package com.trabajointegrador.trabajointegradordh.services.impl;

import com.trabajointegrador.trabajointegradordh.dao.IDao;
import com.trabajointegrador.trabajointegradordh.model.Appointment;
import com.trabajointegrador.trabajointegradordh.services.AppointmentService;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final IDao<Appointment> appointmentDAO;

    public AppointmentServiceImpl(IDao<Appointment> appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }


    public Appointment save(Appointment appointment) throws SQLException {
        appointmentDAO.save(appointment);
        return appointment;
    }

    public void delete(Long id) throws SQLException {
        appointmentDAO.delete(id);
    }

    public Appointment search(Long id) throws SQLException {
        return appointmentDAO.search(id);
    }

    public List<Appointment> searchAll() throws SQLException {
        return appointmentDAO.searchAll();
    }
}
