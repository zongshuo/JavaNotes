package ioBIO.streamChar.reader;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.FileSystemException;

/**
 * Created by DEll on 2019-11-7.
 */
public class ReadFile {

    /**
     * 根据文件路径获取文件的字符输入流
     * 返回Reader类型的FileReader对象，将导致FileReader类独有的方法不可用
     * 如：getEncoding()
     * 路径不存在或路径不是标准文件会抛出异常
     * @param file
     * @return Reader
     * @throws FileNotFoundException
     * @throws FileSystemException
     */
    public Reader getReader(File file) throws FileNotFoundException, FileSystemException {
        return getFileReader(file);
    }

    /**
     * 方法以特定字符集获取字符输入流
     * 路径不存在或者不是标准文件会抛出异常
     * @param file
     * @param charset
     * @return Reader
     * @throws FileNotFoundException
     * @throws FileSystemException
     */
    public Reader getReader(File file, Charset charset) throws FileNotFoundException, FileSystemException {
        if(!file.isFile()) {
            throw new FileNotFoundException("file not found:"+file.toString());
        }
        if (file.canRead()){
            return new InputStreamReader(new FileInputStream(file), charset);
        }
        throw new FileSystemException("file can not read:"+file.toString());
    }

    /**
     * 根据文件对象返回一个文件字符输入流
     * 路径不存在或路径不是标准文件会抛出异常
     * @param file
     * @return FileReader
     * @throws FileNotFoundException
     * @throws FileSystemException
     */
    public FileReader getFileReader(File file) throws FileNotFoundException, FileSystemException {
        if(!file.isFile()) {
            throw new FileNotFoundException("file not found:"+file.toString());
        }
        System.out.print(file.canRead());
        if(file.canRead()) {
            return new FileReader(file);
        }
        throw new FileSystemException("file can not read:"+file.toString());
    }



    /**
     * 以缓存方式读取文件
     * 该方式读取时会产生中文乱码问题
     * @param reader
     * @return
     * @throws IOException
     */
    public String readFile(Reader reader) throws IOException {
        String result = "";
        BufferedReader bufReader = new BufferedReader(reader);
        int len = 0;
        char [] cBuf = new char[2048];
        while((len = bufReader.read(cBuf, 0, cBuf.length)) != -1){
            //合理设置字符数组的长度，减少创建String对象的次数。
            //如果没有读够2048字符，后面的空元素不转换为字符串。
            result += new String(cBuf, 0, len);
        }
        reader.close();
        bufReader.close();
        return result;
    }
}
