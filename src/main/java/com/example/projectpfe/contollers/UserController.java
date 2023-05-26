package com.example.projectpfe.contollers;


import com.example.projectpfe.dto.UserDto;
import com.example.projectpfe.pojo.emuns.RoleEnum;
import com.example.projectpfe.pojo.req.UserReq;
import com.example.projectpfe.pojo.req.UserUpdateReq;
import com.example.projectpfe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Set;

@RestController
@RequestMapping("user")
@CrossOrigin("*")

public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserReq userReq){
        return new ResponseEntity<>(userService.createUser(userReq), HttpStatus.OK);
    }

    @GetMapping("/{igg}")
    public  ResponseEntity<UserDto> getUserbyIgg(@PathVariable("igg") String igg){
        return new ResponseEntity<>(userService.getUserbyIgg(igg), HttpStatus.OK);
    }

    @PutMapping("/{igg}")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserUpdateReq userReq, @PathVariable("igg") String igg){
        return new ResponseEntity<>(userService.updateUser(userReq,igg), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<Page<UserDto>> allUsers(@RequestParam("page") int page, @RequestParam("size")  int size){
        return new ResponseEntity<>(userService.allUsers(size,page), HttpStatus.OK);
    }
    @DeleteMapping("/{igg}")
    public ResponseEntity<String> deleteUser(@PathVariable("igg") String igg){
        userService.deleteUser(igg);
        return new ResponseEntity<String>("user deleted successfully!.", HttpStatus.OK);
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<Set<UserDto>>filter(
            @RequestParam(value="igg", required = false) String igg,
            @RequestParam(value="firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required=false) String lastName,
            @RequestParam(value = "city", required=false) String city,
            @RequestParam(value = "country", required=false) String country,
            @RequestParam(value = "roleEnum", required=false) RoleEnum roleEnum
    ){
        return new ResponseEntity<>(userService.filter(igg,firstName, lastName,city,country,roleEnum),HttpStatus.OK);
    }

/*
@GetMapping(value = "/filter")
public ResponseEntity<Page<UserDto>> filter(
        @RequestParam(value = "igg", required = false) String igg,
        @RequestParam(value = "firstName", required = false) String firstName,
        @RequestParam(value = "lastName", required = false) String lastName,
        @RequestParam(value = "city", required = false) String city,
        @RequestParam(value = "country", required = false) String country,
        @RequestParam(value = "roleEnum", required = false) RoleEnum roleEnum,
        @RequestParam(value = "size", defaultValue = "5") int size,
        @RequestParam(value = "page", defaultValue = "0") int page
) {
    //Page<UserDto> filteredPage = userService.filter(igg, firstName, lastName, city, country, roleEnum, size, page);
    //return ResponseEntity.ok(filteredPage);
    return new ResponseEntity<>(userService.filter(igg,firstName, lastName,city,country,roleEnum,size, page),HttpStatus.OK);

}
*/
}
