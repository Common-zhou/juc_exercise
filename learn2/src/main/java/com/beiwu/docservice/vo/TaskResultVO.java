package com.beiwu.docservice.vo;

import java.util.concurrent.Callable;

/**
 * @Author zhoubing
 * @Date 2021-07-15 16:08
 */
public class TaskResultVO {
    private final String questionDetail;
    private final Callable<QuestionInCacheVO> questionFuture;

    public TaskResultVO(String questionDetail) {
        this.questionDetail = questionDetail;
        this.questionFuture = null;
    }

    public TaskResultVO(Callable<QuestionInCacheVO> questionFuture) {
        this.questionFuture = questionFuture;
        this.questionDetail = null;
    }
}
