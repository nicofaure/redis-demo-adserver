package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import redis.RedisClient;
import spring.SpringApplicationContext;
import views.html.index;

public class Application extends Controller {

    public static Result index() {

    	//An example to test that redis client is working
		RedisClient client = SpringApplicationContext.getBean(RedisClient.class);
		client.set("key", "value");

        return ok(index.render("Your new application is ready."));
    }

}
