package edu.micronaut.service;

import javax.inject.Singleton;

@Singleton
public class GreetingService{

    public  String greet(String name){
        return " "+ name+ " welcome to our site!";
    }
}
