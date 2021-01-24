package com.example.demo;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.FileExtServiceImpl;

@SpringBootTest
public class FileExtServiceTest {

	@Autowired
	FileExtServiceImpl fe;
	
	@Test
	void writeLog() throws IOException {
		fe.logging("titidks");
	}
}
