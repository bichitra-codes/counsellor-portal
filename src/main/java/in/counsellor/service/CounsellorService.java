package in.counsellor.service;


import in.counsellor.dto.DashboardResponseDto;
import in.counsellor.entity.Counsellor;

public interface CounsellorService {

	public Counsellor login(String email, String  password); 
	
	public boolean saveCounsellor(Counsellor counsellor);
	
	public DashboardResponseDto getDashboardInfo( Integer counsellorId);
	
	
}
