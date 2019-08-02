package com.stratesys.octopus.auth.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private LocalDateTime dataCriacao;

    private String username;

    private String email;

    private String status;

    @JsonIgnore
    private String password;

    private Set<String> roles;

//    private Collection<? extends GrantedAuthority> authorities;

    private long clienteId;

    public UserPrinciple(Long id, String nome,
                         String username, String email, String password, LocalDateTime dataCriacao, String status,
                         Set<String> roles,
//                         Collection<? extends GrantedAuthority> authorities,
                         long clienteId) {
        this.id = id;
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.password = password;
        this.roles = roles;
        this.clienteId = clienteId;
//        this.authorities = authorities;
    }

    public static UserPrinciple build(User user) {


//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        authorities.add(new SimpleGrantedAuthority("testeRole1"));
//        authorities.add(new SimpleGrantedAuthority("testeRole2"));
//        authorities.add(new SimpleGrantedAuthority("testeRole3"));


        return new UserPrinciple(
                        user.getId(),
                        user.getNome(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getDataCriacao(),
                        user.getStatus(),
                user.getRoles(),
//                authorities,
                user.getClienteId()
                );
    }

    public Set<String> getRoles() {
        return roles;
    }

    public long getCliente() {
        return clienteId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}
