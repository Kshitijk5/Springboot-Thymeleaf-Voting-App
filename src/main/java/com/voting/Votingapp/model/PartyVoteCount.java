package com.voting.Votingapp.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PartyVoteCount {

    private Party party;
    private long voteCount;



}
