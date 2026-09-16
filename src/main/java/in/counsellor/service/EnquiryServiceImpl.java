package in.counsellor.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.counsellor.CounsellorPortalApplication;
import in.counsellor.dto.EnqFilterRequestDto;
import in.counsellor.dto.EnquiryDto;
import in.counsellor.entity.Counsellor;
import in.counsellor.entity.Course;
import in.counsellor.entity.Enquiry;
import in.counsellor.repo.CounsellorRepo;
import in.counsellor.repo.CourseRepo;
import in.counsellor.repo.EnquiryRepo;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    private final CounsellorPortalApplication counsellorPortalApplication;

	@Autowired
	public EnquiryRepo enquiryRepo;
	
	@Autowired 
	public CounsellorRepo counsellorRepo;
	
	@Autowired
	public CourseRepo courseRepo;

    EnquiryServiceImpl(CounsellorPortalApplication counsellorPortalApplication) {
        this.counsellorPortalApplication = counsellorPortalApplication;
    }
	
    @Override
    public boolean addEnquiry(EnquiryDto enqDto,  Integer counsellorId) {

        Enquiry enq = null;

        // UPDATE CASE
        if(enqDto.getEnqId() != null) {

            enq = enquiryRepo.findById(enqDto.getEnqId())
                             .orElse(new Enquiry());

        }

        // INSERT CASE
        else {

            enq = new Enquiry();

        }

        // copy dto to entity
        BeanUtils.copyProperties(enqDto, enq);

        Course course =
                courseRepo.findById(enqDto.getCourseId())
                          .orElseThrow();

        Counsellor counsellor =
                counsellorRepo.findById(counsellorId)
                              .orElseThrow();

        // association mapping
        enq.setCourse(course);

        enq.setCounsellor(counsellor);

        Enquiry savedEnq = enquiryRepo.save(enq);

        return savedEnq.getEnqId() != null;
    }
	@Override
	public List<Enquiry> getAllEnquiries(Integer counsellorId) {
		
		return enquiryRepo.findByCounsellorCounsellorId(counsellorId);
	}

	@Override
	public List<Enquiry> getEnquiriesByFilter(EnqFilterRequestDto reqDto, Integer counsellorId) {
		
		Enquiry enqEntity = new Enquiry();
		
		if(null != reqDto.getClassMode() && !reqDto.getClassMode().equals("")) {
			enqEntity.setClassMode(reqDto.getClassMode());
		}
		
		if(null != reqDto.getStatus() && !reqDto.getStatus().equals("")) {
			enqEntity.setStatus(reqDto.getStatus());
		}
		
		if(null != reqDto.getCourseId() && reqDto.getCourseId() > 0) {
			Course course = courseRepo.findById(reqDto.getCourseId()).orElseThrow();
			enqEntity.setCourse(course);
		}
		           
		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElseThrow();
		enqEntity.setCounsellor(counsellor);
		
		 return enquiryRepo.findAll(Example.of(enqEntity));
		
	}

	@Override
	public EnquiryDto editEnquiry(Integer enqId) {

		Enquiry entity = enquiryRepo.findById(enqId).orElseThrow();
		
		EnquiryDto dto = new EnquiryDto();
		BeanUtils.copyProperties(entity, dto);
		
		dto.setCourseId(entity.getCourse().getCourseId());
		
		 return dto;
		 	
	}

	@Override
	public boolean updateEnquiry(EnquiryDto enqDto) {

		Enquiry entity = enquiryRepo.findById(enqDto.getEnqId()).orElseThrow();
		
		entity.setClassMode(enqDto.getClassMode());
		entity.setStatus(enqDto.getStatus());
		entity.setName(enqDto.getName());
		entity.setPhoneNumber(enqDto.getPhoneNumber());
		
		Course course = courseRepo.findById(enqDto.getCourseId()).orElseThrow();
		entity.setCourse(course);
		enquiryRepo.save(entity);
		
		return true;
	}

}
