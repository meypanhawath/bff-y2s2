package co.istad.elearning.features.auth;

import co.istad.elearning.config.props.KeyCloakAdminClientProps;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final KeyCloakAdminClientProps keyCloakAdminClientProps;
    private final Keycloak keycloak;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {

        if (!registerRequest.password().equals(registerRequest.confirmedPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Passwords don't match"
            );
        }

        UserRepresentation user = new UserRepresentation();
        user.setUsername(registerRequest.username());
        user.setEmail(registerRequest.email());
        user.setFirstName(registerRequest.firstName());
        user.setLastName(registerRequest.lastName());


//        Map<String, List<String>> attributes = new HashMap<>();
//        attributes.put("gender", List.of(registerRequest.gender().getGender()));
//        attributes.put("biography", List.of(registerRequest.biography()));
//        user.setAttributes(attributes);
        // Set custom attributes
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("gender", List.of(registerRequest.gender().getGender()));
        attributes.put("biography", List.of(registerRequest.biography()));
        user.setAttributes(attributes);


        user.setEnabled(true);
        user.setEmailVerified(false);


        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType("password");
        credential.setValue(registerRequest.password());
        user.setCredentials(List.of(credential));

        UsersResource usersResource = keycloak
                .realm(keyCloakAdminClientProps.getTargetRealm())
                .users();

        try(Response response = usersResource.create(user)) {
            log.info("keycloak status : {}", response.getStatus());
            if (response.getStatus() == HttpStatus.CREATED.value()){
                UserRepresentation createdUser = usersResource
                        .search(user.getUsername())
                        .getFirst();

                return RegisterResponse.builder()
                        .id(createdUser.getId())
                        .username(createdUser.getUsername())
                        .email(createdUser.getEmail())
                        .firstName(createdUser.getFirstName())
                        .lastName(createdUser.getLastName())
                        .gender(createdUser.getAttributes().get("gender").getFirst())
                        .biography(createdUser.getAttributes().get("biography").getFirst())
                        .build();
            }
        }

        return null;
    }
}
