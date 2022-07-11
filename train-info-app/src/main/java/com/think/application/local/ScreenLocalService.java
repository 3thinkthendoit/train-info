package com.think.application.local;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;
import com.think.application.local.spider.GetScreenInfoSpiderTencent;
import com.think.application.service.screen.ScreenAppService;
import com.think.domain.screen.model.CreateScreenInfoCommand;
import com.think.infrastructure.http.IHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车站大屏(业务定制+北向网关防腐)
 * 该逻辑暂时无法沉淀为车站大屏的领域能力
 * @author NINGMEI
 */
@Service
public class ScreenLocalService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ScreenAppService screenAppService;
    @Autowired
    IHttpClient iHttpClient;
    
    /**
     *
     * 腾讯出行数据
     */
    public void  getScreenTrainFormTencent(){
      try {
          //1获取原始数据 沉淀不了领域能力的 无需通过南向网关获取数据，依赖基础设施 acl-infrastructure
          GetScreenInfoSpiderTencent getScreenInfoSpiderTencent = new GetScreenInfoSpiderTencent();
          List<JSONArray> list = getScreenInfoSpiderTencent.execute(iHttpClient, Maps.newHashMap());
          //2组装 list -> CreateScreenInfoCommand
          list.forEach(arr->{
              CreateScreenInfoCommand command = new CreateScreenInfoCommand();
              //3调用AppService保存大屏信息
              screenAppService.saveScreenInfo(command);
          });

      }catch (Exception e){
          logger.error(e.getMessage(),e);
      }
    }


    /**
     * 12306数据
     */
    public void  getScreenTrainForm12306(){

    }


    /**
     * 九州出行数据
     */
    public void  getScreenTrainForm9zhou(){

    }

}
