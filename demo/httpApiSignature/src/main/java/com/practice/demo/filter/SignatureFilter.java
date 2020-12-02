package com.practice.demo.filter;

import com.google.common.cache.Cache;
import com.practice.demo.config.Constants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Enumeration;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

@Component
public class SignatureFilter extends OncePerRequestFilter {

    private final ValueOperations<String, String> valueOper;
    private final Cache localNonceCache;

    public SignatureFilter(ValueOperations valueOper, @Qualifier("localNonceCache") Cache localNonceCache) {
        this.valueOper = valueOper;
        this.localNonceCache = localNonceCache;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        //参数中必须携带time，随机nouce参数
        //根据请求参数生成签名，准备与客户端报文中携带签名比对
        TreeMap<String,String[]> parameters = new TreeMap<>();




    }

    /**
     * 检查参数中携带的time参数与当前时间的间隔是否超过60s
     *
     * @param time 从HttpServletRequest携带的参数中取到的time
     * @return 超过60s，返回true, 其他返回false
     */
    private boolean timeout(String time) {
        LocalDateTime validStartTime = LocalDateTime.now().minus(Constants.REQUEST_TIMEOUT_DURATION,
                ChronoUnit.SECONDS);
        //todo format time
        LocalDateTime timeL = LocalDateTime.now();
        return validStartTime.compareTo(timeL) > 0;
    }

    /**
     * 检查随机生成的nonce是否短时间内重复，在缓存中不存在的nonce将会缓存起来，且10分钟后失效
     *
     * @param nonce 从HttpServletRequest携带的参数中取到的nonce
     * @return 如果在localNonceCache中存在或者redis中存在，将会返回true，其他返回false
     */
    public boolean duplicate(String nonce) {
        String nonceKey = MessageFormat.format(Constants.NONCE_GROUP_KEY, nonce);
        Object present = this.localNonceCache.getIfPresent(nonceKey);
        if (present != null) {
            return true;
        }

        present = this.valueOper.get(nonceKey);
        String value = Constants.EMPTY_NONCE_VALUE;
        if (present == null) {
            //todo 可将此处的超时时间改为可配参数
            this.valueOper.setIfAbsent(nonceKey, value, 10, TimeUnit.MINUTES);
        }
        this.localNonceCache.put(nonceKey, value);
        return present == null;
    }

    @Override
    public void destroy() {

    }
}
