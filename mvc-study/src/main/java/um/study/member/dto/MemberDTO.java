package um.study.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long Id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
}
