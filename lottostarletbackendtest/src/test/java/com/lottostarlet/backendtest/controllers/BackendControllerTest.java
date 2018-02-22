package com.lottostarlet.backendtest.controllers;


import com.lottostarlet.backendtest.BackendApplication;
import com.lottostarlet.backendtest.app.domainlogic.BackendService;
import com.lottostarlet.backendtest.app.model.GameResult;
import com.lottostarlet.backendtest.app.model.Round;
import com.lottostarlet.backendtest.app.persistence.GameResultRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
public class BackendControllerTest {

	@LocalServerPort
	int localPort;
	
	@Autowired
	GameResultRepository gameResultRepository;

	private GameResult game;

	private TestRestTemplate template = new TestRestTemplate();

	@Before
	public void setUp() {
		BackendService service = new BackendService();
		List<Round> rounds = service.playGame();
		game = new GameResult();
		game.setRounds(rounds);
	}

	@After
	public void clean() {
		gameResultRepository.deleteAll();
	}

	private String getBaseUrl() {
		return "http://localhost:" + localPort;
	}

	@Test
	public void testGetGameResultWithWrongId() {
		ResponseEntity<Void> entity = template.getForEntity(getBaseUrl() + "/api/result/1", Void.class);
		Assert.assertEquals("The request should return status 404", HttpStatus.NOT_FOUND, entity.getStatusCode());
	}

	@Test
	public void testGetGameResultWithCorrectId() {
		game = gameResultRepository.save(game);
		assertThat(gameResultRepository.findById(game.getId()).getId()).isEqualTo(game.getId());
	}

	@Test
	public void testCreateNewGameResult() {
		URI uri = template.postForLocation(getBaseUrl() + "/api/result", null);
		assertNotNull("URI should contain the location of the new game result.", uri);

		ResponseEntity<GameResult> entity = template.getForEntity(uri, GameResult.class);
		assertEquals("The request should return status 200", HttpStatus.OK, entity.getStatusCode());

		GameResult gameResult = entity.getBody();
		assertNotNull("The response body should contain newly inserted game result.", gameResult);
	}

	@Test
	public void testGetAllGameResults() {
		game = gameResultRepository.save(game);
		ResponseEntity<List<GameResult>> response =
				template.exchange(getBaseUrl() + "/api/result",
						HttpMethod.GET, null, new ParameterizedTypeReference<List<GameResult>>() {
						});
		List<GameResult> results = response.getBody();

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertNotNull("Returned list of game results should not be null.", results);
		assertThat(results).isNotEmpty();
	}

}
