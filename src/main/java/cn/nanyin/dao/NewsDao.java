package cn.nanyin.dao;

import cn.nanyin.model.News;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ���� on 2015/7/13.
 */
@Repository
@Transactional
public class NewsDao {


    public List<News> getNewsList(Integer start,Integer max)
    {

        return null;
    }
}
