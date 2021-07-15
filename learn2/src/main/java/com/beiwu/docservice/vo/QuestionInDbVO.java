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
    private final String sha;

    public QuestionInDbVO(Integer id, String detail, String sha) {
        this.id = id;
        this.detail = detail;
        this.sha = sha;
    }

    public Integer getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public String getSha() {
        return sha;
    }

    @Override
    public String toString() {
        return "QuestionInDbVO{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                ", sha='" + sha + '\'' +
                '}';
    }
}
