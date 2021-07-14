package com.beiwu.docservice;

import com.beiwu.docservice.service.DocService;
import com.beiwu.docservice.utils.Constants;
import com.beiwu.docservice.utils.DocGenerate;
import com.beiwu.docservice.vo.DocVO;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author zhoubing
 * @date 2021-07-13 20:45
 */
public class RpcServiceV1 {

    private static final ExecutorService docMakeExecutorService =
        Executors.newFixedThreadPool(Constants.CORE_COUNT);

    private static final ExecutorService docUploadExecutorService =
        Executors.newFixedThreadPool(Constants.CORE_COUNT);

    private static final CompletionService<String> docMakeCompletionService =
        new ExecutorCompletionService<>(docMakeExecutorService);

    private static final CompletionService<String> docUploadCompletionService =
        new ExecutorCompletionService<>(docUploadExecutorService);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //首先初始化题库 题目数目
        QuestionBank.initBank();
        System.out.println("题库已经初始化完成");
        int docCount = 6;
        // 之后根据给定的数目 生成文档
        List<DocVO> list = DocGenerate.generate(docCount);

        long startTime = System.currentTimeMillis();

        for (DocVO docVO : list) {
            docMakeCompletionService.submit(new MakeDocCallable(docVO));
        }
        System.out.println("all doc make service has submitted.");

        for (int i = 0; i < docCount; i++) {
            Future<String> future = docMakeCompletionService.take();
            docUploadCompletionService.submit(new UploadTask(future.get()));
        }
        System.out.println("all doc upload service has submitted.");


        for (int i = 0; i < docCount; i++) {
            Future<String> future = docUploadCompletionService.take();
        }
        System.out.println(String.format("consume time: %s", (System.currentTimeMillis() - startTime)));

        docMakeExecutorService.shutdown();
        docUploadExecutorService.shutdown();
    }

    public static class MakeDocCallable implements Callable<String> {

        private DocVO pendingDocVo;

        public MakeDocCallable(DocVO pendingDocVo) {
            this.pendingDocVo = pendingDocVo;
        }

        @Override
        public String call() throws Exception {
            long start = System.currentTimeMillis();
            String result = DocService.makeDoc(pendingDocVo);
            System.out.println("文档" + result + "生成耗时："
                + (System.currentTimeMillis() - start) + "ms");
            return result;
        }
    }

    //上传文档的工作任务
    private static class UploadTask implements Callable<String> {

        private String fileName;

        public UploadTask(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public String call() throws Exception {
            long start = System.currentTimeMillis();
            String result = DocService.upLoadDoc(fileName);
            System.out.println("已上传至[" + result + "]耗时："
                + (System.currentTimeMillis() - start) + "ms");
            return result;
        }
    }
}
