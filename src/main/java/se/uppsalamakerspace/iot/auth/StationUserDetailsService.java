package se.uppsalamakerspace.iot.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import se.uppsalamakerspace.iot.model.Station;
import se.uppsalamakerspace.iot.repository.StationRepository;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by fredl2 on 2016-11-01.
 */
public class StationUserDetailsService implements UserDetailsService {

    private final StationRepository stationRepo;

    @Autowired
    public StationUserDetailsService(StationRepository stationRepo) {
        this.stationRepo = stationRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Station station = stationRepo.findOne(username);
        if(station == null) {
            throw new UsernameNotFoundException("StationController med ID " + username + " finns inte");
        }
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Arrays.asList(new SimpleGrantedAuthority("ROLE_STATION"));
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return username;
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
        };
    }
}
