package com.leyou.auth.client;

import com.leyou.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: Administrator
 * @Date: 2020/2/12 15:27
 * @Description:
 */
@FeignClient("user-service")
public interface UserClient extends UserApi {
}
