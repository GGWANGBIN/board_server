package board.board.controller;

import board.board.dto.MemberInfoDTO;
import board.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;


@RestController
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private PasswordEncoder pwEncoder;

    private final UserService userService;

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

        String rawPw = "";
        String encodePw = "";

        rawPw = dto.getPassword();
        encodePw = pwEncoder.encode(rawPw);
        dto.setPassword(encodePw);

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

        MemberInfoDTO Login = userService.Login(param);

        if(!pwEncoder.matches((CharSequence) param.get("password"),Login.getPassword())) {
            return "false";
        } else {
            session.setAttribute("userid", param.get("id"));
            return "Login";
        }

    }

}
