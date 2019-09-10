package edu.micronaut.controller;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;


import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@MicronautTest
public class HomeControllerTest {

    @Inject
    EmbeddedServer embeddedServer;



   // private  static EmbeddedServer embeddedServer;

    //private static RxHttpClient rxHttpClient;

////
//    @BeforeClass
//    public static  void  setup(){
//        embeddedServer= ApplicationContext.build().run(EmbeddedServer.class);
//        rxHttpClient=embeddedServer.getApplicationContext().createBean(RxHttpClient.class,embeddedServer.getURL());
//    }
////
//    @AfterClass
//    public  static  void cleanup(){
//        if(embeddedServer!=null) {
//            embeddedServer.stop();
//        }
//        if (rxHttpClient!=null){
//            rxHttpClient.stop();
//        }
//    }
//
//    @Test
//    public void canEmailWith() throws Exception{
//        UsernamePasswordCredentials upc=new UsernamePasswordCredentials("user","password");
//        HttpRequest<Object> loginRequest=HttpRequest.POST("/login", upc);
//        HttpResponse<BearerAccessRefreshToken> rsp=rxHttpClient.toBlocking().exchange(loginRequest,BearerAccessRefreshToken.class);
//        assertEquals(rsp.getStatus(),HttpStatus.OK);
//        assertEquals("user",rsp.getBody().get().getUsername());
//    }
//
////    @Test
////    public  void cantLogin(){
////        try{
////            UsernamePasswordCredentials upc=new UsernamePasswordCredentials("user","pass");
////            HttpRequest loginRequest=HttpRequest.POST("/login",upc);
////            HttpResponse<BearerAccessRefreshToken>rsp=rxHttpClient.toBlocking().exchange(loginRequest,BearerAccessRefreshToken.class);
////            fail();
////        }catch (HttpClientResponseException ex){
////            assertEquals(ex.getStatus(),HttpStatus.UNAUTHORIZED);
////        }
////    }


    @Test
    public void testIndex() throws Exception {
        try(RxHttpClient client = embeddedServer.getApplicationContext().createBean(RxHttpClient.class, embeddedServer.getURL())) {
            assertEquals(HttpStatus.OK, client.toBlocking().exchange("/greet/").status());
        }
    }
}
