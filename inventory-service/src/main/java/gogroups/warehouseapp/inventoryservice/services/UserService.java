package gogroups.warehouseapp.inventoryservice.services;

import gogroups.warehouseapp.inventoryservice.exceptions.NotFoundException;
import gogroups.warehouseapp.inventoryservice.models.User;
import gogroups.warehouseapp.inventoryservice.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User getUserWithIdIfExistOrError(Long userId){
        Optional<User> optionalUser = userRepo.findById(userId);
        if(!optionalUser.isPresent()) throw new NotFoundException("No user exist with that Id");
        return optionalUser.get();
    }
}
