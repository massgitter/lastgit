package com.atlas.skull.Service;

import com.atlas.skull.BaseSkullModel.Tenants;
import com.atlas.skull.BaseSkullModel.User;
import com.atlas.skull.BaseSkullModel.UserRoles;
import com.atlas.skull.BaseSkullModel.UsersData;
import com.atlas.skull.BaseSkullRepository.TenantsRepository;
import com.atlas.skull.BaseSkullRepository.UserRepository;
import com.atlas.skull.BaseSkullRepository.UserRolesRepository;
import com.atlas.skull.BaseSkullRepository.UsersDataRepository;
import com.atlas.skull.Dto.*;
import com.atlas.skull.Exception.ResourceNotFound;
import com.atlas.skull.Mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service

public class UserService {

    private final UserRolesRepository userRolesRepository;
    private final UsersDataRepository usersDataRepository;
    private final TenantsRepository tenantsRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void addTenant(TenantRequest tenantRequest){

        tenantsRepository.save(userMapper.saveIntoTenants(tenantRequest));
    }

    public List<TenantRequest> getFromTenants(){

        return tenantsRepository.findAll().stream().map(userMapper::getFromTenants).collect(Collectors.toList());
    }

    public void addRole(RoleRequest roleRequest){

        userRolesRepository.save(userMapper.saveIntoUserRoles(roleRequest));
    }

    public List<RoleRequest> getFromRoles(){

        return userRolesRepository.findAll().stream().map(userMapper::getFromRoles).collect(Collectors.toList());
    }

    public void addUser(UserRequest userRequest){

        Tenants tenants = tenantsRepository.findById(userRequest.getAuthority()).orElseThrow(()-> new ResourceNotFound("school not found"));

        UserRoles userRoles = userRolesRepository.findById(userRequest.getAuthority()).orElseThrow(()-> new ResourceNotFound("role not found"));

        UsersData usersData = new UsersData();
        usersData.setFirstName(userRequest.getFirstName());
        usersData.setLastName(userRequest.getLastName());
        usersData.setEmail(userRequest.getEmail());
        usersData.setPhoneNumber(userRequest.getPhone());

        usersDataRepository.save(usersData);

        User user = new User();
        user.setUsername(userRequest.getUsername());
       user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        user.setSchool(tenants.getDsKey());
        user.setActive(true);
        user.setUserRoles(userRoles);
        user.setUsersData(usersData);

        userRepository.save(user);
    }

    public List<UserResponse> getAllUsers(){

        return userRepository.findAll().stream().map(userMapper::getFromUser).collect(Collectors.toList());
    }
}
