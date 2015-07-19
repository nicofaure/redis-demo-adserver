package services;

import java.util.ArrayList;
import java.util.List;

import model.Ad;

import org.springframework.stereotype.Component;

import play.libs.F.Function0;
import play.libs.F.Promise;

@Component
public class AdSearchService {

	public Promise<List<Ad>> findByTitle(String title) {
		return Promise.promise(new Function0<List<Ad>>() {
			@Override
			public List<Ad> apply() throws Throwable {
				// This is just mock code. Dani, you must remove this!!!
				List<Ad> ads = new ArrayList<Ad>();
				ads.add(Ad.builder()
						.title("Macbook Pro")
						.clickUrl("http://localhost:9000/click/1")
						.printUrl("http://localhost:9000/print/1")
						.image("")
						.cpc(0.049D)
						.build());
				ads.add(Ad.builder()
						.title("Macbook Air")
						.clickUrl("http://localhost:9000/click/2")
						.printUrl("http://localhost:9000/print/2")
						.image("")
						.cpc(0.049D)
						.build());
				
				return ads;
			}
		});
	}
	
	
}
