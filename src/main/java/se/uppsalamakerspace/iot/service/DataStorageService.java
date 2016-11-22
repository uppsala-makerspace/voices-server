package se.uppsalamakerspace.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by fredl2 on 2016-11-22.
 *
 * Service responsible for storing byte arrays in files.
 */

@Service
public class DataStorageService {

    private final Path basePath;

    @Autowired
    public DataStorageService(@Value("${files.path:/tmp/iot-server}") final String filePath) {
        try {
            this.basePath = Paths.get(filePath);
            Files.createDirectories(basePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create file storage directory",e);
        }
    }

    public void storeBytes(final String key, final byte[] bytes) {
        try {
            Files.write(basePath.resolve(key), bytes);
        } catch (IOException e) {
            throw new RuntimeException("Could not store bytes",e);
        }
    }

    public byte[] retrieveBytes(final String key) {
        try {
            return Files.readAllBytes(basePath.resolve(key));
        } catch (IOException e) {
            throw new RuntimeException("Could not retrieve bytes",e);
        }
    }
}
