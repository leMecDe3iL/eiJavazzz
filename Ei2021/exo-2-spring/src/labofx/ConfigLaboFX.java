package labofx;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;

import labofx.model.DaoCalculSerial;
import labofx.model.DaoCalculXml;
import labofx.model.IDaoCalcul;

@ComponentScan(lazyInit = true)
public class ConfigLaboFX {

	@Bean @Lazy
	public IDaoCalcul daoCalcul() {
		return new DaoCalculXml();
	}
	
}
