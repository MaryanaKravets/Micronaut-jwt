package edu.micronaut.controller;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@MicronautTest
public class HomeControllerTest {

    private  static EmbeddedServer embeddedServer;

    private static RxHttpClient rxHttpClient;


    @BeforeEach
    public  void  setup(){
        embeddedServer= ApplicationContext.build().run(EmbeddedServer.class);
        rxHttpClient=embeddedServer.getApplicationContext().createBean(RxHttpClient.class,embeddedServer.getURL());
    }

    @AfterAll
    public static   void cleanup(){
        if(embeddedServer!=null) {
            embeddedServer.stop();
        }
        if (rxHttpClient!=null){
            rxHttpClient.stop();
        }
    }

    @Test
    public void canEmailWith() {
        UsernamePasswordCredentials upc = new UsernamePasswordCredentials("user", "password");
        HttpRequest<Object> loginRequest = HttpRequest.POST("/login", upc);
        HttpResponse<BearerAccessRefreshToken> rsp = rxHttpClient.toBlocking().exchange(loginRequest, BearerAccessRefreshToken.class);
        assertEquals(rsp.getStatus(),HttpStatus.OK);
        assertEquals("user",rsp.getBody().get().getUsername());
    }

    @Test
    public  void cantLogin(){
        try{
            UsernamePasswordCredentials upc=new UsernamePasswordCredentials("user","pass");
            HttpRequest<Object> loginRequest=HttpRequest.POST("/login",upc);
            HttpResponse<BearerAccessRefreshToken>rsp=rxHttpClient.toBlocking().exchange(loginRequest,BearerAccessRefreshToken.class);
            fail();
        }catch (HttpClientResponseException ex){
            assertEquals(ex.getStatus(),HttpStatus.UNAUTHORIZED);
        }
   }
}
