package com.chen.service.Rank;

import com.chen.dao.BaseDao;
import com.chen.dao.Rank.RankDao;
import com.chen.dao.Rank.RankDaoImpl;
import com.chen.pojo.Rank;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RankServiceImpl implements RankService {
    //业务层调用持久层
    private RankDao rankDao = null;
    public RankServiceImpl(){
        this.rankDao =new RankDaoImpl();
    }
    @Override
    public List<Rank> getRankList()  {
        Connection conn = null;
        List<Rank> rankList = null;
        try {
            //获取数据库连接
            conn = BaseDao.getConnection();
            rankList = rankDao.getRankList(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(conn,null,null);
            return rankList;
        }
    }
    @Test
    public void testGetRankList(){
        RankServiceImpl rankService = new RankServiceImpl();
        List<Rank> rankList = rankService.getRankList();
        for (Rank rank : rankList) {
            System.out.println(rank.getRankName());
        }
    }
}
