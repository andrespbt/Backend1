package com.integradordh.trabajofinal.repository;

import com.integradordh.trabajofinal.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

}
