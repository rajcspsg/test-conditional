package com.as.demo.config;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ClientPolicy;
import com.as.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.core.AerospikeTemplate;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;

@Configuration
@EnableAerospikeRepositories(basePackages = "com.as.demo.repositories")
@ComponentScan(basePackageClasses = {UserService.class})
public class AerospikeConfig {

    @Bean(destroyMethod = "close")
    public AerospikeClient aerospikeClient() {

        try {
            ClientPolicy policy = new ClientPolicy();
            policy.failIfNotConnected = true;
            String localhost = "localhost";

            return new AerospikeClient(policy, localhost, 3000);
        } finally {
            return null;
        }

    }

    @Bean
    public  AerospikeTemplate aerospikeTemplate(@Autowired(required = false) AerospikeClient aerospikeClient) {
        if(aerospikeClient == null)
            return null;
        return new AerospikeTemplate(aerospikeClient, "test");
    }
}
