package net.vijay.amlosafe.API.util;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ 
	"file:src/main/resources/net/kosmos/config/properties/environment.properties" // mention the property file name
})
public interface ConfigProperty extends Config{
		
	@Key("testReportPath")
	String getTestReportFilepath();
	
	@Key("testReportName")
	String getTestReportName();
	
	@Key("baseURI")
	String getBaseURI();
	
	@Key("dataFilePath")
	String getDataFilePath();
}
