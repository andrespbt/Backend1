package main.com.dh.integrador;

import main.com.dh.integrador.dao.IDao;
import main.com.dh.integrador.dao.impl.AppointmentDAOH2;
import main.com.dh.integrador.dao.impl.DentistDAOH2;
import main.com.dh.integrador.dao.impl.PatientDAOH2;
import main.com.dh.integrador.dao.impl.UserDAOH2;
import main.com.dh.integrador.model.Appointment;
import main.com.dh.integrador.model.Dentist;
import main.com.dh.integrador.model.Patient;
import main.com.dh.integrador.model.User;
import main.com.dh.integrador.service.AppointmentService;
import main.com.dh.integrador.service.DentistService;
import main.com.dh.integrador.service.PatientService;
import main.com.dh.integrador.service.UserService;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws SQLException, IllegalAccessException {



        User user = new User(1L, "Andres","Poblete","user");
        UserService userService = new UserService(new UserDAOH2());
//        userService.save(user);
//        System.out.println(userService.search(1L).toString());



//        // Dentist
//
//
//
//        Dentist dentist = new Dentist(1L, "Juan","Lozano","Admin","AE24CC6");
//        DentistService dentistService = new DentistService(new DentistDAOH2());
//        dentistService.save(dentist);
//
//
//        // Patient
//
//
//        int year = LocalDate.now().getYear() - 1900;
//        int month = LocalDate.now().getMonthValue() - 1;
//        int day = LocalDate.now().getDayOfMonth();
//
//
//        Patient patient = new Patient(1L,"Cristian","Lotorto","User","33214190", new Date(year, month, day));
//        PatientService patientService = new PatientService(new PatientDAOH2());
//        patientService.save(patient);
//
//
//        // Appointment
//
//
//        Appointment appointment = new Appointment(
//                1L,
//               dentist,
//                patient,
//                new Date (122 , 12 , 10),
//                new Time(20 , 00 , 00));
//
//        AppointmentService appointmentService = new AppointmentService(new AppointmentDAOH2());
//
//        System.out.println(appointmentService.save(appointment));

        for (Field field : user.getClass().getDeclaredFields()){

            field.setAccessible(true);
            System.out.println(String.valueOf(field.get(user)));

        }



    }
}
