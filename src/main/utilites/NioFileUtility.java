package main.utilites;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * NIO File Operations
 */
public class NioFileUtility {

    public static void writeBytesInfoFile(String fileName, byte[] content) throws IOException {
        Path path = Paths.get(fileName);
        Files.write(path, content);
    }

    public static byte[] readBytesFromFile(String fileName) throws IOException{
        Path filePath = Paths.get(fileName);
        return Files.readAllBytes(filePath);
    }
}
