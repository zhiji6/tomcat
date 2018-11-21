package tomcat_v2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 服务端接口
 */
public interface Servlet {

    public void init();

    public void Service(InputStream is, OutputStream ops) throws IOException;

    public void destroy();
}
