package com.integradordh.trabajofinal.security.config;

import com.integradordh.trabajofinal.models.AppUser;
import com.integradordh.trabajofinal.models.AppUserRoles;
import com.integradordh.trabajofinal.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private IUserRepository userRepository;

    @Autowired
    public DataLoader(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("password");
        userRepository.save(new AppUser("User", "userTest@test.com", "userTest@test.com", password, AppUserRoles.USER));
        userRepository.save(new AppUser( "UserAdmin", "userAdmin@admin.com", "userAdmin@admin.com", password, AppUserRoles.ADMIN));
    }
}
