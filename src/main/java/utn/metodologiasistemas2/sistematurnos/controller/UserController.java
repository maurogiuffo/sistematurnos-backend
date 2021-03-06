package utn.metodologiasistemas2.sistematurnos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.metodologiasistemas2.sistematurnos.exceptions.UserNotexistException;
import utn.metodologiasistemas2.sistematurnos.model.User;
import utn.metodologiasistemas2.sistematurnos.dto.UserTurns;
import utn.metodologiasistemas2.sistematurnos.service.UserService;


import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id_user}")
    public ResponseEntity getUserById(@PathVariable Integer id_user) {

        ResponseEntity response;

        try {

            response = ResponseEntity.ok(userService.getUserById(id_user));

        } catch (UserNotexistException e) {

            response = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return response;
    }

    @PostMapping("/")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }



    @GetMapping("/")
    public List<User> getAll(@RequestParam(required = false) String firstname){
        return userService.getAllUser(firstname);
    }

    @GetMapping("/category")
    public ResponseEntity getAllCategory(@RequestParam(required = false) String categoryName){
        try {
            return ResponseEntity.ok(userService.getAllUserCategory(categoryName));
        } catch (Exception E) {
           return new ResponseEntity(E.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
