package lk.slt.marketplacer.service.impl;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class KeycloakServiceImpl {

    private final String baseUrl = "YOUR_KEYCLOAK_BASE_URL";
    private final String clientId = "YOUR_CLIENT_ID";
    private final String clientSecret = "YOUR_CLIENT_SECRET";
    private final String realm = "YOUR_REALM";

    private final RestTemplate restTemplate = new RestTemplate();

    public String getRptTokens(String accessToken) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("grant_type", "YOUR_RPT_GRANT_TYPE");
            params.put("audience", clientId);
            String url = baseUrl + "/realms/" + realm + "/YOUR_OIDC_TOKEN_URL";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);
            HttpEntity<Map<String, String>> request = new HttpEntity<>(params, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new HttpClientErrorException(response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while getting RPT tokens: " + e.getMessage());
        }
    }

    public String getPAT() {
        try {
            String url = baseUrl + "/realms/" + realm + "/protocol/openid-connect/token";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "YOUR_PAT_GRANT_TYPE");
            params.add("client_id", clientId);
            params.add("client_secret", clientSecret);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> responseBody = response.getBody();
                return (String) responseBody.get("access_token");
            } else {
                throw new HttpClientErrorException(response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while getting PAT: " + e.getMessage());
        }
    }

}
