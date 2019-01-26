package CONFIG;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.BeforeClass;
import org.xml.sax.SAXException;

public class BeforeClassRun extends CONFIGURATION{
//	@BeforeClass
	public void before() throws IOException, ParserConfigurationException, SAXException
	{
		Init();					
								
		}
}
