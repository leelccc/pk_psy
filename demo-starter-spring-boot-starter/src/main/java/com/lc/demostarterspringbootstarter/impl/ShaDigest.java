package com.lc.demostarterspringbootstarter.impl;

import com.lc.demostarterspringbootstarter.Digest;
import org.apache.commons.codec.digest.DigestUtils;

public class ShaDigest implements Digest{
    @Override
    public String digest(String str) {
        System.out.println("使用sha256");
        return DigestUtils.sha256Hex(str);
    }
}
