## 01 simple demo

01-simple-provider-user   模拟了一个简单的服务提供者

01-simple-consumer-order  模拟了一个简单的服务消费者

消费者通过RestTemplate消费提供者提供的服务。

#### 这种方式存在的问题：

A：如果服务提供者的地址或者端口发生变化，那么消费者也必须随着改变。

B：如果服务提供者有多个节点，那么nginx负载的时候也比较麻烦。

怎么解决呢？引入了服务发现者。

服务提供者和服务消费者启动的时候，都会向服务发现组件注册。如果服务提供者停止服务，服务消费者会隔一段时间向服务发现者组件发送心跳，如果没有服务提供者的相应，则将此服务提供者从服务发现组件剔除。

![微服务原理](https://github.com/liuyanliang2015/springCloud/blob/master/pics/spring-cloud1.png)

### 服务发现组件的功能：

A：服务注册表<br>
服务注册表是一个记录当前可用服务实例的网络信息的数据库，是服务发现机制的核心。服务注册表提供查询API和管理API，使用查询API获得可用的服务实例，使用管理API实现注册和注销；

B：服务注册<br>
服务注册很好理解，就是服务启动时，将服务的网络地址注册到服务注册表中；

C:健康检查<br>
服务发现组件会通过一些机制定时检测已注册的服务，如果发现某服务无法访问了（可能是某几个心跳周期后），就将该服务从服务注册表中移除。

### 服务发现的方式

A：客户端发现，例如：Eureka，Zookeeper

B：服务器端发现，例如：consul+nginx


## 02 Eureka-ribbon

### A：Eureka与ribbon关系

Eureka担当注册中心，服务发现的角色

ribbon负责负载均衡的功能

Eureka已经默认集成ribbon，也就是配置了spring-cloud-starter-eureka，就需要配置ribbon了

![ribbon](https://github.com/liuyanliang2015/springCloud/blob/master/pics/ribbon2.png)


### B：负载均衡策略

@LoadBalanced让RestTemplate拥有负载均衡能力

       @Bean
 	   @LoadBalanced
 	   public RestTemplate restTemplate() {
          return new RestTemplate();
       }

自定义负载均衡策略（消费者）

    @RibbonClient(name = "provider-user", configuration = TestConfiguration.class)

为了让自定义负载策略只作用于某一个服务提供节点，有两种方法：

一种是将TestConfiguration定义在其他的包下，让@SpringBootApplication扫描不到

另外一种是使用自定义注解

    public @interface ExcludeFromComponentScan {}

    @SpringBootApplication
	@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) })


    @Configuration
	@ExcludeFromComponentScan
	public class TestConfiguration {

  	@Bean
  	public IRule ribbonRule() {
	 //随机策略
    return new RandomRule();
  	}
	}


## 03 eureka-ribbon-props

上面的例子是通过注解的方式配置ribbon以及策略，本例是通过yum(或者properties)来配置ribbon，相对比较简单。

    provider-user:
  	 ribbon:
     NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule