package com.leyou.cart.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: Administrator
 * @Date: 2020/2/14 17:49
 * @Description:
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
