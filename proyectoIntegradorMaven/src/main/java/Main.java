import dao.impl.AppointmentDAOH2;
import dao.impl.DentistDAOH2;
import dao.impl.PatientDAOH2;
import dao.impl.UserDAOH2;
import model.Appointment;
import model.Dentist;
import model.Patient;
import model.User;
import service.AppointmentService;
import service.DentistService;
import service.PatientService;
import service.UserService;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws SQLException {

        // User


        User user = new User(10L, "Andres","Poblete","user");
        UserService userService = new UserService(new UserDAOH2());
        userService.save(user);
        System.out.println(userService.search(10L).toString());



        // Dentist



        Dentist dentist = new Dentist(10L, "Juan","Lozano","Admin","AE24CC6");
        DentistService dentistService = new DentistService(new DentistDAOH2());
        dentistService.save(dentist);


        // Patient


        int year = LocalDate.now().getYear() - 1900;
        int month = LocalDate.now().getMonthValue() - 1;
        int day = LocalDate.now().getDayOfMonth();


        Patient patient = new Patient(10L,"Cristian","Lotorto","User","33214190", new Date(year, month, day));
        PatientService patientService = new PatientService(new PatientDAOH2());
        patientService.save(patient);


        // Appointment


        Appointment appointment = new Appointment(
                5L,
               dentist,
                patient,
                new Date (122 , 12 , 10),
                new Time(20 , 00 , 00));

        AppointmentService appointmentService = new AppointmentService(new AppointmentDAOH2());

        System.out.println(appointmentService.save(appointment));



    }
}
