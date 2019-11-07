package io.streamChar.reader;

import java.io.*;

/**
 * Created by DEll on 2019-11-7.
 */
public class ReadFile {

    /**
     * 根据文件路径获取文件的字符输入流
     * 返回Reader类型的FileReader对象，将导致FileReader类独有的方法不可用
     * 如：getEncoding()
     * 路径不存在或路径不是文件会抛出异常
     * @param file
     * @return Reader
     * @throws FileNotFoundException
     */
    public Reader getReader(File file) throws FileNotFoundException {
        return getFileReader(file);
    }

    /**
     * 根据文件对象返回一个文件字符输入流
     * 路径不存在或路径不是文件会抛出异常
     * @param file
     * @return FileReader
     * @throws FileNotFoundException
     */
    public FileReader getFileReader(File file) throws FileNotFoundException {
        if(file.isFile())
            return new FileReader(file);
        throw new FileNotFoundException("file not found:"+file.toString());
    }
}
