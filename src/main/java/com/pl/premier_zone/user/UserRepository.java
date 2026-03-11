package com.pl.premier_zone.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // void deleteById(Integer id);

    
    // Optional<User> findById(Integer id);
    

    User findByUsername(String username);
    boolean existsByUsername(String username);

}
