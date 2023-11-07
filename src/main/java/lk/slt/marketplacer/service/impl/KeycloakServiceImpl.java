package lk.slt.marketplacer.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author harindu.sul@gmail.com
 */
@Service
@Slf4j
public class KeycloakServiceImpl {

    @Value("${keycloak.baseUrl}")
    private String baseUrl;
    @Value("${keycloak.adminClientId}")
    private String clientId;
    @Value("${keycloak.adminClientSecret}")
    private String clientSecret;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.targetRealm}")
    private String targetRealm;
    private Keycloak keycloak;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    private void init() {
        keycloak = KeycloakBuilder.builder()
                .serverUrl(baseUrl)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();

    }


    public UserRepresentation searchByUsername(String username) {
        keycloak.tokenManager().getAccessToken();
        log.info("searching user on keycloak by username: {} ", username);
        try {
            List<UserRepresentation> users = keycloak.realm(targetRealm)
                    .users()
                    .searchByUsername(username, true);
            return users.isEmpty() ? null : users.get(0);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

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
            params.add("grant_type", "client_credentials");
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

    public Map<String, String> login(String code, String codeVerifier, String redirectUri) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            Map<String, String> params = new HashMap<>();
            params.put("grant_type", "authorization_code");
            params.put("client_id", clientId);
            params.put("client_secret", clientSecret);
            params.put("code", code);
            params.put("code_verifier", codeVerifier);
            params.put("redirect_uri", redirectUri);

            String url = baseUrl + "/realms/" + realm + "/YOUR_OIDC_TOKEN_URL";

            HttpEntity<Map<String, String>> request = new HttpEntity<>(params, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> responseBody = response.getBody();
                String accessToken = (String) responseBody.get("access_token");
                String idToken = (String) responseBody.get("id_token");
                String refreshToken = (String) responseBody.get("refresh_token");

                Map<String, String> result = new HashMap<>();
                result.put("accessToken", accessToken);
                result.put("idToken", idToken);
                result.put("refreshToken", refreshToken);

                // Assuming __getRptTokens is a method in this class
                String rptAccessToken = getRptTokens(accessToken);
                result.put("rptAccessToken", rptAccessToken);

                return result;
            } else {
                throw new HttpClientErrorException(response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while login: " + e.getMessage());
        }
    }
}

/**
 * # Keycloak
 * KC_BASE_URL="https://kc.dev.tmdsandbox.tk"
 * KC_REALM="surge-hub"
 * KC_TOKEN_URL="protocol/openid-connect/token"
 * KC_USER_INFO_URL="protocol/openid-connect/userinfo"
 * KC_TOKEN_EXCHANGE_GRANT_TYPE="authorization_code"
 * KC_CLIENT_ID="surge-hub-client"
 * KC_CLIENT_SECRET="deC3KyNf2Caxi0XqIuva9JFBCUCFPeaw"
 * KC_RPT_TOKEN_EXCHANGE_GRANT_TYPE="urn:ietf:params:oauth:grant-type:uma-ticket"
 * KC_REFRESH_TOKEN_GRANT_TYPE="refresh_token"
 * KC_PAT_GRANT_TYPE="client_credentials"
 * KC_REDIRECT_URI="login/callback"
 */
