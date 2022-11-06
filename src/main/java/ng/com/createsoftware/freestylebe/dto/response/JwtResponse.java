package ng.com.createsoftware.freestylebe.dto.response;

import java.util.List;

public class JwtResponse {
    private String token;
  private String type = "Bearer";
  private Long id;
  private String stagename;
  private String email;
  private String age;
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String stagename, String email, String age, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.stagename = stagename;
    this.email = email;
    this.age = age;
    this.roles = roles;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
 
 
public String getStagename() {
    return stagename;
}

public void setStagename(String stagename) {
    this.stagename = stagename;
}

public List<String> getRoles() {
    return roles;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }
}
