package controllers;

import static spring.SpringApplicationContext.getBean;
import play.libs.F.Promise;
import play.mvc.Result;

public class ControllerFactory {

	public static Promise<Result> findAdsByTitle(String title) {
		return getBean(AdController.class).findAdsByTitle(title);
	}
}
