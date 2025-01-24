package com.example.easyaccount.interceptor;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.easyaccount.service.UserService;
import com.example.easyaccount.tools.JWTUtil;
import com.example.easyaccount.tools.RedisService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/** * 判断 redis 中是否存在 user 属性，如果存在就通过，如果不存在就跳转到 login 页面 */
@Service
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("preHandle....");
        String uri = httpServletRequest.getRequestURI();
        System.out.println("当前路径："+uri);

        final Logger log = LoggerFactory.getLogger(UserService.class);

        Map userInfo = JSONUtil.parseObj(redisService.get("userInfo"));

        if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
            System.out.println("OPTIONS请求，放行");
            return true;
        }
        String token = httpServletRequest.getHeader("Authorization");
        System.out.println(token);
        System.out.println(userInfo.get("token"));

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");

        PrintWriter out = null;
        JSONObject json = new JSONObject();

        // 2、判断 token 是否存在
        if (
                token == null
                || "".equals(token)
                || null == JWTUtil.verifyToken(token)
                || JWTUtil.verifyToken(token).equals(userInfo.get("id"))
        ) {
            System.out.println("未登录");
            try {
                json.set("code", 401);
                json.set("msg", "请携带token");
                out = httpServletResponse.getWriter();
                out.print(json);
                return false;
            } catch (IOException e) {
                log.error("response error", e);
            } finally {
                if (out != null)
                    out.close();
            }
        }

        return true;
    }

    /**
     * 访问控制器方法后执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(new Date() + "--postHandle:" + request.getRequestURL());
    }

    /**
     * postHandle方法执行完成后执行，一般用于释放资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(new Date() + "--afterCompletion:" + request.getRequestURL());
    }
}
