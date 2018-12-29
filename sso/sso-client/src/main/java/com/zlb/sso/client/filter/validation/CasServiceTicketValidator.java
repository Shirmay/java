package com.zlb.sso.client.filter.validation;

import com.zlb.sso.client.authentication.SimplePrincipal;
import com.zlb.sso.client.utils.HttpUtils;
import org.json.JSONObject;

public class CasServiceTicketValidator implements TicketValidator{

    @Override
    public SimplePrincipal validation(String ticket, String url) throws Exception{
        String result = HttpUtils.doPost(url, null);
        if (result == null){
            throw new RuntimeException("ticket validate fail");
        }
        JSONObject data = new JSONObject(result);
        int code = data.getInt("code");
        if (code == -1){
            throw new RuntimeException("ticket validate fail");
        }
        String name = data.getString("data");
        return new SimplePrincipal(name);
    }
}
