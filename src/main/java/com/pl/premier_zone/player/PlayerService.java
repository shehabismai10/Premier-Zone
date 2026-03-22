package com.pl.premier_zone.player;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import jakarta.transaction.Transactional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    // الـ Constructor اليدوي - برافو عليك ده اللي هيعدي الـ Build
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayerById(Integer id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, 
                        "The player was not found by id: " + id));
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> getPlayersByTeam(String team) {
        return playerRepository.findByTeam(team);
    }

    public List<Player> getPlayersByName(String name) {
        String cleanName = name.trim();
        return playerRepository.findByNameContainingIgnoreCase(cleanName);
    }

    public List<Player> getPlayerByPosition(String position) {
        return playerRepository.findByPosition(position);
    }

    public List<Player> getPlayersByTeamAndPosition(String team, String position) {
        return playerRepository.findByTeamAndPosition(team, position);
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Integer id, Player player) {
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player was not found by id: " + id));

        existingPlayer.setName(player.getName());
        existingPlayer.setTeam(player.getTeam());
        existingPlayer.setPosition(player.getPosition());

        return playerRepository.save(existingPlayer);
    }

    @Transactional
    public void deletePlayer(Integer id) {
        playerRepository.deleteById(id);
    }
}