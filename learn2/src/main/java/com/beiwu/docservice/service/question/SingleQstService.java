package com.beiwu.docservice.service.question;

import com.beiwu.docservice.QuestionBank;

/**
 *类说明：模拟解析题目文本，下载图片等操作，返回解析后的文本
 */
public class SingleQstService {

    /**
     * 普通对题目进行处理
     * @param questionId 题目id
     * @return 题目解析后的文本
     */
    public static String makeQuestion(Integer questionId){
        return QstService.makeQuestion(questionId,
                QuestionBank.queryById(questionId).getDetail());
    }

}
