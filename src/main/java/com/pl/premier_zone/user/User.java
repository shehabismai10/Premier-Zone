package com.pl.premier_zone.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User implements org.springframework.security.core.userdetails.UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (unique = true)
    private String username;
    private String email;
    private String password;



    
    @Enumerated(EnumType.STRING)
    private Role role; //THIS IS FOR AUTHORIZATION (ROLE BASED ACCESS CONTROL) NOT FOR AUTHENTICATION (WHO YOU ARE)



    @Override
    public boolean isAccountNonExpired() {
        return true; // we can implement this later if we want to add account expiration feature
    }

    @Override
    public boolean isAccountNonLocked() {   
        return true; // we can implement this later if we want to add account locking feature
    }


    @Override
    public boolean isCredentialsNonExpired() { 
        return true; // we can implement this later if we want to add password expiration feature
    }

    @Override
    public boolean isEnabled() {
        return true; // we can implement this later if we want to add account enabling/disabling feature
    }

    

    //this method will check the role of person who is trying to log 
    @Override
    public java.util.Collection<? extends org.springframework.security.core.GrantedAuthority> getAuthorities() {
        // بنحول الـ Role بتاعنا لـ SimpleGrantedAuthority عشان سبرينج يفهمه
        return java.util.List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority(role.name()));
    }

    
}
