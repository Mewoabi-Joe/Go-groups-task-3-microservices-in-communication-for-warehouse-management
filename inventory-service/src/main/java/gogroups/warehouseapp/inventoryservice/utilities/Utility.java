package gogroups.warehouseapp.inventoryservice.utilities;

import gogroups.warehouseapp.inventoryservice.exceptions.BadRequestException;

import java.util.Arrays;
import java.util.List;

public class Utility {

    static List<String> validRoles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");

    public static boolean isValidRole(String role){
        return validRoles.stream().anyMatch(validRole -> role.equals(validRole));
    }

    public static void validateRole(String role){
        if(!isValidRole(role)) throw new BadRequestException("The role you passed is not valid");
    }


}
