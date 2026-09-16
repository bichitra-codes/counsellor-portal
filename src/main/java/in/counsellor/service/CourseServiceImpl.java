package in.counsellor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.counsellor.entity.Course;
import in.counsellor.repo.CourseRepo;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepo courseRepo;
	
	@Override
	public List<Course> getCourse() {
		return courseRepo.findAll();
	}

}
