package service;

import dao.IDao;
import model.Appointment;
import java.sql.SQLException;
import java.util.List;

public class AppointmentService {

    private IDao<Appointment> appointmentDAO;

    public AppointmentService() {
    }

    public AppointmentService(IDao<Appointment> appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }

    public void setAppointmentDAO(IDao<Appointment> appointmentDAO) {
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
