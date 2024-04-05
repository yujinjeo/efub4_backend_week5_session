package efub.session.blog.account.service;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.domain.Post;
import efub.session.blog.account.dto.post.AllPostsResponseDto;
import efub.session.blog.account.dto.post.PostRequestDto;
import efub.session.blog.account.dto.post.PostResponseDto;
import efub.session.blog.account.repository.AccountRepository;
import efub.session.blog.account.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AccountService accountService;

    @Transactional
    public Post createNewPost(PostRequestDto dto){
        Account account = accountService.findAccountById(Long.parseLong(dto.getAccountId()));
        Post post = new Post(account, dto.getTitle(), dto.getContent());
        Post savedPost = postRepository.save(post);
        return savedPost;
    }

    @Transactional(readOnly = true)
    public List<Post> findAllPosts(){
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Transactional(readOnly = true)
    public long countAllPosts(){
        return postRepository.count();
    }

    @Transactional(readOnly = true)
    public Post findPostById(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new EntityNotFoundException("해당 id를 가진 Post를 찾을 수 없습니다.id="+postId));
        return post;
    }
}
