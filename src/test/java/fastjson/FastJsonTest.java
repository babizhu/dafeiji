package fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.net.protocol.LoginRequest;
import org.junit.Test;

import java.math.BigDecimal;

public class FastJsonTest{

    @Test
    public void testRead() throws Exception{


//        String data = "{\"mod\":\"User\",\"do\":\"login\",\"p\":{\"sId\":1,\"uName\":\"11\",\"uPass\":\"111q\",\"uDevId\":\"\",\"devId\":\"\"}}";
//        JSONObject parse = (JSONObject) JSON.parse( data );
//        String p = parse.get( "p" ).toString();
//        System.out.println( p );
//
//        LoginRequest request = JSON.parseObject( p, LoginRequest.class );
//        System.out.println( request );

    }


    @Test
    public void testWrite() throws Exception{

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Jobs");
        jsonObject.put("age", 50);
        jsonObject.put("salary", new BigDecimal(8000));

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put( "msg", "login" );
        jsonObject1.put("arg",jsonObject);
        String text = jsonObject1.toJSONString();
        System.out.println(text);

    }
}