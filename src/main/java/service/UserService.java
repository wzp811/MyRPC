package service;

import common.User;

public interface UserService {
    User getUserByUserId(Integer id);
    Integer insertUserId(User user);
}
