package com.letsgo;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author 荆康
 * @version 1.0
 * @date 2020/9/24 9:35 AM
 */
public class DraftTest {
    @Test
    public void ClassPathDemo() throws IOException {

        File file = new File("");
        String filePath = file.getCanonicalPath();

        System.out.println(filePath);

    }

    @Test
    public void aaa(String text){
        System.out.println(text);
    }

    @Test
    public void  bbb(){
        String sdf = "aaa";

        aaa(sdf);
    }
}
