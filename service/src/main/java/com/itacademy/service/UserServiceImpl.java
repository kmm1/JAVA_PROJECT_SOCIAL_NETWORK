package com.itacademy.service;

import com.itacademy.dao.UserDao;
import com.itacademy.entity.Role;
import com.itacademy.entity.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public SystemUser findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public SystemUser findOneUserByName(String name) {
        return userDao.findOneUserByName(name);
    }

    @Override
    public List<SystemUser> findOneUserByName2(String name) {
        return userDao.findOneUserByName2(name);
    }

    @Override
    public List<SystemUser> findAll() {
        return userDao.findAll();
    }

    @Override
    public Long save(SystemUser user) {
        return userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser foundSystemUser = userDao.findByName(username);
        if (foundSystemUser == null) {
            throw new UsernameNotFoundException("Can't find user by provided name!");
        }
        return new User(foundSystemUser.getName(),
                foundSystemUser.getPassword(), getUserAuthorities(foundSystemUser));
    }

    private Set<GrantedAuthority> getUserAuthorities(SystemUser systemUser) {
        Set<Role> roles = systemUser.getRoles();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public void addExistingRoleToExistingUser(Long roleId, Long userId) {
        userDao.addExistingRoleToExistingUser(roleId, userId);
    }

}