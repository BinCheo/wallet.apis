package mm.com.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mm.com.entity.Money;
import mm.com.repository.MoneyRepository;
 
@Service
public class MoneyService{

	@Autowired
	private MoneyRepository moneyRepository;
	
	public List<Money> findAll() {
		List<Money> rs = moneyRepository.findAll();
		return rs;
	}
	public List<Money> findAllByDay(Date date) {
		List<Money> rs = moneyRepository.findAllByDay(date);
		return rs;
	}
	public List<Money> findAllByMonth(Date date) {
		List<Money> rs = moneyRepository.findAllByMonth(date);
		return rs;
	}
	public Money save(Money money) {
		moneyRepository.save(money);
		return money;
	}
	
	public void delete(long[] ids) {
		for(long item: ids) {
			moneyRepository.deleteById(item);
		}	
	}
	
	public Money findById(long id) {
		return moneyRepository.findById(id).get();
	}
}
