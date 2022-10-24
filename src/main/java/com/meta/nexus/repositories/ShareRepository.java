package com.meta.nexus.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meta.nexus.models.Share;

@Repository
public interface ShareRepository extends CrudRepository<Share, Long>{
	List<Share> findAll();
	
	List<Share> findAllByOrderByCreatedAtDesc();
	

}
