package com.beiwu.docservice.utils;

import com.beiwu.docservice.vo.DocVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author zhoubing
 * @Date 2021-07-06 18:09
 */
public class DocGenerate {
    public static List<DocVO> generate(int length) {
        List<DocVO> doces = new ArrayList<>(length);

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            // 题目长度 60 - 90
            int questionLength = random.nextInt(30) + 60;
            List<Integer> questions = new ArrayList<>();

            for (int j = 0; j < questionLength; j++) {
                questions.add(random.nextInt(Constants.BANK_SIZE));
            }

            DocVO docVO = new DocVO("pending_" + i, questions);
            doces.add(docVO);

        }
        return doces;
    }
}
