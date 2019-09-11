package edu.micronaut.configuration;

import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.ArrayList;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        String email = authenticationRequest.getIdentity().toString();
        String password = authenticationRequest.getSecret().toString();
        if (email.equals("user") && password.equals("password")) {
            return Flowable.just(new UserDetails(authenticationRequest.getIdentity().toString(),new ArrayList<>()));
        }
        return Flowable.just(new AuthenticationFailed());
    }
}
