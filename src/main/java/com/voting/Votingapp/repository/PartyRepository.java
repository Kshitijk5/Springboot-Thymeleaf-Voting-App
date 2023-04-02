package com.voting.Votingapp.repository;


import com.voting.Votingapp.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PartyRepository extends JpaRepository<Party,Long> {

    Optional<Party> findById(long id);
    Optional<Party> findByPartyName(String partName);
}
