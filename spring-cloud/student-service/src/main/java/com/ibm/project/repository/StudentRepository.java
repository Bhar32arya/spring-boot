package com.ibm.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ibm.project.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
}
