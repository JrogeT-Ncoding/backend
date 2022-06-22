package com.jroget.ncodingbackend.repositories;

import com.jroget.ncodingbackend.models.User;
import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends CrudRepository<User, Integer> {


}
