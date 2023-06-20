package board.board.service;

import board.board.dto.MemberInfoDTO;
import board.board.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;

    public String idDoubleCheck(String id) {return userMapper.idDoubleCheck(id);};

    public int signUp(MemberInfoDTO dto) {return userMapper.signUp(dto);}

    public String memberCheck(HashMap<String, Object> param) {return userMapper.memberCheck(param);}

    public int changePassword(MemberInfoDTO dto) {return userMapper.changePassword(dto);}

    public String Login(HashMap<String, Object> param) {return userMapper.Login(param);}
}
