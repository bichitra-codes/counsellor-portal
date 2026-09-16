package in.counsellor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.counsellor.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {

	
	// Select * from enquiry where counsellor_id = ? ;
	public List<Enquiry> findByCounsellorCounsellorId(Integer counsellorId);
}
