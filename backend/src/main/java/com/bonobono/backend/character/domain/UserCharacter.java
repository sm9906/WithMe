package com.bonobono.backend.character.domain;


import com.bonobono.backend.global.entity.BaseTimeEntity;
import com.bonobono.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"member_id","main"})})
public class UserCharacter extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member; //유저 정보(한 유저가 여러개의 캐릭터를 가짐)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private OurCharacter ourCharacter; //캐릭터id(정보를가지고 있음)


    private String custom_name;

    //경험치
    @Column(columnDefinition = "int default 0")
    private Integer experience;

    @Column(columnDefinition = "boolean default false")
    private Boolean main;

    @Builder
    public UserCharacter(OurCharacter ourCharacter, Member member, boolean main, String custom_name){
        this.main = main;
        this.ourCharacter = ourCharacter;
        this.member = member;
        this.custom_name = custom_name;
    }


    //경험치 수정
    public void updateExperience(int experience){
        this.experience=experience;
    }


    public void upgradeCharacter(OurCharacter upgradedCharacter) {
        this.ourCharacter = upgradedCharacter;
        this.experience= this.getExperience()-100;
    }

    @PrePersist
    public void setCustomNameDefaultValue() {
        this.custom_name=this.custom_name == null ?  ourCharacter.getName():this.custom_name;
        this.experience=this.experience==null? 0:this.experience;
        this.main = this.main ==null? false: this.main;
    }

    // 글 수정
    public void updateName(String custom_name){
        this.custom_name=custom_name;
    }


    public void updateMain(Boolean main) {
        this.main=main;
    }

}
