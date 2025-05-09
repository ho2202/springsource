package com.example.security.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClubMember {
    @Id
    private String email;

    private String password;
    private String name;

    private boolean fromSocial;
    // 한 사람에게 여러 롤 부여
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ClubMemberRole> roleSet = new HashSet<>();

    public void addMemberRole(ClubMemberRole memberRole) {
        roleSet.add(memberRole);
    }
}
