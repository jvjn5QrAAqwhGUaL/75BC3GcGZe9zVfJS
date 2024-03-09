package moe.dic1911.esun_library.data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.HashMap;

public class MyUserDetail extends User {
    private HashMap<String, String> sessionStorage;
    public MyUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public MyUserDetail(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public HashMap<String, String> getSessionStorage() {
        if (sessionStorage == null) sessionStorage = new HashMap<>();
        return sessionStorage;
    }
}
