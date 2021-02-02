package com.example.demo;

import com.example.demo.bean.User;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.web.multipart.MultipartResolver;
//import org.springframework.web.servlet.DispatcherServlet;

//@Import({User.class,User.class})//快速导入组件，id默认是全类名，Import两个User.class容器中只有一个com.example.demo.bean.User
@Configuration(proxyBeanMethods = true)
// @ConditionalOnMissingBean(name = "user2")
// //在装配Congig类的所有Bean之前检查user2是否存在，肯定不存在所以同时装配user2,user1

//使TestConfigurationProperties生效
@EnableConfigurationProperties(TestConfigurationProperties.class)
public class TestAutoConfiguration{

    private TestConfigurationProperties tcp;
    public TestAutoConfiguration(TestConfigurationProperties testConfigurationProperties){
        this.tcp = testConfigurationProperties;
    }

    @Bean
    public User userP(){
        User user = new User();
        user.setName(tcp.getName());
        user.setAge(tcp.getAge());
        return user;
    }

    @Bean
    public User user2() {
        return new User("lihua", 19);
    }

    @Bean
    @ConditionalOnMissingBean(name = "user2") // 装配user1前检查user2是否存在，与顺序有关，user2在前声明，那么user1不装配。在后声明则装配
    public User user1() {
        return new User("zhangsan", 18);
    }

    /*
     * @Bean public User user2(){ return new User("lihua",19); }
     */

    /*
     * @ConditionalOnProperty
     * @ConditionalOnBean 和 ConditionalOnMissingBean
     * @ConditionalOnClass 和 @ConditionalOnMissingClass
     * @ConditionalOnExpression
     * @ConditionalOnSingleCandidate
     * @ConditionalOnResource
     * @ConditionalOnJndi
     * @ConditionalOnJava
     * @ConditionalOnWebApplication 和 @ConditionalOnNotWebApplication
     * @ConditionalOnCloudPlatform
     * 
     * 
     * @EnableConfigurationProperties注解的作用是：使使用 @ConfigurationProperties 注解的类生效。 说明：
     * 如果一个配置类只配置@ConfigurationProperties注解，而没有使用@Component，那么在IOC容器中是获取不到properties
     * 配置文件转化的bean。说白了 @EnableConfigurationProperties
     * 相当于把使用 @ConfigurationProperties 的类进行了一次注入。 
     * 测试发现 @ConfigurationProperties与 @EnableConfigurationProperties关系特别大。 
     * 测试证明：
     * @ConfigurationProperties 与 @EnableConfigurationProperties 的关系。
     * @EnableConfigurationProperties 文档中解释：
     * 当@EnableConfigurationProperties注解应用到你的@Configuration时，
     * 任何被@ConfigurationProperties注解的beans将自动被Environment属性配置。
     * 这种风格的配置特别适合与SpringApplication的外部YAML配置进行配合使用。
     *
     * 
     * //源码中给容器中某个类型的Bean重命名后放入容器
     * @Bean
     * @ConditionalOnBean(MultipartResolver.class) // 容器中有这个类型组件
     * @ConditionalOnMissingBean(name =
     * DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME) // 容器中没有这个名字
     * multipartResolver 的组件 public MultipartResolver
     * multipartResolver(MultipartResolver resolver) { //
     * 给@Bean标注的方法传入了对象参数，这个参数的值就会从容器中找。 // SpringMVC
     * multipartResolver。防止有些用户配置的文件上传解析器不符合规范 return resolver; } // 给容器中加入了文件上传解析器；
     */
}