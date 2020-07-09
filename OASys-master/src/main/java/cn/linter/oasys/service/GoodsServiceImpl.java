package cn.linter.oasys.service;

import cn.linter.oasys.entity.Goods;
import cn.linter.oasys.mapper.GoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
public class GoodsServiceImpl implements GoodsService{
    private final GoodsMapper goodsMapper;
    @Autowired
    public GoodsServiceImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }
    @Override
    public Goods getGoodsById(int id) {
        return goodsMapper.selectGoodsByID(id);
    }
    public static String[] arr = new String[]{"id","tagid", "component type","sub-type","manufacturer",
            "manufacturer part number", "description","stock qty","annual stock","auto replenish rate",
            "lead time"};
    @Override
    public PageInfo<?> getGoods(int pageNumber, int pageSize, String tagid, String manufacturerPartNumber, String description, String stockQty) {
        PageHelper.startPage(pageNumber, pageSize);
        HashMap<String,Object> m = new HashMap<>();
        m.put("pageNumber",pageNumber);
        m.put("pageSize",pageSize);
        m.put("tagid",tagid);
        m.put("manufacturerPartNumber",manufacturerPartNumber);
        m.put("description",description);
        m.put("stockQty",stockQty);
        return new PageInfo<>(goodsMapper.selectGoods(m));
    }

    @Override
    public int updateGoods(Goods goods) {
        return goodsMapper.updateGoods(goods);
    }

    @Override
    public int addGoods(Goods goods) {
        return goodsMapper.insertGoods(goods);
    }

    @Override
    public void deleteGoods(Integer[] ids) {
        goodsMapper.deleteGoods(ids);
    }
}
