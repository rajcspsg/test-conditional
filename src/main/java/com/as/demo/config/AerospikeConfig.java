package com.as.demo.config;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ClientPolicy;
import com.as.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.data.aerospike.core.AerospikeTemplate;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;

class AerospikeDataSourceCondition implements Condition {

    @Value( "${userstore.host}")
    private String host;

    @Value( "${userstore.port}")
    private int port;

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        try {
            System.out.println("host = "+ host);
            System.out.println("port = "+ port);
            System.out.println("${userstore.host} " + System.getProperty("userstore.host"));
            System.out.println("${userstore.port} " + System.getProperty("userstore.port"));
            new AerospikeClient(new ClientPolicy(), System.getProperty("userstore.host"), Integer.parseInt(System.getProperty("userstore.port")));
            System.out.println("returns true");
            return true;
        } catch (Exception e) {

        }
        return false;
    }
}


@Configuration
@EnableAerospikeRepositories(basePackages = "com.as.demo.repositories")
@ComponentScan(basePackageClasses = {UserService.class})
@Conditional(value=AerospikeDataSourceCondition.class)
public class AerospikeConfig {

    @Value( "${userstore.host}")
    private String host;

    @Value( "${userstore.port}")
    private int port;

    @Value( "${userstore.test}")
    private String namespace = "test";

    @Bean(destroyMethod = "close")
    @Conditional(value=AerospikeDataSourceCondition.class)
    public AerospikeClient aerospikeClient() {

        ClientPolicy policy = new ClientPolicy();
        policy.failIfNotConnected = true;
        return new AerospikeClient(policy, host, port);

    }

    @Bean
    @Conditional(value=AerospikeDataSourceCondition.class)
    public  AerospikeTemplate aerospikeTemplate(@Autowired(required = false) AerospikeClient aerospikeClient) {
        if(aerospikeClient == null)
            return null;
        return new AerospikeTemplate(aerospikeClient, "test");
    }
}
