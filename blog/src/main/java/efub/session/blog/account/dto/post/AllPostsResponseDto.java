package efub.session.blog.account.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class AllPostsResponseDto {
    private List<PostResponseDto> posts;
    private long count;

}
