package tomcat_v1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    //存服务器资源文件路径
    public static String WEB_ROOT = System.getProperty("user.dir")+"\\" + "webRoot" +"\\";

    private static String url = "";

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        OutputStream ops = null;
        try {
            System.out.println(WEB_ROOT);
            serverSocket  = new ServerSocket(8080);
            while (true){
                //获取客户端对应的Socket
                socket = serverSocket.accept();
                //获取输入流对象
                is = socket.getInputStream();
                //获取输出流对象
                ops = socket.getOutputStream();

                //解析请求的资源
                parset(is);
                //发送资源
                send2FontEnd(ops);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=is){
                is.close();
                is = null;
            }
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

    //获取请求部分，解析客户端访问的资源名称，将这个资源给url
    private static void parset(InputStream is) throws IOException {
        //存储数据
        StringBuffer content = new StringBuffer(2048);
        byte[] buff = new byte[2048];
        //定义数据量大小
        int max = -1;
        //读取发送的数据，将数据存放在数组中，max代表数据量的大小
        max = is.read(buff);
        //遍历数组，将内同追加到StringBuffer中
        for(int j = 0;j < max;j++){
            content.append((char)buff[j]);
        }
        System.out.print(content);
        parseUrl(content.toString());

    }

    private static void parseUrl(String content) {
        int index1,index2;
        index1 = content.indexOf(" ");
        if(index1!=-1){
            index2 = content.indexOf(" ",index1+1);
            if(index2 > index1){
                url = content.substring(index1+2,index2);
            }
        }
        System.out.print(url);
    }

    private static void send2FontEnd(OutputStream ops) throws IOException {
        byte[] bytes = new byte[2048];
        FileInputStream fis = null;
        try{
            File file = new File(WEB_ROOT,url);
            if(file.exists()){
                ops.write("HTTP/1.1 200 OK\n".getBytes());
                ops.write("Content-Type:text/html;charset=utf-8\n".getBytes());
                ops.write("Server:Apache-Coyote/1.1\n".getBytes());
                ops.write("\n".getBytes());
                fis = new FileInputStream(file);
                int ch = fis.read(bytes);
                while (ch != -1){
                    ops.write(bytes,0,ch);
                    ch = fis.read(bytes);
                }
            }else{
                ops.write("HTTP/1.1 404 not found\n".getBytes());
                ops.write("Content-Type:text/html;charset=utf-8\n".getBytes());
                ops.write("Server:Apache-Coyote/1.1\n".getBytes());
                ops.write("\n\n".getBytes());
                String msg = "File not found!";
                ops.write(msg.getBytes());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null!=fis){
                fis.close();
                fis = null;
            }
        }
    }
}
