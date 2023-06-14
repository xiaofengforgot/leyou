package com.leyou.search.client;

import com.leyou.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: Administrator
 * @Date: 2020/1/19 16:22
 * @Description:
 */
@FeignClient("item-service")
public interface BrandClient extends BrandApi {
}
