package gogroups.warehouseapp.userservice.utilities;

import java.util.Arrays;
import java.util.List;

public class Utility {

    static List<String> validRoles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");

    public static boolean isValidRole(String role){
        return validRoles.stream().anyMatch(validRole -> role.equals(validRole));
    }


}
