package com.acmday.pipeline.demo.pipeline.response;


import com.acmday.pipeline.demo.enums.IBaseEnum;

import java.io.Serializable;

/**
 * @Author acmday
 * @Date 2023/4/13 7:55 下午
 */
public class PipelineResponse <Resp> implements Serializable {
    private static final long serialVersionUID = 826105894906736192L;
    private Resp data;
    private Integer code;
    private String msg;

    public PipelineResponse() {
    }

    public PipelineResponse(Resp data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public PipelineResponse(IBaseEnum baseEnum) {
        if (baseEnum == null) {
            return;
        }
        this.code = baseEnum.getCode();
        this.msg = baseEnum.getValue();
    }

    public PipelineResponse(Resp data) {
        this.data = data;
    }

    public PipelineResponse(Resp data, IBaseEnum baseEnum) {
        this.data = data;
        if (baseEnum == null) {
            return;
        }
        this.code = baseEnum.getCode();
        this.msg = baseEnum.getValue();
    }

    public static <T> PipelineResponse<T> success(T data) {
        return new PipelineResponse<T>(data, ResponseEnum.SUCCESS);
    }

    public static <T> PipelineResponse<T> fail(IBaseEnum baseEnum) {
        return new PipelineResponse<T>(baseEnum);
    }

    public static <T> PipelineResponse<T> fail(int code, String msg) {
        return new PipelineResponse<T>(null, code, msg);
    }

    public Resp getData() {
        return data;
    }

    public void setData(Resp data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

