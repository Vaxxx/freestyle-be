package ng.com.createsoftware.freestylebe;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "12345678";
        String vaxPwd = "Vakporize";
        String encodedRawPassword = encoder.encode(rawPassword);
        String encodedVaxPwd = encoder.encode(vaxPwd);
        System.out.println(encodedRawPassword);
    }
}
