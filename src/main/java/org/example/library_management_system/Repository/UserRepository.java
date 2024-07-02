package org.example.library_management_system.Repository;

import org.example.library_management_system.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
