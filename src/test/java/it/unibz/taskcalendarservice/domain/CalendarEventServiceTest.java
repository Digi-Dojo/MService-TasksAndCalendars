package it.unibz.taskcalendarservice.domain;

public class CalendarEventServiceTest {
}

/* to do something like this (it is another code)
*
* package com.startupsdigidojo.battleships.battleship;

import com.startupsdigidojo.battleships.battleship.domain.Battleship;
import com.startupsdigidojo.battleships.battleship.domain.BattleshipRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BattleshipIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private BattleshipRepository battleshipRepository;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setup() {
        baseUrl = baseUrl + ":" + port + "/v1/battleships";

        ensureEmptyDatabase();
    }

    @Test
    public void itCreatesANewBattleship() {
        // given
        String name = "millenium falcon";
        Battleship inputBattleship = new Battleship(name);

        // when
        Battleship response = restTemplate.postForObject(baseUrl, inputBattleship, Battleship.class);

        // then
        assertThat(inputBattleship.getId()).isNull();
        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(Battleship.class);
        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo(name);
    }

    @Test
    public void itReadsAnExitingBattleship() {
        // given
        String name = "Enterprise";
        Battleship battleship = battleshipRepository.save(new Battleship(name));
        String getOneUrl = baseUrl + "/" + battleship.getId();

        // when
        Battleship response = restTemplate.getForObject(getOneUrl, Battleship.class);

        // then
        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(Battleship.class);
        assertThat(response.getId()).isEqualTo(battleship.getId());
        assertThat(response.getName()).isEqualTo(name);
    }

    private void ensureEmptyDatabase() {
        battleshipRepository.deleteAll();
    }
}
*
*/