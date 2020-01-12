package com.unzipfile.service;

import java.io.IOException;

public interface UnzipFileService {

	public void extractFileFromZipFile(String zipFilePath, String destinationDirectory) throws IOException;
}
