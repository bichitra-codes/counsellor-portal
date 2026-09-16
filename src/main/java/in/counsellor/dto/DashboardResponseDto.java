package in.counsellor.dto;

import lombok.Data;

@Data
public class DashboardResponseDto {

	 private Integer totalEnquiries;
	    private Integer newEnquiries;
	    private Integer enrolledEnquiries;
	    private Integer lostEnquiries;
}
