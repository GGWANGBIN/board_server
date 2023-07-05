package board.board.dto;

import lombok.Data;

@Data
public class BoardDTO {
    String id;
    String title;
    String content;
    String hit;
    String writer;
    String createDate;
    String commentCnt;
    String secret;
    String searchMenu;
    String searchText;
    String userid;
}
