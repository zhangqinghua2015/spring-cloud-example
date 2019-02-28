package com.zqh.repository;

import com.zqh.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @discription:
 * @date: 2019/02/22 上午10:34
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
