package com.thuongmaidientu.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {
	private final Path rootLocationPath;

	public FileSystemStorageService() {
		// TODO Auto-generated constructor stub
		this.rootLocationPath = Paths.get("src/main/resources/static/uploads");
	}

	@Override
	public void store(MultipartFile file) {
		try {
			Path destinationFilePath = this.rootLocationPath.resolve(Paths.get(file.getOriginalFilename())).normalize()
					.toAbsolutePath();
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		try {
			Files.createDirectories(rootLocationPath);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
