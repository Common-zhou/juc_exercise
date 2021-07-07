package com.beiwu.docservice.service;

import com.beiwu.docservice.bussiness.BusinessTools;
import com.beiwu.docservice.service.question.SingleQstService;
import com.beiwu.docservice.vo.DocVO;
import java.util.Random;

/**
 * @author zhoubing
 * @date 2021-07-07 23:56
 */
public class DocService {

    /**
     * 上传文档到网络
     *
     * @param docFileName 实际文档在本地的存储位置
     * @return 上传后的网络存储地址
     */
    public static String upLoadDoc(String docFileName) {
        Random r = new Random();
        BusinessTools.businessMs(9000 + r.nextInt(400));
        return "http://www.xxxx.com/file/upload/" + docFileName;
    }


    public static String makeDoc(DocVO srcDocVO) {
        System.out.println(String.format("开始处理文档:[%s]", srcDocVO.getName()));

        StringBuilder sb = new StringBuilder();

        for (Integer question : srcDocVO.getQuestions()) {
            sb.append(SingleQstService.makeQuestion(question));
        }

        return String.format("complete_%s_%s.pdf", System.currentTimeMillis(), srcDocVO.getName());
    }


}
