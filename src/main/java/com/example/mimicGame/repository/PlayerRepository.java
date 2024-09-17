package com.example.mimicGame.repository;

import com.example.mimicGame.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
