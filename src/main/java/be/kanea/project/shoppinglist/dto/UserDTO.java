package be.kanea.project.shoppinglist.dto;

/**
 *
 * @author cristiane
 */
public class UserDTO {
    
    private static final long serialVersionUID = -652874597239526920L;
    
    private String username;
    
    private String accessToken;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    
    
}
