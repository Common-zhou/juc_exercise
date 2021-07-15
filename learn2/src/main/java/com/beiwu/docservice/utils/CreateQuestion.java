package com.beiwu.docservice.utils;

import com.beiwu.docservice.vo.QuestionInDbVO;

import java.util.Random;

import static com.beiwu.docservice.utils.Constants.randomSeed;

/**
 * @Author zhoubing
 * @Date 2021-07-06 17:09
 */
public class CreateQuestion {
    private static Random docRandomLength = new Random();

    public static QuestionInDbVO generateOne(Integer id) {
        int length = docRandomLength.nextInt(Constants.QUESTION_LENGTH) + 30;

        String detail = generateDetail(length);

        String sha256 = EncryptUtils.encryptBySHA256(detail);

        return new QuestionInDbVO(id, detail, sha256);
    }

    public static String generateDetail(int length) {
        StringBuilder sb = new StringBuilder();

        Random r = new Random();
        int len = randomSeed.length();
        for (int i = 0; i < length; i++) {
            sb.append(randomSeed.charAt(r.nextInt(len)));
        }
        return sb.toString();

    }
}
