package com.leyou.search.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: Administrator
 * @Date: 2020/1/19 16:00
 * @Description:
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {

}
