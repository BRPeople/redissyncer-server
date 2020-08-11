package syncer.syncerpluswebapp.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import syncer.syncerpluscommon.entity.ResultMap;
import syncer.syncerpluswebapp.util.TokenUtils;
import syncer.syncerservice.util.common.Strings;
import syncer.syncerservice.util.jedis.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhanenqiang
 * @Description 描述
 * @Date 2020/8/6
 */
@Slf4j
@Component
public class UserFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {


        String token=request.getHeader("X-Token");

        if(StringUtils.isEmpty(token)){
            response.setStatus(403);
            response.setContentType("application/json; charset=utf-8");

            returnJson(response,ResultMap.builder().code("100").msg("no token").json());

            return false;
        }else{
            if(!TokenUtils.checkToken(token)){
                response.setStatus(403);

                returnJson(response,ResultMap.builder().code("100").msg("no token").json());
                return false;
            }
        }


        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }



    private void returnJson(HttpServletResponse response, String json){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
            log.error("response error",e);
        } finally {
            if (writer != null){
                writer.close();
            }

        }
    }
}