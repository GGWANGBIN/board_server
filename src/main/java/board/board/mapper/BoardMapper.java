package board.board.mapper;

import board.board.dto.BoardDTO;
import board.board.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List boardList(String userid);

    List<BoardDTO> boardDetail(String id);

    List<CommentDTO> getComment(String id);

    int addComment(CommentDTO dto);

    int modifyComment(CommentDTO dto);

    int goNewBoard(BoardDTO dto);

    int goModifyBoard(BoardDTO dto);

    int deleteComment(CommentDTO dto);

    int deleteBoard(BoardDTO dto);

    int updateHit(String id);

    List<BoardDTO> myBoardList(BoardDTO dto);

    int secretYtoN(BoardDTO dto);

    int secretNtoY(BoardDTO dto);

    List<BoardDTO> myCommentList(String userid);

    int secretYtoNComment(CommentDTO dto);

    int secretNtoYComment(CommentDTO dto);
}
