package board.board.service;

import board.board.dto.BoardDTO;
import board.board.dto.CommentDTO;
import board.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardMapper boardMapper;


    public List<BoardDTO> boardList(BoardDTO dto) { return boardMapper.boardList(dto);}

    public List<BoardDTO> boardDetail(String id) { return boardMapper.boardDetail(id);}

    public List<CommentDTO> getComment(String id) { return boardMapper.getComment(id);}

    public int addComment(CommentDTO dto) { return boardMapper.addComment(dto);}

    public int modifyComment(CommentDTO dto) { return boardMapper.modifyComment(dto);}

    public int goNewBoard(BoardDTO dto) { return boardMapper.goNewBoard(dto);}

    public int goModifyBoard(BoardDTO dto) { return boardMapper.goModifyBoard(dto);}

    public int deleteComment(CommentDTO dto) { return boardMapper.deleteComment(dto);}

    public int deleteBoard(BoardDTO dto) { return boardMapper.deleteBoard(dto);}

    public int updateHit(String id) { return boardMapper.updateHit(id);}

    public List<BoardDTO> myBoardList(BoardDTO dto) { return boardMapper.myBoardList(dto);}

    public int secretYtoN(BoardDTO dto) { return boardMapper.secretYtoN(dto);}

    public int secretNtoY(BoardDTO dto) { return boardMapper.secretNtoY(dto);}

    public List<BoardDTO> myCommentList(String userid) { return boardMapper.myCommentList(userid);}

    public int secretYtoNComment(CommentDTO dto) { return boardMapper.secretYtoNComment(dto);}

    public int secretNtoYComment(CommentDTO dto) { return boardMapper.secretNtoYComment(dto);}

}
