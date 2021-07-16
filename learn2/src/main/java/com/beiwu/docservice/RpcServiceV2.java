package com.beiwu.docservice;

import com.beiwu.docservice.service.DocService;
import com.beiwu.docservice.utils.Constants;
import com.beiwu.docservice.utils.DocGenerate;
import com.beiwu.docservice.vo.DocVO;

import java.util.List;
import java.util.concurrent.*;

/**
 * @Author zhoubing
 * @Date 2021-07-15 15:00
 */
public class RpcServiceV2 {
    private static ExecutorService makeDocService = Executors.newFixedThreadPool(Constants.CORE_COUNT * 2);
    private static ExecutorService uploadDocService = Executors.newFixedThreadPool(Constants.CORE_COUNT * 2);

    private static CompletionService<String> docMakeCompletionService = new ExecutorCompletionService<>(makeDocService);
    private static CompletionService<String> docUploadCompletionService = new ExecutorCompletionService<>(uploadDocService);

    public static void main(String[] args) {
        QuestionBank.initBank();
        System.out.println("题库初始化完成");

        int docLength = 60;
        List<DocVO> docList = DocGenerate.generate(docLength);

        long startTime = System.currentTimeMillis();

        for (DocVO docVO : docList) {
            docMakeCompletionService.submit(new DocMakeCallable(docVO));
        }
        System.out.println("all docVo has been submitted!");

        for (int i = 0; i < docLength; i++) {
            Future<String> future = null;
            try {
                future = docMakeCompletionService.take();
                docUploadCompletionService.submit(new DocUploadCallable(future.get()));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < docLength; i++) {
            try {
                Future<String> future = docUploadCompletionService.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(String.format("total consume time: [%.2f]s", (System.currentTimeMillis() - startTime) / 1000.0));
        makeDocService.shutdown();
        uploadDocService.shutdown();

    }

    public static class DocMakeCallable implements Callable<String> {
        private DocVO pengingDoc;

        public DocMakeCallable(DocVO pengingDoc) {
            this.pengingDoc = pengingDoc;
        }

        @Override
        public String call() throws Exception {
            long startTime = System.currentTimeMillis();
            String doc = DocService.makeDocAsyn(pengingDoc);
            System.out.println(String.format("doc:[%s] has been made,consume time: %.2f s", doc, (System.currentTimeMillis() - startTime) / 1000.0));
            return doc;
        }
    }

    public static class DocUploadCallable implements Callable<String> {
        private String fileName;

        public DocUploadCallable(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public String call() throws Exception {
            long startTime = System.currentTimeMillis();
            String url = DocService.upLoadDoc(fileName);
            System.out.println(String.format("doc:[%s] has been upload,consume time: %.2f s", url, (System.currentTimeMillis() - startTime) / 1000.0));
            return url;
        }
    }
}
