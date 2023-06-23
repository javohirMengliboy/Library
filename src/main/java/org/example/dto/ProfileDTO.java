package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.enums.ProfileRole;
import org.example.enums.ProfileStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ProfileDTO {
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String phone;
    private ProfileStatus status;
    private ProfileRole role;
    private LocalDateTime createdDate;

    public ProfileDTO(Integer id, String name, String surname, String login, String password, String phone, ProfileStatus status, ProfileRole role, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.role = role;
        this.createdDate = createdDate;
    }

}
