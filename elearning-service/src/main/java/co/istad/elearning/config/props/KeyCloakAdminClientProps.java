package co.istad.elearning.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "keycloak")
public class KeyCloakAdminClientProps {
    private String serverUrl;
    private String clientId;
    private String ClientSecret;
    private String realm;
    private String targetRealm;
}
