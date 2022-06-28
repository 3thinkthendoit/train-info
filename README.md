# 列车正晚点,车站大屏实时系统

<pre>
DDD菱形架构参考
</pre>

![image](https://user-images.githubusercontent.com/13362524/176121875-f306b547-e157-4717-aa47-d7ba78d191e7.png)
<pre>
调用链参考：
</pre>
![image](https://user-images.githubusercontent.com/13362524/176122143-773e7ba8-b6a0-43bb-b5ef-3a5682481c1b.png)


代码结构说明

<!-- DIRSTRUCTURE_START_MARKER -->
<pre>
train-info/

├─ starter/ .................................................. 启动类

├─ train-info-acl/ ........................................... 菱形架构南向网关
│  └─ src/ ................................................... 
│     └─ main/ ............................................... 
│        ├─ java/ ............................................ 
│        │  └─ com/ .......................................... 
│        │     └─ think/ ..................................... 
│        │        ├─ acl/ .................................... 
│        │        │  └─ port/ ................................ 南向网关port实现
│        │        │     ├─ gateway/ .......................... 外部服务实现
│        │        │     │  ├─ GetStationGatewayImpl.java ..... 
│        │        │     │  └─ GetTrainInfoGatewayImpl.java ... 
│        │        │     ├─ pl/ ............................... 南向网关防腐层
│        │        │     │  ├─ GetStationInfoSpider12306.java . 
│        │        │     │  ├─ GetTrainDetailSpider12306.java . 
│        │        │     │  ├─ GetTrainInfoSpider12306.java ... 
│        │        │     │  └─ SpiderService.java ............. 
│        │        │     └─ repository/ ....................... 资源库实现
│        │        │        ├─ StationRepositoryImpl.java ..... 
│        │        │        └─ TrainRepositoryImpl.java ....... 
│        │        ├─ common/ ................................. 通用工具
│        │        │  ├─ assembler/ ........................... 
│        │        │  │  └─ StationAssembler.java ............. 
│        │        │  ├─ config/ .............................. 
│        │        │  │  └─ ThreadPool.java ................... 
│        │        │  ├─ constant/ ............................ 
│        │        │  │  └─ CMDConstant.java .................. 
│        │        │  ├─ exception/ ........................... 
│        │        │  │  ├─ TException.java ................... 
│        │        │  │  └─ TrainExceptionHandler.java ........ 
│        │        │  └─ util/ ................................ 
│        │        │     ├─ CommonUtil.java ................... 
│        │        │     ├─ R.java ............................ 
│        │        │     └─ SpringContextUtil.java ............ 
│        │        └─ infrastructure/ ......................... 基础设施层
│        │           ├─ http/ ................................ http实现
│        │           │  ├─ IHttpClient.java .................. 
│        │           │  ├─ IResponse.java .................... 
│        │           │  └─ proxy/ ............................ 
│        │           │     └─ IProxy.java .................... 
│        │           └─ mybatis/ ............................. ORM实现
│        │              ├─ config/ ........................... 
│        │              │  └─ MybatisPlusConfig.java ......... 
│        │              ├─ Injector/ ......................... 
│        │              │  └─ BatchSqlInjector.java .......... 
│        │              ├─ mapper/ ........................... 
│        │              │  ├─ BatchMapper.java ............... 
│        │              │  ├─ IStationInfoMapper.java ........ 
│        │              │  ├─ ITrainDetailMapper.java ........ 
│        │              │  └─ ITrainInfoMapper.java .......... 
│        │              └─ po/ ............................... 
│        │                 ├─ StationInfoPO.java ............. 
│        │                 ├─ TrainDetailPO.java ............. 
│        │                 └─ TrainInfoPO.java ............... 
│        └─ resources/ ....................................... 配置文件
│           └─ mapper/ ....................................... 
│              ├─ StationInfoMapper.xml ...................... 
│              ├─ TrainDetailMapper.xml ...................... 
│              └─ TrainInfoMapper.xml ........................ 
├─ train-info-app/ ........................................... 本地北向网关(local+app应用层)
│  └─ src/ ................................................... 
│     └─ main/ ............................................... 
│        └─ java/ ............................................ 
│           └─ com/ .......................................... 
│              └─ think/ ..................................... 
│                 └─ application/ ............................ 业务层(流程编排)
│                    ├─ bo/ .................................. 
│                    │  └─ StationBO.java .................... 
│                    └─ service/ ............................. 
│                       ├─ station/ .......................... 
│                       │  └─ StationAppService.java ......... 
│                       └─ train/ ............................ 
│                          └─ TrainAppService.java ........... 
├─ train-info-domain/ ........................................ 领域层
│  └─ src/ ................................................... 
│     └─ main/ ............................................... 
│        └─ java/ ............................................ 
│           └─ com/ .......................................... 
│              └─ think/ ..................................... 
│                 └─ domain/ ................................. 
│                    ├─ city/ ................................ 城市限界上下文
│                    │  ├─ model/ ............................ 
│                    │  │  └─ CityEntity.java ................ 
│                    │  └─ service/ .......................... 
│                    ├─ common/ .............................. 
│                    │  ├─ TrainDataSource.java .............. 
│                    │  ├─ assembler/ ........................ 
│                    │  │  └─ DomainAssembler.java ........... 
│                    │  └─ util/ ............................. 
│                    ├─ station/ ............................. 车站限界上下文
│                    │  ├─ model/ ............................ 车站领域模型
│                    │  │  ├─ CreateStationCommand.java ...... 创建聚合根command
│                    │  │  ├─ StationAggregate.java .......... 车站聚合根(领域行为+工厂)
│                    │  │  └─ StationId.java ................. 车站唯一标识(Domain Primitive)
│                    │  ├─ port/ ............................. 领域port适配
│                    │  │  ├─ gateway/ ....................... 三方服务接口
│                    │  │  │  └─ GetStationGateway.java ...... 
│                    │  │  └─ repository/ .................... 资源库接口
│                    │  │     └─ StationRepository.java ...... 
│                    │  └─ service/ .......................... 领域服务(不属于聚合根的领域服务，限界上下文协调领域服务)
│                    │     └─ GetStationDomainService.java ... 
│                    └─ train/ ............................... 车次限界上下文
│                       ├─ model/ ............................ 车次领域模型
│                       │  ├─ CreateTrainDetailCommand.java .. 创建聚合根command
│                       │  ├─ CreateTrainInfoCommand.java .... 
│                       │  ├─ TrainDetailEntity.java ......... 车次运行区间信息
│                       │  ├─ TrainId.java ................... 车次唯一标识(Domain Primitive)
│                       │  └─ TrainInfoAggregate.java ........ 车次信息聚合根(领域行为+工厂)
│                       ├─ port/ ............................. 领域port适配
│                       │  ├─ gateway/ ....................... 三方服务接口
│                       │  │  └─ GetTrainInfoGateway.java .... 
│                       │  └─ repository/ .................... 资源库接口
│                       │     └─ TrainRepository.java ........ 
│                       └─ service/ .......................... 领域服务
│                          └─ GetTrainInfoDomainService.java . 
└─ train-info-osh/ ........................................... 远程北向网关
   ├─ pom.xml ................................................ 
   └─ src/ ................................................... 
      └─ main/ ............................................... 
         └─ java/ ............................................ 
            └─ com/ .......................................... 
               └─ think/ ..................................... 
                  └─ adapter/ ................................ 网关适配器(web,RPC,MQ)
                  │   ├─ dto/ ................................. 
                  │   │  └─ cmd/ .............................. 
                  │   │     ├─ GetAllStationCmd.java .......... 
                  │   │     └─ GetAllTrainInfoCmd.java ........ 
                  │   │─ web/ ................................. 
                  │   │   └─ TrainController.java .............
                  └─ pl/ ...................................... 北向网关防腐层(PL+业务防腐)   
</pre>
<!-- DIRSTRUCTURE_END_MARKER -->


技术栈：
spring boot
webmagic


