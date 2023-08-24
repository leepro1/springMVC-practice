package um.study.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
