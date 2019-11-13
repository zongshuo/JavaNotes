package io.streamChar.reader;

import io.streamChar.writer.WriterFile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.Writer;


/**
 * Created by DEll on 2019-11-7.
 */
public class ReadFileTest {
    //设置异常的断言
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @After
    public void clear(){
        File file = new File("./test.txt");
        System.gc();
        file.deleteOnExit();
    }

    @org.junit.Test
    public void testGetReader() throws Exception {

    }

    @org.junit.Test
    public void testGetFileReader() throws Exception {
        ReadFile readFile = new ReadFile();
        File file = new File("./test.txt");
        Assert.assertTrue(file.createNewFile());
        file.setReadable(false);
        //windows下设置文件不可读。
        //Runtime.getRuntime().exec("cmd /c echo y|cacls " + file.getAbsolutePath() + " /t /p everyone:n");
        Assert.assertNotNull(readFile.getFileReader(file));

        file = new File("D:\\redis\\red");
        thrown.expect(FileNotFoundException.class);
        //TODO 断言消息未起作用
//        thrown.expectMessage("file not found:D:\\redis\\re");
        readFile.getFileReader(file);
    }

    @Test
    public void testGetReader1() throws Exception {
        ReadFile readFile = new ReadFile();
        File file = new File("./test.txt");
        String source = "hehe呵呵";
        file.createNewFile();
        WriterFile writerFile = new WriterFile();
        Writer writer = writerFile.getWriter(file, false);
        writer.write(source);
        writer.flush();
        writer.close();

        Reader reader = readFile.getReader(file);
        char [] cBuf = new char[9];
        reader.read(cBuf);
        String result = new String(cBuf);
        Assert.assertEquals(result.trim(), source);
    }
}