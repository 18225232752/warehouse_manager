package com.zxh.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:09
*/

/**
    * 供货商
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supply implements Serializable {
    private Integer supplyId;

    private String supplyNum;

    private String supplyName;

    private String supplyIntroduce;

    private String concat;

    private String phone;

    private String address;

    /**
    * 0:可用  1:不可用
    */
    private String isDelete;

    private static final long serialVersionUID = 1L;
}