package board.board.controller;

import board.board.dto.BoardDTO;
import board.board.dto.CommentDTO;
import board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/BoardList")
    public List boardList(HttpSession session,BoardDTO dto) {

        dto.setUserid((String) session.getAttribute("userid"));

        List<BoardDTO> boardList = boardService.boardList(dto);

        System.out.println(dto);
        System.out.println(boardList);

        return boardList;
    }

    @GetMapping("/MyBoardList")
    public List myBoardList(HttpSession session,BoardDTO dto) {

        dto.setUserid((String) session.getAttribute("userid"));

        List<BoardDTO> boardList = boardService.myBoardList(dto);

        return boardList;
    }

    @GetMapping("/BoardDetail")
    public List boardDetail(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {

        /* 조회수 로직 */
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("postView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("["+ id.toString() +"]")) {
                int updateHit = boardService.updateHit(id);
                oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24); 							// 쿠키 시간
                response.addCookie(oldCookie);
            }
        } else {
            int updateHit = boardService.updateHit(id);
            Cookie newCookie = new Cookie("postView", "[" + id + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24); 								// 쿠키 시간
            response.addCookie(newCookie);
        }

        List<BoardDTO> boardDetail = boardService.boardDetail(id);

        return boardDetail;
    }

    @GetMapping("/GetComment")
    public List getComment(@RequestParam String id) {

        List<CommentDTO> commentList = boardService.getComment(id);

        return commentList;
    }

    @PostMapping("/AddComment")
    public void addComment(@RequestBody CommentDTO dto) {
        int addComment = boardService.addComment(dto);
    }

    @PostMapping("/ModifyComment")
    public void modifyComment(@RequestBody CommentDTO dto) {
        int modifyComment = boardService.modifyComment(dto);
    }

    @PostMapping("/GoNewBoard")
    public void goNewBoard(@RequestBody BoardDTO dto) {
        int goNewBoard = boardService.goNewBoard(dto);
    }

    @PostMapping("/GoModifyBoard")
    public void goModifyBoard(@RequestBody BoardDTO dto) {
        int goModifyBoard = boardService.goModifyBoard(dto);
    }

    @DeleteMapping("/DeleteComment")
    public void deleteComment(@RequestBody CommentDTO dto) {
        int deleteComment = boardService.deleteComment(dto);
    }

    @DeleteMapping("/DeleteBoard")
    public void deleteBoard(@RequestBody BoardDTO dto) {
        int deleteBoard = boardService.deleteBoard(dto);
    }

    @PutMapping("/SecretYtoN")
    public void scretYtoN(@RequestBody BoardDTO dto) {
        int scretYtoN = boardService.secretYtoN(dto);
    }

    @PutMapping("/SecretNtoY")
    public void scretNtoY(@RequestBody BoardDTO dto) {
        int scretYtoN = boardService.secretNtoY(dto);
    }

    @PutMapping("/SecretYtoNComment")
    public void scretYtoNComment(@RequestBody CommentDTO dto) {

        System.out.println(dto);

        int scretYtoN = boardService.secretYtoNComment(dto);
    }

    @PutMapping("/SecretNtoYComment")
    public void scretNtoYComment(@RequestBody CommentDTO dto) {
        int scretYtoN = boardService.secretNtoYComment(dto);
    }

    @GetMapping("/MyComment")
    public List myComment(HttpSession session) {

        String userid = (String) session.getAttribute("userid");

        List<BoardDTO> myCommentList = boardService.myCommentList(userid);

        return myCommentList;
    }
}
