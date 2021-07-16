package com.beiwu.docservice.vo;

import java.util.concurrent.Future;

/**
 * @Author zhoubing
 * @Date 2021-07-15 16:08
 */
public class TaskResultVO {
    private final String questionDetail;
    private final Future<QuestionInCacheVO> questionFuture;

    public TaskResultVO(String questionDetail) {
        this.questionDetail = questionDetail;
        this.questionFuture = null;
    }

    public TaskResultVO(Future<QuestionInCacheVO> questionFuture) {
        this.questionFuture = questionFuture;
        this.questionDetail = null;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public Future<QuestionInCacheVO> getQuestionFuture() {
        return questionFuture;
    }
}
