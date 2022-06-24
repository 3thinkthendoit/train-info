package com.think.infrastructure.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hg
 * @date 2022-04-01日 17:25
 */
public class IResponse {

    private Map<String,Object> headers = new HashMap<String,Object>();

    private Object data;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
