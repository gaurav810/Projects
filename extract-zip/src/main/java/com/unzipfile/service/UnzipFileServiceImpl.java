package com.unzipfile.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.stereotype.Service;

@Service
public class UnzipFileServiceImpl implements UnzipFileService {

	 private static final int BUFFER_SIZE = 4096;
	
	@Override
	public void extractFileFromZipFile(String zipFilePath, String destinationDirectory) throws IOException {
		
		File destDir = new File(destinationDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        while (zipEntry != null) {
            String filePath = destinationDirectory + File.separator + zipEntry.getName();
            if (!zipEntry.isDirectory()) {
                extractFile(zipInputStream, filePath);
            } else {
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipInputStream.closeEntry();
            zipEntry = zipInputStream.getNextEntry();
        }
        zipInputStream.close();
	}
	
	private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

}
