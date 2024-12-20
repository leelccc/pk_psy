package com.lc.demostarterspringbootstarter.impl;

import com.lc.demostarterspringbootstarter.Digest;
import org.apache.commons.codec.digest.DigestUtils;

public class MD5Digest implements Digest{
    @Override
    public String digest(String str) {
        System.out.println("使用MD5");
        return DigestUtils.md5Hex(str);

    }
}
