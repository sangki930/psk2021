package com.example.demo.service;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.FileExt;
import com.example.demo.repo.FileExtRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileExtServiceImpl implements FileExtService{

	private final FileExtRepository feRepo;
	
	@Override
	public FileExt getFileExt(FileExt fe) {
		// TODO Auto-generated method stub
		fe=feRepo.getOne(fe.getId());
		return fe;
	}
	
	@Override
	public FileExt add(FileExt fe) {
		// TODO Auto-generated method stub
		int cnt=feRepo.findByType(0).size();
		
		//커스텀 확장자가 200개 넘으면 id=-1을 반환하여 추가 생성을 막음
		if(cnt>=200) 
			return new FileExt(-1L,"",0,false);
		
		fe=feRepo.save(fe);
		System.out.println(fe);
		return fe;
	}
	
	@Override
	public FileExt update(FileExt fe) {
		// TODO Auto-generated method stub
		FileExt tmp = feRepo.getOne(fe.getId());
		tmp=new FileExt(tmp.getId(),tmp.getExt(),tmp.getType(),fe.isChecked());
		feRepo.save(tmp);
		return tmp;
	}

	@Override
	public int delete(FileExt fe) {
		// TODO Auto-generated method stub
		feRepo.deleteById(fe.getId());
		return 0;
	}
	

	@Override
	//고정 확장자
	public List<FileExt> getStatic() {
		// TODO Auto-generated method stub
		if(feRepo.findByType(1).isEmpty()) {
			String exts[]= {"bat","cmd","com","cpl","exe","scr","js"};
			//고정 확장자
			
			for(String ext : exts) {
				FileExt fe = FileExt.builder()
								.ext(ext)
								.type(1)
								.checked(false)
								.build();
				feRepo.save(fe);
			}
			
		}
		
		return feRepo.findByType(1);
	}

	@Override
	//커스텀 확장자
	public List<FileExt> getRest() {
		// TODO Auto-generated method stub
		return feRepo.findByType(0);
	}
	
	
	public void logging(String s) throws IOException{
		
		String filename="./src/main/java/com/example/demo/service/logs.txt";
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename,true));
		bw.write(s);
		bw.flush();
		bw.close();
	}

	


}
