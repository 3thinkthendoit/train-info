package com.think.infrastructure.http.proxy;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author hg
 * @date 2022-04-02日 17:19
 */
public class IProxy {

    private long expire;

    private Proxy proxy;

    private String proxyType;

    public IProxy(String hostname,int port,long expire,String proxyType) {
        this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname, port));
        this.expire = expire;
        this.proxyType = proxyType;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public String getProxyType() {
        return proxyType;
    }

    public void setProxyType(String proxyType) {
        this.proxyType = proxyType;
    }

}
