package com.ita.provapp.common.json;

import java.util.Date;

public class Order {
    Date createTime;

    public Order(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
