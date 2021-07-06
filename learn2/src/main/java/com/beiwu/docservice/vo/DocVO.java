package com.beiwu.docservice.vo;

import java.util.List;

/**
 * 文档的VO实体类  里面包含了文件名 已经所有的题目
 *
 * @Author zhoubing
 * @Date 2021-07-06 18:12
 */
public class DocVO {
    private String name;
    private List<Integer> questions;

    public DocVO() {
    }

    public DocVO(String name, List<Integer> questions) {
        this.name = name;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Integer> questions) {
        this.questions = questions;
    }
}
