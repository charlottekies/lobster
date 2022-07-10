package com.charlotte.kies.repository;

import com.charlotte.kies.model.User;
import com.charlotte.kies.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
