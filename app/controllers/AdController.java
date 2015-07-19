package controllers;

import java.util.List;

import model.Ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Result;
import services.AdSearchService;

@Controller
public class AdController extends play.mvc.Controller {

	@Autowired
	private AdSearchService adSearchService;

	public Promise<Result> findAdsByTitle(String title) {
		return adSearchService.findByTitle(title).map(new Function<List<Ad>, Result>() {

			@Override
			public Result apply(List<Ad> ads) throws Throwable {
				if (ads == null || ads.isEmpty()) {
					return noContent();
				} else {
					return ok(Json.toJson(ads));
				}
			}
		});
	}

}
