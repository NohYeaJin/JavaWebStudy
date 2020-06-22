package me.minjun.study.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minjun.study.Domain.Posts.Posts;

@Getter
@NoArgsConstructor
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
