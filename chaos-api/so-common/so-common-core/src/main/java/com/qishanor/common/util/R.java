package com.qishanor.common.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 */
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @JsonPropertyDescription(value = "调用成功返回0，调用失败返回1")
    private int code;

    @Getter
    @Setter
    @JsonPropertyDescription(value = "返回具体的信息")
    private String msg;

    @Getter
    @Setter
    private Object data;

    public static  R ok() {
        return restResult(null, 0, null);
    }

    public static  R ok(Object data) {
        return restResult(data, 0, null);
    }

    public static  R ok(Object data, String msg) {
        return restResult(data, 0, msg);
    }


    public static  R failed() {return restResult(null, 1, null);}

    public static  R failed(String msg) {
        return restResult(null, 1, msg);
    }

    public static  R failed(Object data) {
        return restResult(data, 1, null);
    }

    public static  R failed(Object data, String msg) {
        return restResult(data, 1, msg);
    }

    public static  R failed(Integer code,String msg) {
        return restResult(null, code, msg);
    }

    static  R restResult(Object data, int code, String msg) {
        R apiResult = new R();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public boolean isOk() {
        return this.code == 0;
    }

}
