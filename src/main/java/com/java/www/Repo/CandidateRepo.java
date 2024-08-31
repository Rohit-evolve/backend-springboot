package com.java.www.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.www.Model.Candidate;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Integer> 
{
   public Candidate findByEmail(String email);
}
