package com.beiwu.docservice.service.question;

import com.beiwu.docservice.QuestionBank;
import com.beiwu.docservice.vo.QuestionInCacheVO;
import com.beiwu.docservice.vo.QuestionInDbVO;
import com.beiwu.docservice.vo.TaskResultVO;

import java.util.concurrent.*;

public class ParallelQstService {
    private static ConcurrentHashMap<Integer, QuestionInCacheVO> questionCache = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Integer, Future<QuestionInCacheVO>> processingQestionCache = new ConcurrentHashMap<>();
    /*处理题目的线程池*/
    private static ExecutorService makeQuestionExector = Executors.newCachedThreadPool();

    /**
     * 对题目进行处理，如解析文本，下载图片等等工作
     *
     * @param questionId 题目id
     * @return 题目解析后的文本
     */
    public static TaskResultVO makeQuestion(Integer questionId) {
        QuestionInCacheVO cacheVO = questionCache.get(questionId);
        // 检查是否有缓存
        if (cacheVO == null) {
            // 没有缓存
            System.out.println(String.format("题目[%s]在缓存中不存在,准备查询", questionId));

            return new TaskResultVO(getQuestionFuture(questionId));
        } else {
            // 有缓存 检查和数据库的相同
            String shaFromDb = QuestionBank.queryBySha(questionId);
            if (shaFromDb.equals(cacheVO.getQuestionSha())) {
                // sha一致  直接返回
                System.out.println(String.format("题目[%s]在缓存中已存在,可以使用", questionId));
                return new TaskResultVO(cacheVO.getQuestionDetail());
            } else {
                // 不一致 需要重新获取
                System.out.println(String.format("题目[%s]在缓存中已过期,准备更新", questionId));
                return new TaskResultVO(getQuestionFuture(questionId));
            }
        }

    }

    private static Future<QuestionInCacheVO> getQuestionFuture(Integer questionId) {
        Future<QuestionInCacheVO> future = processingQestionCache.get(questionId);
        if (future != null) {
            return future;
        }
        Future<QuestionInCacheVO> questionFt = null;
        try {
            QuestionInDbVO questionInDbVO = QuestionBank.queryById(questionId);
            QuestionTask questionTask = new QuestionTask(questionInDbVO, questionId);

            FutureTask<QuestionInCacheVO> ft = new FutureTask<>(questionTask);

            // putIfAbsent 如果当前尚未有值与key相关联。则返回null，并将当前值和key相关联
            // 如果当前有值与key相关联  则返回值
            questionFt = processingQestionCache.putIfAbsent(questionId, ft);

            if (questionFt == null) {
                // 说明设置成功
                questionFt = ft;
                makeQuestionExector.submit(ft);
                System.out.println("提交任务成功.");
            } else {
                System.out.println("已有其他线程提交该任务，无需执行");
            }

            return questionFt;
        } catch (Exception e) {
            processingQestionCache.remove(questionId);
            e.printStackTrace();
            throw e;
        }
    }

    private static class QuestionTask implements Callable<QuestionInCacheVO> {
        private QuestionInDbVO questionInDbVO;
        private Integer questionId;

        public QuestionTask(QuestionInDbVO questionInDbVO, Integer questionId) {
            this.questionInDbVO = questionInDbVO;
            this.questionId = questionId;
        }

        @Override
        public QuestionInCacheVO call() throws Exception {

            try {
                // 这里的detail与之前的有所不同。比如图片下载好之后，放到了本地，则放上它的地址
                String questionDetail = QstService.makeQuestion(questionId, questionInDbVO.getDetail());

                QuestionInCacheVO cacheVO = new QuestionInCacheVO(questionDetail, questionInDbVO.getSha());
                questionCache.put(questionId, cacheVO);
                return cacheVO;
            } finally {
                // 这里无论成功 还是失败 都必须将其从正在处理的集合中去除
                processingQestionCache.remove(questionId);
            }
        }
    }
}
