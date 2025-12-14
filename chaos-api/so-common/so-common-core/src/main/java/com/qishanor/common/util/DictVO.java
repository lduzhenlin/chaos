package com.qishanor.common.util;

import cn.hutool.http.webservice.SoapUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: 周振林
 * @date: 2022-04-17
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 *

 *
 **/

@Data
@Accessors(chain = true)
public class DictVO<PK> {
    private PK id;
    private String label;
    private String value;
    private List<DictVO> children=new ArrayList<>();

    public DictVO(){}
    public DictVO(String label,String value){
        this.label=label;
        this.value=value;
    }
    public DictVO(PK id,String label,String value){
        this.id=id;
        this.label=label;
        this.value=value;
    }
}
