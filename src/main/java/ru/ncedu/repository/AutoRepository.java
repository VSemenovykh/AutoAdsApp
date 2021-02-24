package ru.ncedu.repository;	

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.ncedu.entity.Auto;

import java.util.List;

@Repository	
public interface AutoRepository extends PagingAndSortingRepository<Auto, Long>, JpaSpecificationExecutor<Auto> {
	List<Auto> findAll();
	void deleteById(Long id);
}