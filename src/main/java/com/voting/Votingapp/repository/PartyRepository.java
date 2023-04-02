package com.voting.Votingapp.repository;


import com.voting.Votingapp.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party,Long> {

}
