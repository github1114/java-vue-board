package com.board.backend.api.v1;

import com.board.backend.service.AuthService;
import com.board.backend.domain.User.User;
import com.board.backend.domain.User.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoginApi {

    private final AuthService authService;
    private final UserRepository userRepository;

    LoginApi(AuthService authService, UserRepository userRepository){
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> login(@AuthenticationPrincipal @RequestBody User user){
        return  new ResponseEntity<>(authService.login(user), HttpStatus.OK);
    }
/*
    @PostMapping("/login")
    public ResponseEntity<?> getAuthentication (@AuthenticationPrincipal User user){
        return  new ResponseEntity<>( userRepository.findById(user.getUserId()).get(), HttpStatus.OK);
    }
*/
}
