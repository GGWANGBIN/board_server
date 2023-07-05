package board.board.controller;

import board.board.dto.MemberInfoDTO;
import board.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;


@RestController
@RequiredArgsConstructor
public class HelloController {

    private final UserService userService;

    @PostMapping("/hello")
    public String hello(){
        System.out.println();
        return "ㅎㅎ";
    }

    @GetMapping("/IdDoubleCheck")
    public String idDoubleCheck(@RequestParam String id) {

        String idDoubleCheck = userService.idDoubleCheck(id);

        if(idDoubleCheck != null) {
            return "pass";
        }
        else {
            return "block";
        }
    }

    @PostMapping("/SignUp")
    public void signUp(@RequestBody MemberInfoDTO dto) {
        int signUp = userService.signUp(dto);
    }

    @GetMapping("/MemberCheck")
    public String memberCheck(@RequestParam HashMap<String, Object> param) {
        String memberCheck = userService.memberCheck(param);

        if(memberCheck == null) {
            return "missmatch";
        }
        else return "match";
    }

    @PostMapping("/ChangePassword")
    public void changePassword(@RequestBody MemberInfoDTO dto) {
        int changePassword = userService.changePassword(dto);
    }

    @GetMapping("/Login")
    public String Login(@RequestParam HashMap<String, Object> param, HttpSession session) {

        String Login = userService.Login(param);

        if(Login == null) {
            return "false";
        } else {
            session.setAttribute("userid", param.get("id"));
            return "Login";
        }

    }

}
