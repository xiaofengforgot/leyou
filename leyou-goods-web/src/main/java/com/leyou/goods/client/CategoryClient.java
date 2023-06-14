package com.leyou.goods.client;

import com.leyou.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: Administrator
 * @Date: 2020/1/19 16:24
 * @Description:
 */
@FeignClient("item-service")
public interface CategoryClient extends CategoryApi {
}
