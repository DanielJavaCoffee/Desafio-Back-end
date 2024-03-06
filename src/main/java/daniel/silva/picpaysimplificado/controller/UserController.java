package daniel.silva.picpaysimplificado.controller;

import daniel.silva.picpaysimplificado.dtos.user.UserCreateRecord;
import daniel.silva.picpaysimplificado.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserCreateRecord> createUser(@RequestBody @Valid UserCreateRecord userCreateRecord){
        var user = userService.createUser(userCreateRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
