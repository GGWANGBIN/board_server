package board.board.mapper;

import board.board.dto.SignUpRequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    String idDoubleCheck(String id);

    int signUp(SignUpRequestDTO dto);
}
