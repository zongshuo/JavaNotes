package ioBIO.streamChar.writer;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.FileSystemException;

/**
 * Created by DEll on 2019-11-12.
 */
public class WriterFile {

    /**
     * 获取字符输出流
     * 如果文件不存在或者文件非标准文件返回异常
     * @param file
     * @return Writer
     * @throws FileNotFoundException
     * @throws FileSystemException
     */
    public Writer getWriter(File file, boolean append) throws FileNotFoundException, FileSystemException {
        return getFileWriter(file, append);
    }

    /**
     * 以特定字符集获取字符输出流
     * 如果文件不存在或者非标准文件返回异常
     * @param file
     * @param charset
     * @return Writer
     * @throws FileNotFoundException
     * @throws FileSystemException
     */
    public Writer getWriter(File file, Charset charset) throws FileNotFoundException, FileSystemException {
        if (!file.isFile()){
            throw new FileNotFoundException("file not found:"+file.toString());
        }
        if (file.canWrite()){
            return new OutputStreamWriter(new FileOutputStream(file), charset);
        }
        throw new FileSystemException("file can not write:"+file.toString());
    }

    /**
     * 获取字符输出流
     * 如果文件时路径或者非标准文件返回异常
     * 文件不可读返回异常
     * @param file 底层文件访问类
     * @param append 是否追加
     * @return FileWriter
     * @throws FileNotFoundException
     * @throws FileSystemException
     */
    public FileWriter getFileWriter(File file, boolean append) throws FileNotFoundException, FileSystemException {
        if (!file.isFile()){
            throw new FileNotFoundException("file not found:"+file.toString());
        }
        if(file.canWrite()){
            try {
                return new FileWriter(file, append);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new FileSystemException("file can not write:"+file.toString());
    }

    /**
     * 将字符串写入文件
     * @param file
     * @param content
     */
    public void writeFile(File file, String content){
        Writer writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            writer = getWriter(file, true);
            bufferedWriter = new BufferedWriter(writer, content.length());
            bufferedWriter.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (FileSystemException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
