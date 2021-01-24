package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.FileExt;

public interface FileExtService {

	public FileExt add(FileExt fe);
	public int delete(FileExt fe);
	public List<FileExt> getStatic();
	public List<FileExt> getRest();
	public FileExt getFileExt(FileExt fe);
	public FileExt update(FileExt fe);
}
