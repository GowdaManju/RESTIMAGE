package com.imageuplod.RESTIMAGE.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imageuplod.RESTIMAGE.entity.ProfileImages;
import com.imageuplod.RESTIMAGE.entity.UserRegister;

@Repository
public interface ProfilesRepository extends JpaRepository<ProfileImages,Long>{
	
	public ProfileImages findByUserID(String userId);
	
	
	@Transactional
	@Modifying
	@Query("update ProfileImages p set p.profile=null where userID= :userid")
	void removeProfile(@Param("userid") String userID);

	@Transactional
	@Modifying
	@Query("update ProfileImages p set p.profile=:image where userID= :userid")
	int updateProfile(@Param("image") byte[] imag,@Param("userid") String userIDe);
	
}
