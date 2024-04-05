package efub.session.blog.account.controller;

import efub.session.blog.account.domain.Post;
import efub.session.blog.account.dto.post.AllPostsResponseDto;
import efub.session.blog.account.dto.post.PostRequestDto;
import efub.session.blog.account.dto.post.PostResponseDto;
import efub.session.blog.account.service.PostService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public PostResponseDto createNewPost(@RequestBody @Valid final PostRequestDto dto){
        Post savedPost = postService.createNewPost(dto);
        return PostResponseDto.from(savedPost, savedPost.getAccount().getNickname());
    }

    @GetMapping
    public AllPostsResponseDto getAllPosts(){
        List<PostResponseDto> list= new ArrayList<>();
        List<Post> posts = postService.findAllPosts();
        for (Post post : posts){
            PostResponseDto dto = PostResponseDto.from(post, post.getAccount().getNickname());
            list.add(dto);
        }

        long count = postService.countAllPosts();

        return new AllPostsResponseDto(list,count);
    }

    @GetMapping("/{id}")
    public PostResponseDto getOnePost(@PathVariable(name = "id")Long id){
        Post post = postService.findPostById(id);
        return PostResponseDto.from(post,post.getAccount().getNickname());
    }
}
