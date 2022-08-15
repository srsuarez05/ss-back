package com.project.ssback.services.impl;

import com.project.ssback.services.IUploadFileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService {
    private final static String DIRECTORIO_UPLOAD = "C:\\ss-projects\\ss-back\\uploads";
    @Override
    public Resource cargar(String nombreFoto) throws MalformedURLException {
        Path routeFile = getPath(nombreFoto);
        //log.info(routeFile.toString());
        Resource resource = new UrlResource(routeFile.toUri());

        if (!resource.exists() && !resource.isReadable()) {
            routeFile = Paths.get("C:\\ss-projects\\ss-back\\uploads").resolve("user-default.jpeg").toAbsolutePath();
            resource = new UrlResource(routeFile.toUri());
            //log.error("Error, no se pudo cargar la imagen: "+ namePhoto);
        }
        return resource;
    }

    @Override
    public String copiar(MultipartFile fileImage) throws IOException {
        String nameFile = UUID.randomUUID().toString() +"_"+ fileImage.getOriginalFilename().replace(" ", "");
        Path routeFile = getPath(nameFile);

        Files.copy(fileImage.getInputStream(), routeFile);

        return nameFile;
    }

    @Override
    public boolean eliminarFoto(String nombreFoto) {
        if (nombreFoto != null && nombreFoto.length() > 0) {
            Path routePreviousFile = Paths.get("C:\\ss-projects\\ss-back\\uploads").resolve(nombreFoto).toAbsolutePath();
            File filePreviousImage = routePreviousFile.toFile();

            if (filePreviousImage.exists() && filePreviousImage.canRead()) {
                filePreviousImage.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public Path getPath(String nombreFoto) {
        return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
    }
}
