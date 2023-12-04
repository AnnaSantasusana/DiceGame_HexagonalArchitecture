package com.itacademy.dicegame.infrastructure.rest.contoller;

import com.itacademy.dicegame.application.service.PlayerManagementService;
import com.itacademy.dicegame.domain.models.dto.GameDto;
import com.itacademy.dicegame.domain.models.dto.PlayerDto;
import com.itacademy.dicegame.domain.models.dto.request.AuthenticationRequest;
import com.itacademy.dicegame.domain.models.dto.request.RegisterRequest;
import com.itacademy.dicegame.domain.models.dto.response.AuthenticationResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerManagementService playerManagementService;

    public PlayerController(PlayerManagementService diceService) {
        this.playerManagementService = diceService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerPlayer(@RequestBody @Valid RegisterRequest request) {
        playerManagementService.registerPlayer(request);
        return new ResponseEntity<>("Player registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticatePlayer(@RequestBody @Valid AuthenticationRequest request) {
        AuthenticationResponse token = playerManagementService.authenticatePlayer(request);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/{playerId}")
    public ResponseEntity<GameDto> createGame(@PathVariable Long playerId) {
        GameDto createdGame = playerManagementService.createGame(playerId);
        return new ResponseEntity<>(createdGame, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getAllPlayersSuccessRate() {
        List<PlayerDto> players = playerManagementService.getPlayersSuccessRate();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<PlayerDto> updatePlayerName(@PathVariable Long playerId, @RequestBody String name) {
        PlayerDto playerDto = playerManagementService.modifyPlayerName(playerId, name);
        return new ResponseEntity<>(playerDto, HttpStatus.OK);
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<String> deletePlayerGames(@PathVariable Long playerId) {
        playerManagementService.deleteGames(playerId);
        return new ResponseEntity<>("Games deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<List<GameDto>> getAllSinglePlayerGames(@PathVariable Long playerId) {
        List<GameDto> gamesDto = playerManagementService.getAllGamesByPlayerId(playerId);
        return new ResponseEntity<>(gamesDto, HttpStatus.OK);
    }


    @GetMapping("/averageRate")
    public ResponseEntity<String> getPlayersAverageSuccessRate() {
        return new ResponseEntity<>(playerManagementService.getAverageSuccessRate(), HttpStatus.OK);
    }

    @GetMapping("/worstPlayer")
    public ResponseEntity<PlayerDto> getWorstPlayer() {
        PlayerDto playerDto = playerManagementService.getWorstPlayer();
        return new ResponseEntity<>(playerDto, HttpStatus.OK);
    }

    @GetMapping("/bestPlayer")
    public ResponseEntity<PlayerDto> getBestPlayer() {
        PlayerDto playerDto = playerManagementService.getBestPlayer();
        return new ResponseEntity<>(playerDto, HttpStatus.OK);

    }


}
