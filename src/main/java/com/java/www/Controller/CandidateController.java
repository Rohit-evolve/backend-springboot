package com.java.www.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.java.www.Model.Candidate;
import com.java.www.Service.CandidateService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class CandidateController 
{
	@Autowired
	CandidateService ser;
	
	@GetMapping("/candidate")
	public List<Candidate> getCandidiate()
	{
		return ser.getCandidate();
		
	}
	
	@PostMapping("/candidate")
	public Candidate setCandidate(@RequestBody Candidate can)
	{
		return ser.setCandidate(can);
	}
	
	@GetMapping("/candidate/{email}")
	public Candidate getCandidateByEmail(@PathVariable String email)
	{
		return ser.getCandidateByEmail(email);
	}
	
	
	/*@PutMapping("/candidate/{id}")
	public Candidate updateCandidate(@PathVariable int id,  @RequestBody Candidate can)
	{
		return ser.updateCandidate(id, can);
		
	}*/
	
	@DeleteMapping("/candidate/{email}")
	
	public String deleteCandidate(@PathVariable String email)
	{
		return ser.deleteCandidate(email);
	}
	
	@PutMapping("candidate/{email}")
    public ResponseEntity<String> setCandidateFile(@PathVariable String email,@RequestParam("file") MultipartFile file) {
        try {
             ser.setCandidateFile(email,file);
            return ResponseEntity.ok("File uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed.");
        }
    }
	
	
	@GetMapping("/candidate/{id}/file")
	public ResponseEntity<byte[]> getCandidateFile(@PathVariable int id) {
	    
		return ser.getCandidateFile(id);
	}


}
