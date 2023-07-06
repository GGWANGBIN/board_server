package board.board.dto;

import lombok.Data;

@Data
public class CommentDTO {
  String id;
  String content;
  String createDate;
  String writer;
  String secret;
}
