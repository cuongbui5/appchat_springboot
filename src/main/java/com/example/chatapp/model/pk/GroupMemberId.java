package com.example.chatapp.model.pk;

import com.example.chatapp.model.Conversation;
import com.example.chatapp.model.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
public class GroupMemberId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "conversation_id",referencedColumnName = "id")
    private Conversation conversation;

}
