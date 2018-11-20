package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TreeMap;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        Socket socket = null;
        OutputStream ops = null;
        try{
            //创建ServerSocket对象，监听本机对应的8080端口
            serverSocket = new ServerSocket(8080);
            while (true){
                //等待客户端的请求以及客户端对应的Socket对象
                socket = serverSocket.accept();
                //通过获取Socket对象获取输出流对象
                ops = socket.getOutputStream();
                //通过输出流对象将HTTP协议响应部分发送到客户端
                ops.write("HTTP/1.1 200 OK\n".getBytes());
                ops.write("Content-Type:text/html;charset=utf-8\n".getBytes());
                ops.write("Server:Apache-Coyote/1.1\n".getBytes());
                ops.write("\n\n".getBytes());
                StringBuffer buf = new StringBuffer();
                buf.append("<html>");
                buf.append("<head><title>这是自己写的一个服务器</title><head>");
                buf.append("<body>");
                buf.append("<h1>Welcome to Server</h1>");
                buf.append("</body");
                buf.append("</html");
                ops.write(buf.toString().getBytes());
                ops.flush();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            if(null!=ops){
                ops.close();
                ops = null;
            }
            if(null!=socket){
                socket.close();
                socket = null;
            }
        }
    }
}
