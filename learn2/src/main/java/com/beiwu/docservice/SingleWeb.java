package com.beiwu.docservice;

import com.beiwu.docservice.service.DocService;
import com.beiwu.docservice.utils.DocGenerate;
import com.beiwu.docservice.vo.DocVO;
import java.util.List;

/**
 * @Author zhoubing
 * @Date 2021-07-06 16:53
 */
public class SingleWeb {

    public static void main(String[] args) {
        //首先初始化题库 题目数目
        QuestionBank.initBank();
        System.out.println("题库已经初始化完成");

        // 之后根据给定的数目 生成文档
        List<DocVO> list = DocGenerate.generate(3);

        long startTotal = System.currentTimeMillis();

        // 循环遍历  之后处理
        for (DocVO docVO : list) {
            System.out.println(
                String.format("[%s]文档开始处理.共有题目: [%d]道.", docVO.getName(), docVO.getQuestions().size()));
            long startTime = System.currentTimeMillis();
            String localName = DocService.makeDoc(docVO);
            System.out.println(String.format("文档 %s 生成耗时, %s ms", localName, (System.currentTimeMillis() - startTime)));
            startTime = System.currentTimeMillis();
            String remoteUrl = DocService.upLoadDoc(docVO.getName());
            System.out.println(
                String.format("文档已上传至:%s 上传耗时, %s ms", remoteUrl, (System.currentTimeMillis() - startTime)));
        }

        System.out.println("共耗时："+(System.currentTimeMillis()-startTotal)+"ms");
    }

}
