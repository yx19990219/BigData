package cn.linter.oasys.service;

import cn.linter.oasys.entity.Goods;
import com.github.pagehelper.PageInfo;

public interface GoodsService {
    Goods getGoodsById(int id);

    PageInfo<?> getGoods(int pageNumber, int pageSize, String tagid, String manufacturerPartNumber
            , String description, String stockQty);

    int updateGoods(Goods goods);

    int addGoods(Goods goods);

    void deleteGoods(Integer[] ids);
}
