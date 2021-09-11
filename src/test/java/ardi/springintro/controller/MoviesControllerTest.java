package ardi.springintro.controller;

import ardi.springintro.SpringIntroApplication;
import ardi.springintro.model.Movie;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringIntroApplication.class)
@AutoConfigureWebTestClient
class MoviesControllerTest {

  public static final String MOVIE_TESTING_1 = "Movie testing 1";

  String TESTING = "WOLOLO";

  @Autowired
  WebTestClient client;

  static MockWebServer mockWebServer;

  @BeforeAll
  static void beforeAll() throws Exception{
    mockWebServer = new MockWebServer();
    mockWebServer.start(10001);

    mockWebServer.setDispatcher(new Dispatcher() {
      @Override
      public MockResponse dispatch(RecordedRequest recordedRequest) throws InterruptedException {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        mockResponse.setResponseCode(200);

        String filePath="";
        System.out.println("===========" +recordedRequest.getRequestLine()+"=============");
        try {
          switch (recordedRequest.getPath()){
            case "/films":
              filePath = "src/test/resources/jsonResponse/filmsResponse.json";
              break;
            case "/films/1":
              filePath = "src/test/resources/jsonResponse/film1Response.json";
              break;



          }
          System.out.println(filePath);
          FileInputStream fileInputStream = new FileInputStream(filePath);
          String content = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8.name());
          mockResponse.setBody(content);
        } catch (Exception e) {
          System.out.println("ERRORRR" + e.getMessage());
        }
        return mockResponse;
      }
    });
  }

  @AfterAll
  public static void afterAll() throws Exception {
    mockWebServer.shutdown();
  }

  @BeforeEach
  public void setup() {
    Movie movie = new Movie();
    movie.setJudul(TESTING);
    movie.setEpisode(1);
    client.post()
        .uri("/movies")
        .body(BodyInserters.fromValue(movie))
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  public void getMoviesTest() {

    List<Movie> response = client.get()
        .uri("/movies")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(new ParameterizedTypeReference<List<Movie>>() {})
        .returnResult()
        .getResponseBody();

    System.out.println(response);

    assertNotNull(response);
    assertTrue(response.size() > 0);
    assertEquals("Judul Film Pertama--MOCK", response.get(0).getJudul());
    assertEquals(4,response.get(0).getEpisode());
    assertEquals("Judul Film Kedua--MOCK", response.get(1).getJudul());
    assertEquals(1,response.get(1).getEpisode());
  }
//
//  @Test
//  public void postMovie_failed() {
//    client.post()
//        .uri("/movies")
//        .exchange()
//        .expectStatus()
//        .is4xxClientError();
//  }

  @Test
  public void getMovie() {
    Movie response = client.get()
        .uri("/movies/1")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(new ParameterizedTypeReference<Movie>() {})
        .returnResult()
        .getResponseBody();

    assertNotNull(response);
    assertEquals("Judul Film Pertama--MOCK", response.getJudul());
    assertEquals(4,response.getEpisode());
  }

//  @Test
//  public void getMovieEpisode() {
//    Movie response = client.get()
//        .uri(uriBuilder -> uriBuilder.path("/movies-episode")
//            .queryParam("episode", 1)
//            .build()
//        )
//        .exchange()
//        .expectStatus()
//        .isOk()
//        .expectBody(new ParameterizedTypeReference<Movie>() {})
//        .returnResult()
//        .getResponseBody();
//
//    assertNotNull(response);
//  }
}