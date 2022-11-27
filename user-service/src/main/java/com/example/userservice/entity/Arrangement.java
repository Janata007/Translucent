package com.example.userservice.entity;

import com.example.userservice.Priority;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "arrangement")
public class Arrangement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long arrangementId;
    private String name;
    private String code;
    private double duration;
    private LocalDateTime startTime; //yyyy-MM-dd-HH-mm-ss-ns format
    private LocalDateTime endTime;
    private Priority priority;
    private Long createdByUser;
    @ManyToMany
    private List<AppUser> participants;

    public AppUser addNewParticipant(AppUser user) {
        this.participants.add(user);
        return user;
    }

    public AppUser removeParticipant(AppUser user) {
        for (AppUser u : participants) {
            if (u.equals(user)) {
                this.participants.remove(u);
                break;
            }
        }
        return user;
    }
}
