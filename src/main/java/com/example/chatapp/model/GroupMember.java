package com.example.chatapp.model;

import com.example.chatapp.model.pk.GroupMemberId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "group_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupMember {
    @Id
    private GroupMemberId id;
    private ZonedDateTime joinedDateTime;
    private ZonedDateTime leftDateTime;
}
