package mm.com.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mm.com.entity.Category;
import mm.com.entity.Money;


 


@Repository
public interface MoneyRepository extends JpaRepository<Money, Long> {
	@Query("Select m from Money m where DATE_FORMAT(date, '%Y %M %d') = DATE_FORMAT(?1,'%Y %M %d')  ")
	List<Money> findAllByDay(Date date);
	
	@Query("Select m from Money m where DATE_FORMAT(date, '%Y %M') = DATE_FORMAT(?1,'%Y %M') ")
	List<Money> findAllByMonth(Date date);
}
