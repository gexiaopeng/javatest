package cn.gxp.srpc;






import java.io.IOException;
import net.srpc.core.DataTuple;
import net.srpc.core.SrpcInt;
import net.srpc.core.SrpcString;

/**
 *
 * @author Administrator
 */
public class SprcS extends net.srpc.SrpcServer {

    public static void main(String[] args) throws IOException {
        SprcS srpc = new SprcS();
        //srpc.addBlacklist("127.0.0.1");
        System.out.println("begin");
        srpc.start(8018, 1);
        System.out.println("begin start");
    }

     public net.srpc.core.DataTuple sendSms(net.srpc.Context context,net.srpc.core.SrpcString msg, net.srpc.core.SrpcString mobile) {

       String tel = mobile.toString();
       String message = msg.toString();
       System.out.println("tel:"+tel);
       System.out.println("message:"+message);
       System.out.println("context1:"+context.getCmd());
       System.out.println("context2:"+context.getIp());
       System.out.println("context3:"+context.getPackageLength());
       System.out.println("context4:"+context.getPort());
       System.out.println("context5:"+context.getType());
       System.out.println("context6:"+context.getInvObject());
        net.srpc.core.DataTuple t = new DataTuple();
        t.addDataItem(net.srpc.core.DataType.srpc_int, new SrpcInt(1));
        t.addDataItem(net.srpc.core.DataType.srpc_int, new SrpcInt(10));
        t.addDataItem(net.srpc.core.DataType.srpc_string, new SrpcString("成功"));
        return t;
    }
//    private net.hr.dbtools.DataOperater datao = new net.hr.dbtools.DataOperater();
//    public net.srpc.core.DataTuple test(net.srpc.Context context,net.srpc.core.SrpcLong id, net.srpc.core.SrpcDateTime dateTime) throws DBAException {
//
//        System.out.println(id);
//        System.out.println(dateTime);
//        net.srpc.core.DataTuple t = new DataTuple();
//        t.addDataItem(net.srpc.core.DataType.srpc_byte, new SrpcByte((byte) 1));
//        t.addDataItem(new DataTupleItem(DataType.srpc_string, new SrpcString("浣犲ソ")));
//        datao.add(Thread.currentThread().getName(), id.getValue(),dateTime.getDate().getTime());
//        return t;
//    }
//
//    public net.srpc.core.DataTuple test1(net.srpc.Context context, net.srpc.core.SrpcString string, net.srpc.core.SrpcDateTime dateTime) {
//
//        net.srpc.core.DataTuple t = new DataTuple();
//        t.addDataItem(net.srpc.core.DataType.srpc_byte, new SrpcByte((byte) 1));
//        t.addDataItem(new DataTupleItem(DataType.srpc_string, new SrpcString("浣犲ソ")));
//        System.out.println(string);
//        System.out.println(dateTime);
//        return t;
//    }
}
