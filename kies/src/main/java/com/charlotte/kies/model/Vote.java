package com.charlotte.kies.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

}
