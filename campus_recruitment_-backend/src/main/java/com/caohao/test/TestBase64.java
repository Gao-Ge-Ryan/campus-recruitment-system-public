package com.caohao.test;

import java.util.Base64;

public class TestBase64 {
    public static void main(String[] args) {
        byte[] decode = Base64.getDecoder().decode("eyJuaWNrbmFtZSI6IuWwj+abuSIsInN1YiI6IjE1MjE5MzA5MzhAcXEuY29tIiwiYXZhdGFyIjpudWxsLCJyb2xlIjpbeyJhdXRob3JpdHkiOiJVc2VyIn1dLCJleHAiOjE5OTkzMTI5MjgsImlhdCI6MTYzOTMxMjkyODUxOX0");
        System.out.println(new String(decode));
    }
}
