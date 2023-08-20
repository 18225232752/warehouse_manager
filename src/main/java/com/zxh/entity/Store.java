package com.zxh.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:08
*/

/**
    * 仓库表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store implements Serializable {
    private Integer storeId;

    private String storeName;

    private String storeNum;

    private String storeAddress;

    private String concat;

    private String phone;

    private static final long serialVersionUID = 1L;
}