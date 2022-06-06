package com.atlas.skull.Mapper;

import com.atlas.skull.BaseSkullModel.Tenants;
import com.atlas.skull.BaseSkullModel.User;
import com.atlas.skull.BaseSkullModel.UserRoles;
import com.atlas.skull.BaseSkullModel.UsersData;
import com.atlas.skull.Dto.RoleRequest;
import com.atlas.skull.Dto.TenantRequest;
import com.atlas.skull.Dto.UserRequest;
import com.atlas.skull.Dto.UserResponse;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@AllArgsConstructor
@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "id", ignore = true)
    public abstract Tenants saveIntoTenants(TenantRequest tenantRequest);

    public abstract TenantRequest getFromTenants(Tenants tenants);

    @Mapping(target = "id", ignore = true)
    public abstract UserRoles saveIntoUserRoles(RoleRequest roleRequest);

    public abstract RoleRequest getFromRoles(UserRoles userRoles);

    @Mapping(target = "school", ignore = true)
    @Mapping(target = "usersData", source = "usersData")
    @Mapping(target = "userRoles", source = "userRoles")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", expression = "java(true)")
    public abstract User saveIntoUser(UserRequest userRequest, UserRoles userRoles, UsersData usersData);

    @Mapping(target = "role", source = "userRoles.role")
    @Mapping(target = "lastName", source = "usersData.lastName")
    @Mapping(target = "firstName", source = "usersData.firstName")
    @Mapping(target = "email", source = "usersData.email")
    public abstract UserResponse getFromUser(User user);
}
