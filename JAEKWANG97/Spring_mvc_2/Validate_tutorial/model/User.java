package Validation.model;

import jakarta.validation.constraints.NotEmpty;

public class User {
    @NotEmpty(message = "이름은 필값 입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필값 입니다.")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
