package com.leyou.search.repository;

import com.leyou.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: Administrator
 * @Date: 2020/1/19 21:18
 * @Description:
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}
