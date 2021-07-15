package com.beiwu.docservice.service.question;

import com.beiwu.docservice.bussiness.BusinessTools;
import com.beiwu.docservice.vo.QuestionInCacheVO;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ParallelQstService {
    private static ConcurrentHashMap<Integer, QuestionInCacheVO> questionCache = new ConcurrentHashMap<>();

    /**
     * 对题目进行处理，如解析文本，下载图片等等工作
     *
     * @param questionId 题目id
     * @return 题目解析后的文本
     */
    public static String makeQuestion(Integer questionId, String questionSrc) {
        Random r = new Random();
        BusinessTools.businessMs(450 + r.nextInt(100));
        return "CompleteQuestion[id=" + questionId
            + " content=:" + questionSrc + "]";
    }
}
