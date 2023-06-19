package board.board.controller;

import board.board.dto.IdDoubleCheckDTO;
import board.board.dto.SignUpRequestDTO;
import board.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class HelloController {

    private final UserService userService;

    @PostMapping("hello")
    public String hello(){
        System.out.println();
        return "ㅎㅎ";
    }

    @GetMapping("/idDoubleCheck")
    public String idDoubleCheck(@RequestParam String id) {

        String idDoubleCheck = userService.idDoubleCheck(id);

        if(idDoubleCheck != null) {
            return "pass";
        }
        else {
            return "block";
        }

    }

    @PostMapping("/signUp")
    public String signUp(@RequestBody SignUpRequestDTO dto) {

        int signUp = userService.signUp(dto);

        return "";

    }



}
