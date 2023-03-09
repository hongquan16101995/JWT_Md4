package com.example.test.service;

import com.example.test.model.Users;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public static List<Users> listUser = new ArrayList<>();
    static {
        Users userKai = new Users(1, "kai", "123456");
        userKai.setRoles(new String[] { "ROLE_ADMIN" });
        Users userSena = new Users(2, "sena", "123456");
        userSena.setRoles(new String[] { "ROLE_USER" });
        listUser.add(userKai);
        listUser.add(userSena);
    }
    public List<Users> findAll() {
        return listUser;
    }
    public Users findById(int id) {
        for (Users user : listUser) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    public boolean add(Users user) {
        for (Users userExist : listUser) {
            if (user.getId() == userExist.getId() || StringUtils.equals(user.getUsername(), userExist.getUsername())) {
                return false;
            }
        }
        listUser.add(user);
        return true;
    }
    public void delete(int id) {
        listUser.removeIf(user -> user.getId() == id);
    }
    public Users loadUserByUsername(String username) {
        for (Users user : listUser) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    public boolean checkLogin(Users user) {
        for (Users userExist : listUser) {
            if (StringUtils.equals(user.getUsername(), userExist.getUsername())
                    && StringUtils.equals(user.getPassword(), userExist.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
