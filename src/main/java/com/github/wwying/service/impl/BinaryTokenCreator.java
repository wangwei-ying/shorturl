package com.github.wwying.service.impl;

import com.github.wwying.service.TokenCreator;

import java.util.concurrent.ThreadLocalRandom;

/**
 * description: 基础进制算法
 * @author yingww
 * @date 2019-11-27
 */
public class BinaryTokenCreator implements TokenCreator<Long> {

    /**
     * description: 首先要保证id 唯一
     * 52进制加一位随机数,不足6位前方补位随机数,6位的 52进制 最大可达百亿级.
     * @author yingww
     * @date 2019-11-27
     * @param id
     * @return
     */
    @Override
    public String creatToken(Long id) {
        int binary = 52;
        String format = "DEFGHNOLMRSPQIJK0123456ABCefghiTUVW789XYZabcdjklmnop";
        String randomseed = "qrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder();
        binary(id, binary, format, stringBuilder);
        stringBuilder.append(randomseed.charAt(ThreadLocalRandom.current().nextInt(9)));
        int minLength = 6;
        int length=stringBuilder.length();
        if (length < 6) {
            int add = 6-length;
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < add; i++) {
                sb2.append(randomseed.charAt(ThreadLocalRandom.current().nextInt(9)));
            }
            return sb2.append(stringBuilder).toString();
        }

        return stringBuilder.toString();
    }




    /**
     * description: 十进制转特殊进制
     *
     * @param value     需要转化的值
     * @param binary    进制 int 类型
     * @param format    进制对应位数的格式 format的length必须等于binary
     * @param returnStr 返回值
     * @return
     * @author yingww
     * @date 2019-11-25
     */
    private void binary(long value, int binary, String format, StringBuilder returnStr) {
        long divider = value / binary;
        if (divider == 0) {
            long remainder = value % binary;
            if (remainder > 0) {
                System.out.println(remainder);
                returnStr.append(format.charAt((int) remainder));
            }
            returnStr = returnStr.reverse();
            return;
        }
        long remainder = value % binary;
        returnStr.append(format.charAt((int) remainder));
        binary(divider, binary, format, returnStr);
    }
}
