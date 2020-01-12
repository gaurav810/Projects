package com.unzipfile.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unzipfile.service.UnzipFileService;

@RestController
@RequestMapping("unzip")
public class UnzipFileController {

	@Autowired
	private UnzipFileService unzipFileService;
	
	@RequestMapping(value ="/extract-zip", method = RequestMethod.GET)
	public String extractFileFromZipFileController() {
		
		String zipFilePath = "F:/downloaded example/face_recognition_source.zip";
		String destinationDirectory = "F:/Temp/output_dir";
		
		try {
			unzipFileService.extractFileFromZipFile(zipFilePath, destinationDirectory);
		} catch (IOException e) {
			e.printStackTrace();
			return "FAILED";
		}
		return "SUCCESS";
	}
	
}
