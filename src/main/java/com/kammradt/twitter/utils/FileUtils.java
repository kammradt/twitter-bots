package com.kammradt.twitter.utils;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.net.URL;

public class FileUtils {

    @SneakyThrows
    static public InputStream getInputStreamFromUrl(String url) {
        return new URL(url).openStream();
    }

}
