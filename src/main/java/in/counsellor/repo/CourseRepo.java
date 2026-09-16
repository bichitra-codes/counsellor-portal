package in.counsellor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.counsellor.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {

}
