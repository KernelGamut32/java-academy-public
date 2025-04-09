package gts.spring.registration.controller;

import gts.spring.registration.dto.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
    private String baseUrl;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        String tokenUrl = baseUrl + "/protocol/openid-connect/token";
        // Prepare the request body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", clientId);
        requestBody.add("username", request.username());
        requestBody.add("password", request.password());
        requestBody.add("grant_type", "password");
        requestBody.add("client_secret", clientSecret);
        ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, requestBody, String.class);
        // Return the response from Keycloak
        return ResponseEntity.ok(response.getBody());
    }
}
