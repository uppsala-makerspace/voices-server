package se.uppsalamakerspace.iot.repository;

import org.springframework.data.repository.CrudRepository;
import se.uppsalamakerspace.iot.model.User;

import java.util.List;

/**
 * Created by fredl2 on 04/11/16.
 */
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUserName(String userName);
    List<User> findAllByUserType(User.UserType userType);
}
