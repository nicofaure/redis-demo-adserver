import play.Application;
import play.GlobalSettings;
import spring.SpringApplicationContext;

public class Global extends GlobalSettings {
	@Override
	public void onStart(Application app) {
		SpringApplicationContext.initialize();
	}

}
