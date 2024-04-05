package efub.session.blog.post.controller;

import efub.session.blog.post.domain.Post;
import efub.session.blog.post.dto.post.AllPostsResponseDto;
import efub.session.blog.post.dto.post.PostRequestDto;
import efub.session.blog.post.dto.post.PostResponseDto;
import efub.session.blog.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    /*게시글 작성*/
    @PostMapping
    public PostResponseDto createNewPost(@RequestBody @Valid final PostRequestDto dto){
        Post savedPost = postService.createNewPost(dto);
        return PostResponseDto.from(savedPost, savedPost.getAccount().getNickname());
    }

    /*게시글 조회_전체*/
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

    /*게시글 조회_1개*/
    @GetMapping("/{id}")
    public PostResponseDto getOnePost(@PathVariable(name = "id")Long id){
        Post post = postService.findPostById(id);
        return PostResponseDto.from(post,post.getAccount().getNickname());
    }

    /*게시글 수정*/
    @PutMapping("/{id}")
    public PostResponseDto updatePost(@PathVariable(name = "id") Long id,
            @RequestBody @Valid final PostRequestDto dto){
        Long post_id = postService.updatePost(id,dto);
        Post post = postService.findPostById(post_id);
        return PostResponseDto.from(post, post.getAccount().getNickname());
    }

    /*게시글 삭제*/
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable(name = "id") Long id,
                             @RequestParam(name = "accountId") Long account_id){
        Boolean result = postService.deletePost(id, account_id);
        if (!result){
            return "작성자가 아니므로 해당 게시글을 삭제할 수 없습니다.";
        }
        return "성공적으로 삭제되었습니다.";

    }

}
