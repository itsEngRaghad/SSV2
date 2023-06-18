package com.example.studysquadv2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(25) not null")
    private String username;


    @Column(columnDefinition = "varchar(300) not null")
    @NotEmpty(message = "username can't be empty")
    private String password;


    @NotEmpty(message = "role can't be empty")
    @Column(columnDefinition = "varchar(25) not null check ( role='ADMIN' or role='STUDENT' or role='TUTOR')")
    private String role;


    //--------------------Relation---------------//

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "myUser")
    @PrimaryKeyJoinColumn
    private Student student;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "myUser")
    @PrimaryKeyJoinColumn
    private Tutor tutor;


    //---------Other Method--------------//

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
