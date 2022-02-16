package toyProject.juniorForum.domain.member;

import lombok.Data;

@Data
public class Member {
    private long id;
    private String loginId;
    private String name;
    private String password;

    public Member() {
    }

    public Member(String loginId, String name, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }
}
