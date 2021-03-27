package main.utilites;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Standard File Operation
 * */
public class FileUtility {

    public static class WriterUtility{
        /**
         * In this case (without try-with-resources), You should close all Writers yourself
         */
        public static void writeTextIntoFile(String fileName, String text) throws IOException {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text);
            bufferedWriter.close();
            fileWriter.close();
        }

        /**
         * In this case (with try-with-resources), Java will close all Writers. Because,
         * @see FileWriter and
         * @see BufferedWriter implemented
         * @see Closeable interface
         */
        public static void writeTextInfoFileWithTryResources(String fileName, String text) throws IOException{
            try (
                    FileWriter fileWriter = new FileWriter(fileName);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
            ) {
                bufferedWriter.write(text);
                bufferedWriter.flush();
            }
        }

        public static void appendTextIntoFileWithTryResources(String fileName, String text) throws IOException{
            try (
                    FileWriter fileWriter = new FileWriter(fileName, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
            ) {
                bufferedWriter.write(text);
            }
        }

        public static void writeBytesIntoFile(String fileName, byte[] content) throws IOException{
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);

            fos.write(content);
            fos.flush();
            fos.close();

            // we can write this method with try-with-resources
        }
    }

    public static class ReaderUtility{

        public static String readTextFromFile(String fileName) throws IOException{

            try(
                    InputStream is = new FileInputStream(fileName);
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr)
            ) {
                String line;
                StringBuilder sb = new StringBuilder();
                while((line = reader.readLine()) != null){
                    sb.append(line);
                }

                return sb.toString();
            }

            // return something;
            // question : why I cannot write (return something) in here
        }

        public static byte[] readBytesFromFile(String fileName) throws IOException{
            File file = new File(fileName);
            byte[] content = new byte[(int) file.length()];

            try (FileInputStream fis = new FileInputStream(file)){
                int i = fis.read(content);
            }

            return content;
        }
    }

    public static void downloadFromUrl(String fromUrl, String toFile) throws IOException {
        URL url = new URL(fromUrl);
        URLConnection con = url.openConnection();
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);
        InputStream is = con.getInputStream();

        try(
                ReadableByteChannel rbc = Channels.newChannel(is);
                FileOutputStream fos = new FileOutputStream(toFile);
        ){
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }

}
