package com.insistla.ssm.pojo;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * page分页数据
 */
@Service("page")
@Data
public class Page implements Serializable {

    private Integer pageId;
    private String pageName;
    private String pageSex;
    private Integer pageAge;

}
