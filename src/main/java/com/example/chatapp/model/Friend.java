package com.example.chatapp.model;

import com.example.chatapp.model.pk.FriendId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Table(name = "friend")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    @Id
    private FriendId id;
    private ZonedDateTime createdAt;
}
