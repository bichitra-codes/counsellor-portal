package in.counsellor.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.counsellor.dto.DashboardResponseDto;
import in.counsellor.entity.Counsellor;
import in.counsellor.entity.Enquiry;
import in.counsellor.repo.CounsellorRepo;
import in.counsellor.repo.EnquiryRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService {

 
	
	@Autowired
	public CounsellorRepo counsellorRepo;
	
	@Autowired
	public EnquiryRepo enquiryRepo;

		
	@Override
	public Counsellor login(String email, String password) {
		
		Optional<Counsellor> counsellor = counsellorRepo.findByEmailAndPassword(email, password);
		
		if(counsellor.isPresent()) {
			return counsellor.get();
		}
		
		return null;
	}	
	@Override
	public boolean saveCounsellor(Counsellor counsellor) {
           Counsellor savedCounsellor = counsellorRepo.save(counsellor); 
		
		return savedCounsellor.getCounsellorId() != null;
	}
	
	@Override
	public DashboardResponseDto getDashboardInfo(Integer counsellorId) {
		
		List<Enquiry> enqList = enquiryRepo.findByCounsellorCounsellorId(counsellorId);
		
		int totalEnqsCnt = enqList.size();
		
		Map<String, Long> statusWiseCount = enqList.stream().collect(Collectors.groupingBy(Enquiry::getStatus, Collectors.counting()));
		
		 int newEnqsCnt = statusWiseCount.getOrDefault("NEW",0L).intValue();
		 int enrolledCnt = statusWiseCount.getOrDefault("ENROLLED",  0L).intValue();
		 int lostCnt = statusWiseCount.getOrDefault("LOST", 0L).intValue();
		 
		DashboardResponseDto dto = new DashboardResponseDto();
		
		dto.setTotalEnquiries(totalEnqsCnt);
		dto.setNewEnquiries(newEnqsCnt);
		dto.setEnrolledEnquiries(enrolledCnt); 
		dto.setLostEnquiries(lostCnt);
		 
		return dto;
	}
	 

}
