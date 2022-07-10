package com.charlotte.kies.security;

import com.charlotte.kies.model.GoogleUser;
import com.google.api.client.auth.openidconnect.IdToken;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
@Component
public class GoogleTokenUtil {

    @Value("${CLIENT_ID}")
    private String CLIENT_ID;

    private NetHttpTransport transport = new NetHttpTransport();
    private JsonFactory jsonFactory = new GsonFactory();

    private GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            // Specify the CLIENT_ID of the app that accesses the backend:
            .setAudience(Collections.singletonList(CLIENT_ID))
//            .setIssuer("https://accounts.google.com")
            // Or, if multiple clients access the backend:
            //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
            .build();

//     (Receive idTokenString by HTTPS POST)

    public boolean validateToken(String token, UserDetails userDetails) throws IOException, GeneralSecurityException {
        GoogleIdToken idToken = GoogleIdToken.parse(jsonFactory, token);
//        boolean verifiedToken = verifier.verify(idToken);
        String id_token = idToken.getPayload().getJwtId();
        GoogleIdToken verifiedToken = verifier.verify(token);

        if (verifiedToken != null) {
            IdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

//            getUsername
//            Get profile information from payload

            String email = ((GoogleIdToken.Payload) payload).getEmail();
            boolean emailVerified = Boolean.valueOf(((GoogleIdToken.Payload) payload).getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            return true;

           // Use or store profile information

        } else {
            System.out.println("Invalid ID token.");
            return false;
        }
    }

    public String getUsernameFromToken(String jwtToken) throws GeneralSecurityException, IOException {
        GoogleIdToken idToken = GoogleIdToken.parse(jsonFactory, jwtToken);
//        idToken = verifier.verify(jwtToken);

        if (idToken != null) {
            IdToken.Payload payload = idToken.getPayload();
            return ((GoogleIdToken.Payload) payload).getEmail();
        }
        else return new String();
    }

    public GoogleUser getGoogleUserFromToken(String jwtToken) throws IOException {
        GoogleIdToken idToken = GoogleIdToken.parse(jsonFactory, jwtToken);
        IdToken.Payload payload = idToken.getPayload();
//
        GoogleUser googleUser = new GoogleUser();
        // Print user identifier
        String userId = payload.getSubject();
        System.out.println("User ID: " + userId);

//         Get profile information from payload

        googleUser.setEmail(((GoogleIdToken.Payload) payload).getEmail());
        googleUser.setEmail_verified( Boolean.valueOf(((GoogleIdToken.Payload) payload).getEmailVerified()));
        googleUser.setName((String) payload.get("name"));
        googleUser.setPicture(payload.get("picture").toString());
        googleUser.setFamily_name(payload.get("family_name").toString());
        googleUser.setGiven_name((payload.get("given_name").toString()));

        return googleUser;


    }
}
