package ardi.springintro.controller;

import ardi.springintro.SpringIntroApplication;
import ardi.springintro.model.Species;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringIntroApplication.class)
@AutoConfigureWebTestClient
public class SpeciesControllerTest {

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

//                recordedRequest.
                String filePath="";
                System.out.println("===========" +recordedRequest.getRequestLine()+"=============");
                try {
                    switch (recordedRequest.getPath()){
                        case "/species":
                            filePath = "src/test/resources/jsonResponse/speciesResponse.json";
                            break;
                        case "/species/1":
                            filePath = "src/test/resources/jsonResponse/species1Response.json";
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
    @Test
    public void getaSpeciesTest() {
        Species response = client.get()
                .uri("/species/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<Species>() {})
                .returnResult()
                .getResponseBody();
//        assertTrue(response.size() > 0);
        assertNotNull(response);
        assertEquals("Human--MOCK", response.getName());
        assertEquals("mammal--MOCK",response.getClassification());
    }

    @Test
    public void getSpeciesTest() {

        List<Species> response = client.get()
                .uri("/species")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<List<Species>>() {})
                .returnResult()
                .getResponseBody();

        System.out.println(response);

        assertNotNull(response);
        assertTrue(response.size() ==2);
//        assertEquals("Luke Skywalkerz", response.get(0).getName());
        assertEquals("Human--MOCK", response.get(0).getName());
        assertEquals("mammal--MOCK",response.get(0).getClassification());
        assertEquals("Droid--MOCK", response.get(1).getName());
        assertEquals("artificial--MOCK",response.get(1).getClassification());
    }


}
