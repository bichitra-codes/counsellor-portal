package in.counsellor.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.counsellor.entity.Counsellor;

public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {
	
	Optional<Counsellor> findByEmailAndPassword(String email, String password);

}
