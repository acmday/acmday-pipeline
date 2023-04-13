package com.acmday.pipeline.demo.pipeline.request;

import java.io.Serializable;

/**
 * @Author acmday
 * @Date 2023/4/13 7:55 下午
 */
public class PipelineRequest<Req> implements Serializable {

    private static final long serialVersionUID = 6144185126084901774L;

    /**
     * 业务类型(groupName)
     **/
    private String bizCode;

    /**
     * 请求对象
     **/
    private Req request;

    public PipelineRequest() {
    }

    public PipelineRequest(String bizCode, Req request) {
        this.bizCode = bizCode;
        this.request = request;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public Req getRequest() {
        return request;
    }

    public void setRequest(Req request) {
        this.request = request;
    }
}

