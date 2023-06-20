package board.board.mapper;

import board.board.dto.MemberInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface UserMapper {
    String idDoubleCheck(String id);

    int signUp(MemberInfoDTO dto);

    String memberCheck(HashMap<String, Object> param);

    int changePassword(MemberInfoDTO dto);

    String Login(HashMap<String, Object> param);
}
