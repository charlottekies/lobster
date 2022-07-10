package com.charlotte.kies.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "user_vote")
@Table(name = "user_vote")
public class Vote {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_user_vote_id")
            }
    )
    @Column(name = "user_vote_id", nullable = false)
    private Long userVoteId;

    @Column(name = "vote_date", nullable = false)
    private Date voteDate;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "vote", nullable = false)
    private Float vote;

    public Vote(Long userVoteId, Date voteDate, Long userId, Float vote) {
        this.userVoteId = userVoteId;
        this.voteDate = voteDate;
        this.userId = userId;
        this.vote = vote;
    }

    public Vote() {
    }

    public Long getUserVoteId() {
        return userVoteId;
    }

    public void setUserVoteId(Long userVoteId) {
        this.userVoteId = userVoteId;
    }

    public Date getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(Date voteDate) {
        this.voteDate = voteDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Float getVote() {
        return vote;
    }

    public void setVote(Float vote) {
        this.vote = vote;
    }
}
