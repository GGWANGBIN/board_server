package board.board.service;

import board.board.dto.SignUpRequestDTO;
import board.board.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;

    public String idDoubleCheck(String id) {return userMapper.idDoubleCheck(id);};

    public int signUp(SignUpRequestDTO dto) {return userMapper.signUp(dto);}

}
