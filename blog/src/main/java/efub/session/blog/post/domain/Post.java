package efub.session.blog.post.domain;

import efub.session.blog.account.domain.Account;
import efub.session.blog.post.dto.post.PostRequestDto;
import efub.session.blog.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", updatable = false)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder
    public Post(Account account, String title, String content){
        this.account=account;
        this.title=title;
        this.content=content;
    }

    public void update(PostRequestDto dto, Account account){
        this.account=account;
        this.title=dto.getTitle();
        this.content=dto.getContent();
    }

}
