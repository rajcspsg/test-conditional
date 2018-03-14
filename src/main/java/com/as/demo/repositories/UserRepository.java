package com.as.demo.repositories;


import com.as.demo.entity.User;
import org.springframework.data.aerospike.repository.AerospikeRepository;

public interface UserRepository extends AerospikeRepository<User, String> {

}
