package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.FileExt;

@Repository
public interface FileExtRepository extends JpaRepository<FileExt, Long>{

	//타입별로 찾기
	public List<FileExt> findByType(int type);
	
}
