package io.streamChar.reader;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by DEll on 2019-11-7.
 */
public class ReadFileTest {
    //设置异常的断言
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @org.junit.Test
    public void testGetReader() throws Exception {

    }

    @org.junit.Test
    public void testGetFileReader() throws Exception {
        ReadFile readFile = new ReadFile();
        File file = new File("./test.txt");
        file.createNewFile();
        Assert.assertNotNull(readFile.getFileReader(file));
        //当文件被内存引用时，无法删除。
        System.gc();
        file.deleteOnExit();

        file = new File("D:\\redis\\red");
        thrown.expect(FileNotFoundException.class);
        //TODO 断言消息未起作用
//        thrown.expectMessage("file not found:D:\\redis\\re");
        readFile.getFileReader(file);
    }
}