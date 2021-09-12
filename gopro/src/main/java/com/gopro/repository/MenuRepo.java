package com.gopro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.gopro.bene.Menu;

public interface MenuRepo  extends PagingAndSortingRepository<Menu, Long>  {

	@Query(value = "SELECT * FROM MENU MNU INNER JOIN ROLE_MENU_LIST ROLEMAP ON ROLEMAP.menu_list_menuid = MNU.menuid INNER JOIN USER USR ON USR.ROLEID = ROLEMAP.role_roleid WHERE USR.id = :USERID ORDER BY MNU.menuqueue asc", nativeQuery = true)
	List<Menu> findMenuByUserId(@Param("USERID")Long userId);

}
