package com.shoesclick.api.order.utils;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileUtils {

    private FileUtils(){}

    public static BufferedReader getBufferReader(String fileName) throws FileNotFoundException {
        FileReader produtoArq = new FileReader(ResourceUtils.getFile("classpath:"+fileName));
       return new BufferedReader(produtoArq);
    }
}
