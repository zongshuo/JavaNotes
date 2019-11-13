package ioBIO.streamChar.writer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.FileSystemException;

/**
 * Created by DEll on 2019-11-12.
 */
public class WriterFileTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @After
    public void clear(){
        File file = new File("./hehe.txt");
        System.gc();
        file.deleteOnExit();
    }

    @Test
    public void testGetWriter() throws Exception {
        WriterFile writerFile = new WriterFile();
        File file = new File("./hehe.txt");
        file.createNewFile();

        //TODO 此处设置为gbk，后续内容会乱码
        Writer writer = writerFile.getWriter(file, Charset.forName("utf8"));
        writer.write("字符输入流输入GBK格式的内容");
        writer.flush();
        writer.close();

        writer = writerFile.getWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        bufferedWriter.newLine();
        bufferedWriter.write("字符输入流输入的内容。");
        writer.flush();
        bufferedWriter.flush();
        writer.close();
        bufferedWriter.close();

    }

    @Test
    public void testGetWriter1() throws Exception {
        WriterFile writerFile = new WriterFile();
        File file = new File("./hehe.txt");
        Assert.assertTrue(file.createNewFile());
        file.setWritable(false);

        thrown.expect(FileSystemException.class);
        writerFile.getFileWriter(file, false);
        thrown.expect(FileNotFoundException.class);
        file = new File("./xixi.txt");
        writerFile.getFileWriter(file, false);
    }

    @Test
    public void testGetFileWriter() throws Exception {

    }
}