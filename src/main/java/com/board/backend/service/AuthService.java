package com.board.backend.service;

import com.board.backend.domain.Login.LoginRepository;
import com.board.backend.domain.User.User;
import com.board.backend.domain.User.UserRepository;
import com.board.backend.dto.LoginDto;
import com.board.backend.exception.AuthException;
import com.board.backend.exception.TxException;
import com.board.backend.utils.JwtTokenUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenUtil jwtTokenUtil;


    AuthService(UserRepository userRepository,LoginRepository loginRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtTokenUtil jwtTokenUtil){
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String login(User user)  {
        String userId = user.getUserId();
        String password = user.getPassword();
        LoginDto loginDto = new LoginDto();
        loginDto.setUserId(userId);

        Optional<User> users = userRepository.findById(userId);
        if (!users.isPresent()) {
            throw new AuthException.NoExistUserId(userId);
        }

        User selectedUser = users.get();
        if (!bCryptPasswordEncoder.matches(password, bCryptPasswordEncoder.encode(users.get().getPassword()))) {
            loginDto.setLastLoginFailDate(new Date());
            insert(loginDto);
            throw  new AuthException.PasswordNotMatched();
        }
        String accessToken = jwtTokenUtil.createToken(userId, selectedUser.getUserName(), "USER");
        loginDto.setLastLoginDate(new Date());
        loginDto.setAccessToken(accessToken);
        loginDto.setAccessTokenExpiredDate(jwtTokenUtil.expireTime());
        insert(loginDto);
        return accessToken;
    }

    @Transactional
    public void insert (LoginDto loginDto){
        try{
            loginRepository.save(loginDto.toEntity());
        }catch (Exception e) {
            throw new TxException.Rollback();
        }
    }

}
