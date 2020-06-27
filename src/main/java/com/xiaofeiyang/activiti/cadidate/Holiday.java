package com.xiaofeiyang.activiti.cadidate;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2020-06-08 17:32
 */
@Data
public class Holiday implements Serializable {
    private static final long  serialVersionUID = -1L;
    private Integer id;
    private String holidayName;
    private Date begin;
    private Date end;
    private String reason;
    private String type;
    private Float num;
}
