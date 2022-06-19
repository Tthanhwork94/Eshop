package com.mockproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mockproject.entity.Products;
import com.mockproject.entity.Users;



@Repository
public interface UsersRepo extends JpaRepository<Users, Long>{

	Users findByUsername(String username);
	List<Users> findByIsDeleted(Boolean isDeleted);
	
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE users SET isDeleted = 1 WHERE username = ?",nativeQuery = true)
	void deleteLogical(String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE users SET email = ?1, fullname= ?2 WHERE username=?3",nativeQuery=true)
	void updateNonPassword(String email,String fullname,String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE users SET email= ?1 , hashPassword= ?2 , fullname= ?3 WHERE username = ?4",nativeQuery=true)
	void updateHasPassword(String email, String hashPassword, String fullname, String username);
	
	
}
