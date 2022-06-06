package com.atlas.skull.Controller;

import com.atlas.skull.Dto.RoleRequest;
import com.atlas.skull.Dto.TenantRequest;
import com.atlas.skull.Dto.UserRequest;
import com.atlas.skull.Dto.UserResponse;
import com.atlas.skull.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController

public class UserController {

    private final UserService userService;

    @PostMapping("/tenants")
    @PreAuthorize("hasAuthority('ACTUSER')")
    @Operation(summary = "add school")
    public String saveTenant(@RequestBody TenantRequest tenantRequest){

        userService.addTenant(tenantRequest);
        return tenantRequest.getDsKey() + " success!";
    }

    @GetMapping("/tenants")
    @PreAuthorize("hasAuthority('ACTUSER')")
    @Operation(summary = "get schoole")
    public List<TenantRequest> returnAllTenants(){

        return userService.getFromTenants();
    }

    @PostMapping("/roles")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "add new role")
    public String saveRole(@RequestBody RoleRequest roleRequest){

        userService.addRole(roleRequest);
        return roleRequest.getRole() + " success!";
    }

    @GetMapping("/roles")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER')")
    @Operation(summary = "get all authorities")
    public List<RoleRequest> getALlFromRoles(){

        return userService.getFromRoles();
    }

    @PostMapping("/user")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "add new User")
    public String saveUser(@RequestBody UserRequest userRequest){

        userService.addUser(userRequest);
        return userRequest.getFirstName() + " " + userRequest.getLastName() + " registered with username " + userRequest.getUsername();
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CASHIER')")
    @Operation(summary = "get all Users")
    public List<UserResponse> getAllUsers(){

        return userService.getAllUsers();
    }
}
