package com.homepage.home.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "HOME_PAGE_ROLE")
@NoArgsConstructor
@AllArgsConstructor
public class Role extends SuperEntity implements GrantedAuthority {

    @Id
    @SequenceGenerator(sequenceName = "role_seq" , name = "role" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "role")
    private long id;
    @Column(length = 25)
    private String authority;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_fk",nullable = false )
    private Account account;

    public Role(String authority, Account account) {
        this.authority = authority;
        this.account = account;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @PrePersist
    public void PrePersist(){
        setInsertTime(new Date(System.currentTimeMillis()));
        setInsertUser("amir");

    }

    @Override
    public boolean equals(Object role) {
        if (this == role) return true;
        if (role == null || getClass() != role.getClass()) return false;
        Role roleObj = (Role) role;
        return id == roleObj.id &&
                Objects.equals(authority, roleObj.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }
}
