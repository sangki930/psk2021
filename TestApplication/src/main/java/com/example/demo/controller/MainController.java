package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.FileExt;
import com.example.demo.service.FileExtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MainController {

	private final FileExtService fes;
	
	@GetMapping("/valueTest")
	public String valueTest(){ 
		String value = "테스트 String"; 
		return value; 
	}
	
	//고정 확장자 얻기
	@GetMapping("/getStatic")
	public List<FileExt> getStatic(){
		return fes.getStatic();
	}
	
	//커스텀 확장자 얻기
	@GetMapping("/getRest")
	public List<FileExt> getRest(){
		return fes.getRest();
	}

	@PostMapping("/add")
	public FileExt add(FileExt fe) {
		
		fe=fes.add(fe);
		return fe;
	}
	
	@PutMapping("/update")
	public FileExt update(FileExt fe) {
		System.out.println(fe);
		return fes.update(fe);
	}
	
	@DeleteMapping("/delete")
	public int delete(FileExt fe) {
		fes.delete(fe);
		return 1;
	}
	
}
