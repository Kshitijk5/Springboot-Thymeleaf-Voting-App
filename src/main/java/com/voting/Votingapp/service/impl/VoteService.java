package com.voting.Votingapp.service.impl;

import com.voting.Votingapp.model.Party;
import com.voting.Votingapp.model.PartyVoteCount;
import com.voting.Votingapp.repository.PartyRepository;
import com.voting.Votingapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.voting.Votingapp.model.User;

@Service
public class VoteService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PartyRepository partyRepository;

    public Boolean doVote(String username,String shortName){

          User user = userRepository.findByUsername(username).get();

          if(!user.getVoted()){
              Party party = partyRepository.findByPartyShortName(shortName).get();
              user.setVoted(true);
              user.setParty(party);
              userRepository.save(user);

              return true;
          }


        return false;
    }

    public List<PartyVoteCount> getVoteCountsForAllParties() {
        List<Party> parties = partyRepository.findAll();
        List<PartyVoteCount> partyVoteCounts = new ArrayList<>();
        for (Party party : parties) {
            long voteCount = userRepository.countByPartyId(party.getId());
            partyVoteCounts.add(new PartyVoteCount(party, voteCount));
            System.out.println(partyVoteCounts);
        }

        Comparator<PartyVoteCount> sortByVoteCount = (v1,v2)->{
            if(v1.getVoteCount()- v2.getVoteCount()>0)
                return -1;
             return 1;
        };

        Collections.sort(partyVoteCounts,sortByVoteCount);

        return partyVoteCounts;
    }
}
