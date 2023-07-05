package board.board.controller;

import board.board.dto.BoardDTO;
import board.board.dto.CommentDTO;
import board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/BoardList")
    public List boardList(HttpSession session) {

        String userid = (String) session.getAttribute("userid");

        List<BoardDTO> boardList = boardService.boardList(userid);

        return boardList;
    }

    @GetMapping("/MyBoardList")
    public List myBoardList(HttpSession session,BoardDTO dto) {

        dto.setUserid((String) session.getAttribute("userid"));

        List<BoardDTO> boardList = boardService.myBoardList(dto);

        System.out.println(boardList);

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

    @PutMapping("/ScretYtoN")
    public void scretYtoN(@RequestBody BoardDTO dto) {
        int scretYtoN = boardService.scretYtoN(dto);
    }

    @PutMapping("/ScretNtoY")
    public void scretNtoY(@RequestBody BoardDTO dto) {
        int scretYtoN = boardService.scretNtoY(dto);
    }

    @GetMapping("/MyComment")
    public List myComment(HttpSession session) {

        String userid = (String) session.getAttribute("userid");

        List<BoardDTO> myCommentList = boardService.myCommentList(userid);

        return myCommentList;
    }
}
