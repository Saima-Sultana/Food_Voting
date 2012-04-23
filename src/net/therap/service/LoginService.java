package net.therap.service;

import net.therap.domain.User;

/**
 * Created by IntelliJ IDEA.
 * User: saima
 * Date: 4/22/12
 * Time: 5:23 PM
 * To change this template use File | Settings | File Templates.
 */
public interface LoginService {
    public User checkUser(String userName, String password);
}
