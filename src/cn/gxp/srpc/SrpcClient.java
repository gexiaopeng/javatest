package cn.gxp.srpc;





import java.io.UnsupportedEncodingException;

import net.srpc.SrpcNetException;
import net.srpc.SrpcServerException;
import net.srpc.core.DataTuple;
import net.srpc.core.DataType;
import net.srpc.core.SrpcDateTime;
import net.srpc.core.SrpcPackeageException;
import net.srpc.core.UnsupportedSprcDataType;

/**
 *
 * @author Administrator
 */
public class SrpcClient extends net.srpc.SprcClient {

    private  long v = 0;

    public SrpcClient(String addr, int port) {
        super(addr, port);
    }

    public void send() throws Exception {
        net.srpc.core.DataTuple args = new DataTuple();
        v++;
        String sms="首先将你要反编译的class文件利用Java自带的jar命令将class文件打包首先将你要反编译的class文件利用Java自带的jar命令将class文件打包首先将你要反编译的class文件利用Java自带的jar命令将class文件打包成.jar包，然后导入到你的Eclipse的Project中去，接着就可以展开刚才导入的jar包，查看class文件相应的源文件了";
        args.addDataItem(net.srpc.core.DataType.srpc_string, new net.srpc.core.SrpcString(sms));
        args.addDataItem(net.srpc.core.DataType.srpc_string, new net.srpc.core.SrpcString("123456"));
        net.srpc.core.DataTuple dataTuple = call("sendSms", args);
        System.out.println(net.srpc.core.DataType.srpc_int);
        for (int i = 0; i < dataTuple.getDataTupleItemCount(); i++) {
        	 
            System.out.println(dataTuple.getDataType(i));
        }
    }

//    public void send1() throws UnsupportedSprcDataType, SrpcNetException, SrpcServerException, SrpcPackeageException {
//        net.srpc.core.DataTuple args = new DataTuple();
//        args.addDataItem(net.srpc.core.DataType.srpc_string, new net.srpc.core.SrpcString("你好这是测试方法"));
//        args.addDataItem(DataType.srpc_datetime, new SrpcDateTime());
//        net.srpc.core.DataTuple dataTuple = call("test1", args);
//        for (int i = 0; i < dataTuple.getDataTupleItemCount(); i++) {
//            System.out.println(dataTuple.getObject(i));
//        }
//    }

    public static void main(String[] args) throws UnsupportedSprcDataType, SrpcNetException, SrpcServerException, SrpcPackeageException, Exception {
       String ip="192.168.10.78";
        ip="127.0.0.1";
    	SrpcClient s = new SrpcClient(ip, 8018);
    	long l=System.currentTimeMillis();
    	int size=1;
        for (int i = 0; i < size; i++) {
         	//long l2=System.currentTimeMillis();
            s.send();
            //System.out.println("l2:"+(System.currentTimeMillis()-l2));
        }
        System.out.println(System.currentTimeMillis()-l);
    }
}
