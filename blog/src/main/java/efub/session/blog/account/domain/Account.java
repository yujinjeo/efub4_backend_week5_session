package efub.session.blog.account.domain;

import efub.session.blog.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static efub.session.blog.account.domain.AccountStatus.REGISTERED;

@Entity // 테이블과의 매핑
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터가 없는 기본 생성자 생성 (이때, protected 접근 제어자로 생성자 접근 제어)
@Getter
public class Account extends BaseTimeEntity {
    @Id // 테이블의 PK 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성전략
    @Column(name = "account_id", updatable = false) // 테이블 컬럼 매핑
    private Long accountId;

    @Column(nullable = false, length = 60) // sql문과 유사하게, not null 조건, length 조건 추가
    private String email;

    @Column(nullable = false)
    private String encodedPassword;

    @Column(nullable = false, updatable = false, length = 16)
    private String nickname;

    private String bio;

    @Enumerated(EnumType.STRING) // enum 타입
    private AccountStatus status;

    @Builder // 객체 생성
    public Account(String email, String password, String nickname, String bio) {
        this.email = email;
        this.encodedPassword = password;
        this.nickname = nickname;
        this.bio = bio;
        this.status = REGISTERED;
    }

    //닉네임 수정하기
    public void updateAccount(String bio, String nickname) {
        this.bio = bio;
        this.nickname = nickname;
    }

    //회원 탈퇴
    public void withdrawAccount() {
        this.status = AccountStatus.UNREGISTERED;
    }

}
