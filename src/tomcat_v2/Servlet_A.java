package tomcat_v2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Servlet_A implements Servlet{
    @Override
    public void init() {
        System.out.print("A....init");
    }

    @Override
    public void Service(InputStream is, OutputStream ops) throws IOException {
        ops.write("I am A".getBytes());
        ops.flush();
    }

    @Override
    public void destroy() {
        System.out.print("A....destroy");
    }
}
