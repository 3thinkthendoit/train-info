package com.think.infrastructure.http;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.think.infrastructure.http.proxy.IProxy;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import java.net.Proxy;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author hg
 *
 * @date 2022-04-01日 16:53
 */
@Component
public class IHttpClient implements InitializingBean, DisposableBean {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public  final MediaType MEDIA_TYPE_HTML = MediaType.parse("text/html; charset=utf-8");

    private boolean IS_PROXY = true;

    private boolean IS_RUNNING = true;

    private final Long EXPIRE_TIME = 1000*60*30L;

    private  OkHttpClient client = new OkHttpClient();

    private Map<String, IProxy> PROXY_MAP = Maps.newConcurrentMap();

    private ExecutorService threadPool;

    private final String CHECK_URL = "https://www.baidu.com";

    public IHttpClient(){

    }

    public IHttpClient(Boolean isProxy){
        this.IS_PROXY = isProxy;
        init();
    }

    private void init(){
        if(IS_PROXY) {
            threadPool = new ThreadPoolExecutor(5, 20, 20L,
                    TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
            manageProxyInfo();
        }
    }

    /**
     * get a IProxy
     * @return
     */
    private IProxy getIProxy(){
        Random r = new Random();
        int index = r.nextInt(PROXY_MAP.size());
        if(PROXY_MAP.size() == 0){
            return null;
        }
        String key = PROXY_MAP.keySet().toArray()[index].toString();
        return PROXY_MAP.get(key);
    }

    @Override
    public void destroy() throws Exception {
        if(threadPool !=null){
            threadPool.shutdown();
            this.IS_RUNNING = false;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    /**
     * manage the proxy info
     */
    private void manageProxyInfo(){
        String taskName = "SYC-PROXY-TASK";
        Thread thread = new Thread(taskName){
            @Override
            public void run() {
                while (IS_RUNNING){
                    try {
                        Map<String,IProxy> map = getProxyInfo(getHttpClient(null));
                        mergeTempProxyInfo(map);
                        checkProxyInfo(map);
                        updateProxyInfo(map);
                        if(!IS_RUNNING){
                            logger.info("{} has stoped...",taskName);
                            break;
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage(),e);
                    }finally {
                        try {
                            Thread.sleep(30000L);
                        } catch (InterruptedException e) {
                            logger.error(e.getMessage(),e);
                        }
                    }

                }
            }
        };
        thread.start();
        logger.info("{} is running .......",taskName);
    }

    /**
     * merge map to PROXY_MAP
     * @param map
     */
    private void mergeTempProxyInfo(Map<String,IProxy> map){
        PROXY_MAP.forEach((key,value)->{
            map.put(key,PROXY_MAP.get(key));
        });
    }

    /**
     * update PROXY_MAP
     * @param map
     */
    private void updateProxyInfo(Map<String,IProxy> map){
        StringBuffer  pInfo = new StringBuffer();
        PROXY_MAP.clear();
        map.forEach((key,value)->{
            PROXY_MAP.put(key,map.get(key));
        });
        logger.info("ALIVE NODE LIST:{}",PROXY_MAP.keySet());
    }

    /**
     * get proxy info
     * @param okHttpClient
     * @return
     */
    private Map<String,IProxy> getProxyInfo(OkHttpClient okHttpClient){
        Map<String,IProxy> map = Maps.newHashMap();
        try {
            String url = "http://proxylist.fatezero.org/proxy.list";
            IResponse iResponse = get(okHttpClient,url);
            String proStr = iResponse.getData().toString();
            String [] proxyArr = proStr.split("\\{");
            for (int i = 0; i < proxyArr.length; i++) {
                if(!Strings.isNullOrEmpty(proxyArr[i])){
                    JSONObject json = JSONObject.parseObject("{"+proxyArr[i]);
                    String country = json.getString("country");
                    if("CN".equalsIgnoreCase(country)){
                        String host = json.getString("host");
                        Integer port = json.getInteger("port");
                        String proxyType = json.getString("type");
                        map.put(host+":"+port,new IProxy(host,port,System.currentTimeMillis()+EXPIRE_TIME,proxyType));
                    }
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return map;
    }

    /**
     * check node is available or unavailable
     */
    private void checkProxyInfo(Map<String,IProxy> map) throws InterruptedException {
        logger.warn("CHECK NODE LIST:{}",map.size());
        Set<String> unavailableNodeList = Sets.newConcurrentHashSet();
        CountDownLatch countDownLatch = new CountDownLatch(map.size());
        StopWatch sw = new StopWatch();
        sw.start();
        map.forEach((key,value)->{
            if(value.getExpire() < System.currentTimeMillis()){
                unavailableNodeList.add(key);
                countDownLatch.countDown();
            }else {
                threadPool.submit(new Runnable() {
                    @Override
                    public void run() {
                        int errCount = 0;
                        int retryCount = 0;
                        for (int i = 0; i < retryCount+1; i++) {
                            try {
                                get(getHttpClient(value), CHECK_URL);
                                break;
                            } catch (Exception e) {
                                errCount++;
                                //logger.error(e.getMessage(), e);
                            }
                        }
                        if(errCount > retryCount){
                            unavailableNodeList.add(key);
                        }
                        countDownLatch.countDown();
                    }
                });
            }
        });
        countDownLatch.await();
        sw.stop();
        unavailableNodeList.forEach(key->{ map.remove(key);});
        logger.info("check proxy spend {} s",sw.getTotalTimeSeconds());
    }

    private OkHttpClient getHttpClient(IProxy iProxy){
        OkHttpClient.Builder builder =  client.newBuilder().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);
        if(IS_PROXY){
            if(iProxy!=null){
                builder.proxy(iProxy.getProxy());
            }
        }
        return builder.build();
    }


    private IResponse makeIResponse(Object response){
        IResponse iResponse = new IResponse();
        try {
            if(response instanceof Response){
                Response okHttpResponse = (Response)response;
                iResponse.setData(okHttpResponse.body().string());
                iResponse.setStatus(okHttpResponse.code());
                okHttpResponse.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return iResponse;
    }

    public IResponse get(String url,Map<String,String> headers) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = getHttpClient(getIProxy()).newCall(request).execute();
        return  makeIResponse(response);
    }

    private IResponse get(OkHttpClient client,String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return  makeIResponse(response);
    }
    public IResponse postParam(String url, Map<String,Object> param,Map<String,String> headers,Proxy proxy) throws Exception {
        FormBody.Builder formBody = new FormBody.Builder();
        headers.forEach((key,value)->{ formBody.add(key,value); });
        RequestBody requestBody = formBody.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = getHttpClient(getIProxy()).newCall(request).execute();
        return  makeIResponse(response);
    }

    public IResponse post(String url,Object body,Map<String,String> headers,Proxy proxy) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(body.toString(),MEDIA_TYPE_HTML))
                .build();
        Response response = getHttpClient(getIProxy()).newCall(request).execute();
        return  makeIResponse(response);
    }



    public static void main(String[] args) throws Exception {
        //IHttpClient client = new IHttpClient(true);
    }
}
