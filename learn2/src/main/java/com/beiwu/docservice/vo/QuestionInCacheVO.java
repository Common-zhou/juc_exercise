package com.beiwu.docservice.vo;

/**
 * @Author zhoubing
 * @Date 2021-07-15 16:06
 */
public class QuestionInCacheVO {
    private final String questionDetail;
    private final String questionSha;

    public QuestionInCacheVO(String questionDetail, String questionSha) {
        this.questionDetail = questionDetail;
        this.questionSha = questionSha;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public String getQuestionSha() {
        return questionSha;
    }
}
