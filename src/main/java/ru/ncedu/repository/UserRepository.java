package ru.ncedu.repository;	

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.Auto;
import ru.ncedu.entity.User;

import java.util.List;	

@Repository	
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
    public List<User> findByName(String name);	

}