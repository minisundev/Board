package com.minisun.board.post.entity;

import com.minisun.board.global.entity.Timestamped;
import com.minisun.board.post.dto.PostRequest;
import com.minisun.board.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String title;

    @Column(length = 5000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(PostRequest request, User user){
        this.title = request.getTitle();
        this.content = request.getContent();
        this.user = user;
    }

    public void update(PostRequest request){
        if(request.getTitle()!=null){
            this.title = request.getTitle();
        }
        if(request.getContent()!=null){
            this.content = request.getContent();
        }
    }

}
