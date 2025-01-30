package application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Requête pour récupérer les utilisateurs (adapte selon ta table)
        userDetailsManager.setUsersByUsernameQuery(
                "SELECT pseudo, mot_de_passe, enabled FROM UTILISATEURS WHERE pseudo = ?"
        );

        // Requête pour récupérer les rôles (adapte selon ta structure)
        userDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT pseudo, administrateur FROM UTILISATEURS WHERE pseudo = ?"
        );

        return userDetailsManager;
    }



    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.GET, "/encheres").permitAll();
            auth.requestMatchers("/css/*", "/js/*", "/img/*").permitAll();
            auth.requestMatchers("/encheres/profilUtilisateur").authenticated();
            auth.requestMatchers("/encheres" , "/encheres/connexion", "/encheres/inscription").permitAll();
            auth.anyRequest().denyAll();
        });

        http.formLogin(form -> form
                .loginPage("/encheres/connexion")
                .loginProcessingUrl("/encheres/connexion")
                .defaultSuccessUrl("/encheres", true)
                .failureUrl("/encheres/connexion?error=true")
                .permitAll()
        );

        http.logout(logout -> logout
                .logoutUrl("/encheres/deconnexion")
                .logoutSuccessUrl("/encheres/connexion?logout=true")
                .permitAll()
        );

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Désactive le hashage des mots de passe
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }



}