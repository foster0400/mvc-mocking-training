package ardi.springintro.controller;

import ardi.springintro.SpringIntroApplication;
import ardi.springintro.model.People;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringIntroApplication.class)
@AutoConfigureWebTestClient
public class PeopleControllerTest {

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
                        case "/people":
                            filePath = "src/test/resources/jsonResponse/peopleResponse.json";
                            break;
                        case "/people/1":
                            filePath = "src/test/resources/jsonResponse/people1Response.json";
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
    public void getPeople() {
        People response = client.get()
                .uri("/people/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<People>() {})
                .returnResult()
                .getResponseBody();
//        assertTrue(response.size() > 0);
        assertNotNull(response);
        assertEquals("Luke Skywalker--MOCK", response.getName());
        assertEquals("male",response.getGender());
    }

    @Test
    public void getPeopleTest() {

        List<People> response = client.get()
                .uri("/people")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<List<People>>() {})
                .returnResult()
                .getResponseBody();

        System.out.println(response);

        assertNotNull(response);
        assertTrue(response.size() ==2);
//        assertEquals("Luke Skywalkerz", response.get(0).getName());
        assertEquals("Luke Skywalker--MOCK", response.get(0).getName());
        assertEquals("male",response.get(0).getGender());
        assertEquals("C-3PO--MOCK", response.get(1).getName());
        assertEquals("n/a",response.get(1).getGender());
    }


}
