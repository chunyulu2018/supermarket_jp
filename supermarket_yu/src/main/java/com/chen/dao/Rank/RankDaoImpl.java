package com.chen.dao.Rank;

import com.chen.dao.BaseDao;
import com.chen.pojo.Rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RankDaoImpl implements RankDao {
    //会員ランクを取得
    @Override
    public List<Rank> getRankList(Connection conn) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs  = null;
        ArrayList<Rank> ranks = new ArrayList<>();
        if(conn != null){
            String sql  = "select * from smbms_rank";
            Object[] params = {};
            rs = BaseDao.executeQuery(conn, sql, pstm, params, rs);
            while(rs.next()){
                Rank rank = new Rank();
                rank.setId(rs.getInt("id"));
                rank.setRankName(rs.getString("rankName"));
                rank.setRankCode(rs.getString("rankCode"));
                ranks.add(rank);
            }
        }
        BaseDao.closeResource(null,pstm,rs);
        return ranks;
    }
}
