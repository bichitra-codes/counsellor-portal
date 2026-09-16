package in.counsellor.dto;

import lombok.Data;

@Data
public class EnquiryDto {
	
	private Integer enqId;
	private String name;
	private Long phoneNumber;
	private String classMode;
	private String Status;
	private Integer counsellorId;
	private Integer courseId;
	
}
