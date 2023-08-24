package um.study.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import um.study.member.dto.MemberDTO;

@Entity
@Getter
@Setter
@Table(name = "member1")
public class MemberEntity {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AI
    private Long id;

    @Column(unique = true) //unique 제약조건
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());

        return memberEntity;
    }
}
