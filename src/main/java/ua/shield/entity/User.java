package ua.shield.entity;

import ua.shield.enum_.Role;

import java.util.Set;

/**
 * Created by sa on 30.11.17.
 */
public class User {
    private int id;
    private String login;
    private String password;
    private String fullName;
    private String birthday;
    private String phone;
    private String email;
    private Set<Role> roles;
    private int status;
}
