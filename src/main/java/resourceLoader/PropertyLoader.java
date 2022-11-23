package resourceLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader
{
    public static Properties getProperties() throws IOException
    {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/common.properties");
            prop = new Properties();
            prop.load(fis);
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }
}




