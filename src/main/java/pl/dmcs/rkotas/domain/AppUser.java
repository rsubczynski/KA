package pl.dmcs.rkotas.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="appuser")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @NotNull
    @Column(nullable=false)
    private String email;

    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppUserRole> appUserRole = new HashSet<AppUserRole>(0);

    private boolean enable;

    private long uidUser;

    private String secretFlatCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AppUserRole> getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(Set<AppUserRole> appUserRole) {
        this.appUserRole = appUserRole;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public long getUidUser() {
        return uidUser;
    }

    public void setUidUser(long uid) {
        this.uidUser = uid;
    }

    public String getSecretFlatCode() {
        return secretFlatCode;
    }

    public void setSecretFlatCode(String secretFlatCode) {
        this.secretFlatCode = secretFlatCode;
    }
}

