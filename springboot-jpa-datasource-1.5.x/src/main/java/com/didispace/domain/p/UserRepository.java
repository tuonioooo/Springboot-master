package com.didispace.domain.p;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * @author tony
 */
public interface UserRepository extends JpaRepository<User, Long> {


}
