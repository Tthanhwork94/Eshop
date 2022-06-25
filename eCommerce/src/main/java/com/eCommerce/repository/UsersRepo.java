package com.eCommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eCommerce.entity.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
	Users findByUsername(String username);
	@Query(value = "SELECT COUNT(*) FROM users",nativeQuery = true)
	Integer countUsers();
	List<Users> findByIsDeleted(Boolean isDeleted);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE users SET isDeleted = 1 WHERE username = ? ",nativeQuery = true)
	void deleteLogical(String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE users SET email=?1, hashPassword=?2 WHERE username =?3 ",nativeQuery = true)
	void update(String email,String hashPassword, String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE users SET email=?1 WHERE username =?2 ",nativeQuery = true)
	void updateNonPass(String email, String username);
}
