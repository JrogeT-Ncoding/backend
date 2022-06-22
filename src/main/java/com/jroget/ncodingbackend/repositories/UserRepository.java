package com.jroget.ncodingbackend.repositories;

import com.jroget.ncodingbackend.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
//@Repository
public interface UserRepository extends CrudRepository<User, Integer> {


}
