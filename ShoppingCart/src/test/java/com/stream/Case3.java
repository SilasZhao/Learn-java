package com.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Case3 {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Role{
        private List<Permission> permissions;
    }
    @Data
    @AllArgsConstructor
    class Permission{
        /**
         * 权限名称
         */
        private String name;
    }
    List<Role> roleList;
    @Before
    public void init(){
        roleList = new ArrayList<>();
        Role adminRole = new Role();
        List<Permission>  adminPermissionList = new ArrayList<>();
        adminPermissionList.add(new Permission("删除"));
        adminPermissionList.add(new Permission("查看"));
        adminPermissionList.add(new Permission("导出"));
        adminRole.setPermissions(adminPermissionList);

        Role userRole = new Role();
        List<Permission> userPermissionList = new ArrayList<>();
        userPermissionList.add(new Permission("删除"));
        userPermissionList.add(new Permission("新建"));
        userPermissionList.add(new Permission("查看"));
        userPermissionList.add(new Permission("修改"));
        userRole.setPermissions(userPermissionList);

        roleList.add(userRole);
        roleList.add(adminRole);
    }
    @Test
    public void findPermission(){
        roleList.stream()
                .flatMap(role -> role.getPermissions().stream())
                .peek(permission ->
                        System.out.println("新的流元素" + permission))
                .distinct()
                .forEach(System.out::println);
    }

}
