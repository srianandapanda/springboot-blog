package nesteen.springboot.project.SpringBootProject.service;

import nesteen.springboot.project.SpringBootProject.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

public class UserDetailServiceUse implements UserDetails {

    private User user;

    public  UserDetailServiceUse(User theUser){
        user=theUser;

    }
    public  UserDetailServiceUse(){

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Set<User> roles = user.getRole();
        //return Collections.singleton(new SimpleGrantedAuthority("USER"));
       // return null;
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        //authorities.add(new SimpleGrantedAuthority("AUTHOR"));
        //authorities.add(new SimpleGrantedAuthority("ADMIN"));
        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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

    public String getName(){
        return user.getName();
    }

    public String roleName(){
        return user.getRole();
    }
}
