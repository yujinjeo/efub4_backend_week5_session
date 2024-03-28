package efub.session.blog.account.controller;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.dto.AccountResponseDto;
import efub.session.blog.account.dto.AccountUpdateRequestDto;
import efub.session.blog.account.dto.SignUpRequestDto;
import efub.session.blog.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController // JSON 형태로 객체 데이터 반환 ( @Controller + @ResponseBody )
                // @Controller : Model 객체를 만들어 데이터를 담고 View를 반환
                // @ResponseBody : 객체를 HTTP 요청의 body 내용으로 매핑하여 클라이언트로 전송
@RequestMapping("/accounts") // 요청이 들어온 URI와 Annotation의 value가 일치하면 해당 클래스나 메소드 실행
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    /* 계정 생성 기능 */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AccountResponseDto signUp(@RequestBody @Valid final SignUpRequestDto requestDto) {
        Long id = accountService.signUp(requestDto);
        Account findAccount = accountService.findAccountById(id);
        return AccountResponseDto.from(findAccount);
    }

    /* 계정 조회 기능 (1명) */
    @GetMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public AccountResponseDto getAccount(@PathVariable Long accountId) {
        Account findAccount = accountService.findAccountById(accountId);
        return AccountResponseDto.from(findAccount);
    }

    /* 계정 프로필 수정 */
    @PatchMapping("/profile/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public AccountResponseDto update(@PathVariable final Long accountId, @RequestBody @Valid final AccountUpdateRequestDto requestDto) {
        Long id = accountService.update(accountId, requestDto);
        Account findAccount = accountService.findAccountById(id);
        return AccountResponseDto.from(findAccount);
    }

    /* 계정 삭제 (휴면 계정으로) */
    @PatchMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String withdraw(@PathVariable long accountId) {
        accountService.withdraw(accountId);
        return "성공적으로 탈퇴되었습니다.";
    }

    /* 계정 삭제 (db에서도 삭제) */
    @DeleteMapping("/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable long accountId) {
        accountService.delete(accountId);
        return "성공적으로 탈퇴가 완료되었습니다";
    }
}
