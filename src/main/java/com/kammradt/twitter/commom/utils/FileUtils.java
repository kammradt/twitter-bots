package com.kammradt.twitter.commom.utils;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.net.URL;

public class FileUtils {

    @SneakyThrows
    static public InputStream getInputStreamFromUrl(String url) {
        return new URL(url).openStream();
    }

}
