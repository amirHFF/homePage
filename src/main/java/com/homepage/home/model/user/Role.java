package com.homepage.home.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "HOME_PAGE_ROLE",schema = "HP_DB")
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
}
