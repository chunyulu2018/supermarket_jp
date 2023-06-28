package com.chen.dao.Rank;

import com.chen.pojo.Rank;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
public interface RankDao {
    //获取会员等级
    public abstract List<Rank> getRankList(Connection conn)throws SQLException;
}
