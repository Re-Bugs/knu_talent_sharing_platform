package com.zeroone.kstp.service;

import com.zeroone.kstp.DTO.LoginDTO;
import com.zeroone.kstp.domain.User;
import com.zeroone.kstp.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Boolean login(LoginDTO loginDTO)
    {
        Optional<User> user = userRepository.findByStudentNumber(loginDTO.getStudentNumber());
        return user.isPresent() && user.get().getPassword().equals(loginDTO.getPassword());
    }

    // PK로 유저 찾기
    public Optional<User> findUserById(Long id)
    {
        return userRepository.findById(id);
    }

    // 학번으로 유저 찾기
    public Optional<User> findStudentNumber(String studentNumber)
    {
        return userRepository.findByStudentNumber(studentNumber);
    }

    //학번이 중복되지 않는지 검증하는 메서드
    public boolean isStudentNumberExists(@NotBlank(message = "학번은 필수 항목입니다.") String studentNumber)
    {
        return userRepository.existsByStudentNumber(studentNumber);
    }

    public void save(User user)
    {
        userRepository.save(user);
    }
}
