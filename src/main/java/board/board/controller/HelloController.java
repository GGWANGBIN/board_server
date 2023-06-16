package board.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PostMapping("hello")
    public String hello(){
        System.out.println();
        return "hidddddsffdsfdsdd";
    }



}
