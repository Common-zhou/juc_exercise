package com.beiwu.docservice;

import com.beiwu.docservice.bussiness.BusinessTools;
import com.beiwu.docservice.utils.Constants;
import com.beiwu.docservice.utils.CreateQuestion;
import com.beiwu.docservice.vo.QuestionInDbVO;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 题库  模拟数据库中的
 *
 * @Author zhoubing
 * @Date 2021-07-06 17:02
 */
public class QuestionBank {
    private static ConcurrentHashMap<Integer, QuestionInDbVO> questionMap = new ConcurrentHashMap<>();

    public static void initBank() {
        for (int i = 0; i < Constants.BANK_SIZE; i++) {
            QuestionInDbVO vo = CreateQuestion.generateOne(i);
            questionMap.put(i, vo);
        }
    }

    public static QuestionInDbVO queryById(Integer id) {
        // 模拟20ms 的处理时间
        BusinessTools.businessMs(20);

        return questionMap.get(id);
    }

    public static String queryBySha(Integer id) {
        // 模拟20ms 的处理时间
        BusinessTools.businessMs(20);

        return questionMap.get(id).getSha();
    }

}
