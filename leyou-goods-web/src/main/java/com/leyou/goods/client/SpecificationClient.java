package com.leyou.goods.client;

import com.leyou.item.api.SpecificationApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: Administrator
 * @Date: 2020/1/19 16:25
 * @Description:
 */
@FeignClient("item-service")
public interface SpecificationClient extends SpecificationApi {
}
