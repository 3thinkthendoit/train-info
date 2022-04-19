package com.think.adapter.dto.cmd;

import java.util.Date;

/**
 * @author hg
 * @date 2022-04-14日 15:42
 */
public class GetAllTrainInfoCmd {

    private Date date;

    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
