package com.company.kun_uz.util;

import com.company.kun_uz.dto.JwtDTO;
import com.company.kun_uz.enums.ProfileRole;
import com.company.kun_uz.exps.NotPermissionExseption;

import javax.servlet.http.HttpServletRequest;

public class HttpHeaderUtil {

    public static Integer getId(HttpServletRequest request, ProfileRole requiredRole) {
        JwtDTO jwtDTO = (JwtDTO) request.getAttribute("jwtDTO");
        Integer id = jwtDTO.getId();

        if (requiredRole != null) {
            if (!requiredRole.equals(jwtDTO.getRole())) {
                throw new NotPermissionExseption("Not Access");
            }
        }
        return id;
    }

    public static Integer getId(HttpServletRequest request) {
        return getId(request, null);
    }

}
