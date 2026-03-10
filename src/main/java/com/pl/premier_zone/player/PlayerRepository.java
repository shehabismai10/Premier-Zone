package com.pl.premier_zone.player;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    // void deleteById(Integer id);

    
    // Optional<Player> findById(Integer id);
    

    List<Player> findByName(String name);

    // // 2. تجاهل حالة الأحرف (Mohamed زي mohamed)
    List<Player> findByNameIgnoreCase(String name);

    // // 3. البحث بجزء من الاسم (لو كتبت "حمود" يجيب لك "محمود")
    List<Player> findByNameContainingIgnoreCase(String name);



        List<Player> findByTeam(String team); // Add this method to find players by team without using streams

        List<Player> findByPosition(String position); // Add this method to find players by position without using streams


        List<Player> findByTeamAndPosition(String team, String position); 
        

}


