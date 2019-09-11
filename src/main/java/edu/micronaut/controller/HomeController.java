package edu.micronaut.controller;

import edu.micronaut.service.GreetingService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.inject.Inject;
import java.security.Principal;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/greet")
public class HomeController {

    @Inject
    private GreetingService greetingService;

        @Get("/{name}")
        public String greet(String name,Principal principal) {
            return principal.getName()+greetingService.greet(name);
        }
}
