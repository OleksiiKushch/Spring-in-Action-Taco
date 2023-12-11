package tacos.service;

import tacos.entity.User;

public interface UserService {

    User findUserByUsername(String username);
    User create(User user);
}
