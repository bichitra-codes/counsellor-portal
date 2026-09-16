package in.counsellor.service;

import java.util.List;

import in.counsellor.dto.EnqFilterRequestDto;
import in.counsellor.dto.EnquiryDto;
import in.counsellor.entity.Enquiry;

public interface EnquiryService {
	
	public boolean addEnquiry(EnquiryDto enq, Integer counsellorId);
	
	public List<Enquiry> getAllEnquiries(Integer counsellorId);
	public List<Enquiry> getEnquiriesByFilter(EnqFilterRequestDto reqDto, Integer counsellorId);
	public EnquiryDto editEnquiry(Integer enqId);
	public boolean updateEnquiry(EnquiryDto enquiry);
}
