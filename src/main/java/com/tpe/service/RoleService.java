package com.tpe.service;

import com.tpe.domain.Role;
import com.tpe.domain.enums.RoleType;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleTypeByName(RoleType roleType){
        Role role = roleRepository.findByType(roleType).orElseThrow(()->new ResourceNotFoundException("Role not found: " + roleType.name()));
        return role;
    }
}
