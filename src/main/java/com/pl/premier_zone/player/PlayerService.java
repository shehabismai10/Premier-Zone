package com.pl.premier_zone.player;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
@Service
@Component
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers(){
        return playerRepository.findAll();
        
    }
    public List<Player> getPlayersByTeam(String team){
        // return playerRepository.findAll()  //get  all players
        // .stream() //put them in a stream
        // .filter(player -> player.getTeam().equals(team)) //filter and see every player team
        // .collect(Collectors.toList()); //collect them in a list and return it if they have the correct team

        return playerRepository.findByTeam(team); // Use the new method to find players by team without using streams
        
    }

    public List<Player> getPlayersByName(String name){
        String cleanName = name.trim();

        return playerRepository.findByNameContainingIgnoreCase(cleanName);
        // .stream()
        // .filter(player -> player.getName().equals(name))
        // .toList()
    }
    public List<Player> getPlayerByPosition(String position){
        return playerRepository.findByPosition(position);
    }
    public List<Player> getPlayersByTeamAndPosition(String team, String position){
        return playerRepository.findByTeamAndPosition(team, position);
    }


    public Player addPlayer(Player player){
    return playerRepository.save(player);
    }


    public Player updatePlayer(Integer id, Player player){

        Player existingPlayer = playerRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Player was not found by id: " + id));
        

        existingPlayer.setName(player.getName());
        existingPlayer.setTeam(player.getTeam());
        existingPlayer.setPosition(player.getPosition());
        

        return playerRepository.save(existingPlayer);
    }
    @Transactional
    public void deletePlayer(Integer id){
        playerRepository.deleteById(id);
    }

}



