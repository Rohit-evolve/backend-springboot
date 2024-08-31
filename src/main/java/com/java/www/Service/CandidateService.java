package com.java.www.Service;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.www.Model.Candidate;
import com.java.www.Repo.CandidateRepo;

@Service
public class CandidateService 
{
	@Autowired
	CandidateRepo repo;
	
	public List<Candidate> getCandidate()
    {
		return repo.findAll(); 
	}
	
	public Candidate getCandidateByEmail(String email)
	{
		return repo.findByEmail(email);
	}
	
	public Candidate setCandidate(Candidate candidate)
	{
		return repo.save(candidate);
	}
	

	
	public Candidate updateCandidate(int id, Candidate can)
	{
		Candidate cand =  repo.findById(id).get();
		cand.setFirstName(can.getFirstName());
		cand.setLastName(can.getLastName());
		cand.setMobileNo(can.getMobileNo());
		cand.setEmail(can.getEmail());
		cand.setGraduation(can.getGraduation());
		cand.setPercentage(can.getPercentage());
		cand.setYop(can.getYop());
		cand.setSkills(can.getSkills());
		
		Candidate update = repo.save(cand);
		
		return update;
		
	}
	
	public String deleteCandidate(String email)
	{
		Candidate can = repo.findByEmail(email);
		
		repo.delete(can);
		 return "Record deleted";
		
	}
	
	public ResponseEntity<String> setCandidateFile(String email, MultipartFile file) throws IOException
	{
		Candidate can = repo.findByEmail(email);
		can.setFile(file.getBytes());
		
		repo.save(can);
		
		return ResponseEntity.ok("success");
	}
	
	public ResponseEntity<byte[]> getCandidateFile(int id) {
	    try {
	        Candidate candidate = repo.findById(id).orElseThrow();
	        byte[] file = candidate.getFile();

	        if (file == null) {
	            return ResponseEntity.notFound().build();
	        }

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        headers.setContentDispositionFormData("attachment", "resume.pdf");

	        return new ResponseEntity<>(file, headers, HttpStatus.OK);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

}
