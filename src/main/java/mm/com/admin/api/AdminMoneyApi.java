package mm.com.admin.api;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mm.com.entity.Category;
import mm.com.entity.Money;
import mm.com.service.MoneyService;;

 

@RestController
@CrossOrigin
@RequestMapping("/admin/api")
public class AdminMoneyApi {

	@Autowired
	private MoneyService moneyService;

	@GetMapping(value = "/money")
	public List<Money> getNews() {		
		return moneyService.findAll();
	}
	
	@GetMapping(value = "/moneyByDay/{date}")
	public List<Money> getMoneyByDay(@PathVariable("date") String date) {	
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		Date startDate = new Date();
		try {
		    startDate = df.parse(date);
		    String newDateString = df.format(startDate);
		    System.out.println(newDateString);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return moneyService.findAllByDay(startDate);
	}

	@GetMapping(value = "/moneyByMonth/{date}")
	public List<Money> getMoneyByMonth(@PathVariable("date") String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		Date startDate = new Date();
		try {
		    startDate = df.parse(date);
		    String newDateString = df.format(startDate);
		    System.out.println(newDateString);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return moneyService.findAllByMonth(startDate);
	}

	
	@GetMapping(value = "/money/{id}")
	public Money getNewsOne(@PathVariable("id") Long id) {
		return moneyService.findById(id);
	}

	@DeleteMapping(value = "/money")
	public void deleteNews(@RequestBody long[] ids) {
		moneyService.delete(ids);
	}

	@PutMapping(value = "/money/{id}")
	public Money updateNews(@RequestBody Money money, @PathVariable("id") Long id) {		
		money.setId(id);
		return moneyService.save(money);
	}
	
	@PostMapping(value = "/money")
	public Money saveMoney(@RequestBody Money money) {
		return moneyService.save(money);
	}
	
	public static String converSlug(String title) {
		String temp = Normalizer.normalize(title, Normalizer.Form.NFD);
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    System.out.println(pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d") );
	    return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
		  
	}
	
}
