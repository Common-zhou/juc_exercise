package com.beiwu.docservice.vo;

/**
 * @Author zhoubing
 * @Date 2021-07-06 17:07
 */
public class QuestionInDbVO {
    //题目id
    private final Integer id;
    //题目详情，平均长度700字节
    private final String detail;

    public QuestionInDbVO(Integer id, String detail) {
        this.id = id;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "QuestionInDbVO{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                '}';
    }
}
