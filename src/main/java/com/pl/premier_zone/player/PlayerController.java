package com.pl.premier_zone.player;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping
    public List<Player> getPlayers(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String team,
        @RequestParam(required = false) String position) 
        { 

        if(name !=null && team != null && position != null){
            return playerService.getPlayersByTeamAndPosition(team, position);
        }
        else if(name != null){   
            return playerService.getPlayersByName(name); // Return all players if no filters are provided
        }
        else if(team != null){   
            return playerService.getPlayersByTeam(team); // Return all players if no filters are provided
        }
        else if(position != null){   
            return playerService.getPlayerByPosition(position); // Return all players if no filters are provided
        }
        
        else{
            return playerService.getPlayers(); // Return all players if no filters are provided}
        }
    }

    
        

        


    
    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        
        Player createdPlayer = playerService.addPlayer(player);

        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED); // Return the created player with a 201 Created status


    }
     
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Integer id, @RequestBody Player player){
        Player updatedPlayer = playerService.updatePlayer(id,player);

        if (updatedPlayer != null) {
            return new ResponseEntity<>(updatedPlayer, HttpStatus.OK); // Return the updated player with a 200 OK status
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return a 404 Not Found status if the player was not found
        }
        
        
        //return entity;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Integer id){
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return a 204 No Content status to indicate successful deletion

    }
}



   



