package com.electronics.store.fileservice;

import com.electronics.store.config.AppConstants;
import com.electronics.store.exceptions.BadApiResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {

        // file name

        String originalFilename = file.getOriginalFilename();
        log.info("Filename : {} ", originalFilename);


        //Generating random name for file
        String randomId = UUID.randomUUID().toString();//generates random id

        String extension = randomId.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));

        String fileNameWithExtension = randomId + extension;
        //abc.png name of image eg
        // Full path
        String fullpath = path  + fileNameWithExtension; // imagepath/filename

        log.info("Full image path: {}", fullpath);
        if (extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg")) {


            // create folder if not created
            log.info("file extension is {} ",extension);
            File f = new File(path);
            if (!f.exists()) {

                // create folder if not created
                f.mkdirs();

            }

            // file copy

            Files.copy(file.getInputStream(), Paths.get(fullpath));
            return fileNameWithExtension;
        } else {
            throw new BadApiResponseException(AppConstants.File_Extension_NotFound );
        }


    }


    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullpath = path + File.separator + name;

        InputStream inputStream = new FileInputStream(fullpath);
        return inputStream;

    }
}
