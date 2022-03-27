package nesteen.springboot.project.SpringBootProject.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());

    }

    /*@Override
    protected void configure(HttpSecurity http) throws Exception{

    }*/
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/login","/post/blog/**","/post/viewpost","/post/create").permitAll()
                .antMatchers("/post/filter/page/**","/post/sorted/page/**","/post/search/page/**","/post/comment").permitAll()
                .antMatchers("/post/register").permitAll()
                .antMatchers("/post/newPost","/post/publish","/post/update","/post/delete").hasAnyAuthority("ADMIN","AUTHOR")
                .antMatchers("/post/comment/**","/post/updateComment","/post/deleteComment").hasAnyAuthority("ADMIN","AUTHOR","USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/post/login").permitAll()
                .defaultSuccessUrl("/post/blog")
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/post/logout-success").permitAll();
    }

    /*@Bean
    @Override
    protected UserDetailsService userDetailsService(){

        List<UserDetails> users=new ArrayList<>();
        users.add(User.withDefaultPasswordEncoder().username("nesteen").password("9876nesteen").roles("USER").build());
        return new InMemoryUserDetailsManager(users);
    }*/
}
