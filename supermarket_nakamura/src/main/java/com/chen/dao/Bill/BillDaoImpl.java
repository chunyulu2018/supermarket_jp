package com.chen.dao.Bill;/*

 */

import com.chen.dao.BaseDao;
import com.chen.pojo.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao{

    //商品名、サンプライヤード、支払いの有無による注文総数の照会
    @Override
    public int getBillCount(Connection conn, String queryProductName, int queryProviderId, int queryIsPayment) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet  rs = null;
        int count = 0;
        if(conn != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_bill b,smbms_provider p where b.providerId = p.id");
            List<Object> paramsList = new ArrayList<>();
            if(queryProductName != "") {
                sql.append(" and productName like ?");
                paramsList.add("%"+queryProductName+"%");
            }
            if(queryProviderId != 0){
                sql.append(" and providerId = ?");
                paramsList.add(queryProviderId);
            }
            if(queryIsPayment != 0){
                sql.append(" and isPayment = ?");
                paramsList.add(queryIsPayment);
            }
            Object[] params = paramsList.toArray();
            rs = BaseDao.executeQuery(conn, sql.toString(), pstm, params, rs);
            //結果セットを巡回して結果セットから数量カウントを取り出す
            if (rs.next()){
                count = rs.getInt("count");
            }
            
            BaseDao.closeResource(null,pstm,rs);
        }
            return count;
        }


    //商品名、サンプライヤード、支払いの有無による注文リストの照会
    @Override
    public List<Bill> getBillList(Connection conn, String queryProductName, int queryProviderId, int queryIsPayment, int currentPageNo, int pageSize) throws SQLException {
        PreparedStatement pstm  = null;
        ResultSet rs = null;
        List<Bill> bills = new ArrayList<>();
        if(conn != null){
            System.out.println("enter BillDaoImpl...");
            //動的スペル文字列
            StringBuffer sql = new StringBuffer();
            //パラメータ
            ArrayList<Object> paramsList = new ArrayList<>();
            sql.append("select b.*,p.proName as proName from smbms_bill b,smbms_provider p where b.providerId = p.id");
            if(queryProductName != "" && queryProviderId != 0 && queryIsPayment != 0){
                //3つのパラメータはすべて空ではありません
                sql.append(" and productName like ? and providerId = ? and isPayment = ?");
                paramsList.add("%"+queryProductName+"%");
                try {
                    paramsList.add(queryProviderId);
                    paramsList.add(queryIsPayment);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else if(queryProductName != "" || queryProviderId != 0 || queryIsPayment != 0){
                //3つのパラメータが空ではないことを示します
                if(queryProductName != ""){
                    sql.append(" and productName like ?");
                    paramsList.add("%"+queryProductName+"%");
                }
                if(queryProviderId != 0){
                    sql.append(" and providerId = ?");
                    paramsList.add(queryProviderId);
                }
                if(queryIsPayment != 0){
                    sql.append(" and isPayment = ?");
                    paramsList.add(queryIsPayment);
                }

            }
            //データベース内の改ページでのlimit startIndexの使用、pageSizeの合計数
            //現在のページ=（現在のページ-1）*ページサイズ
            sql.append(" order by b.creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo-1)*pageSize;
            paramsList.add(currentPageNo);
            paramsList.add(pageSize);
            //sqlスプライン完了パラメータリストも正しい
            System.out.println("Test SQL --> "+sql.toString());
            //パラメータリストを変換するには
            Object[] params = paramsList.toArray();
            //sqlを実行
            rs = BaseDao.executeQuery(conn, sql.toString(), pstm, params, rs);
            //結果セットを巡回してオブジェクトをカプセル化してリストに追加する
            while (rs.next()){
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillCode(rs.getString("billCode"));
                bill.setProductName(rs.getString("productName"));
                bill.setProductDesc(rs.getString("productDesc"));
                bill.setProductUnit(rs.getString("productUnit"));
                bill.setProductCount(rs.getBigDecimal("productCount"));
                bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                bill.setIsPayment(rs.getInt("isPayment"));
                bill.setCreatedBy(rs.getInt("createdBy"));
                bill.setCreationDate(rs.getTimestamp("creationDate"));
                bill.setModifyBy(rs.getInt("modifyBy"));
                bill.setModifyDate(rs.getTimestamp("modifyDate"));
                bill.setProviderId(rs.getInt("providerId"));
                bill.setProviderName(rs.getString("proName"));
                bills.add(bill);
            }
                    BaseDao.closeResource(null,pstm,rs);
        }
                    return bills;
    }
    //添加订单
    @Override
    public boolean addBill(Connection conn, Bill bill) throws SQLException {
        PreparedStatement pstm = null;
        boolean flag  = false;
        if (conn != null){
            String sql = "insert into smbms_bill (billCode,productName,productDesc,productUnit,productCount,totalPrice,isPayment,createdBy,creationDate,modifyBy,modifyDate,providerId)values(?,?,?,?,?,?,?,?,?,?,?,?)" ;
            Object[] params = {bill.getBillCode(),bill.getProductName(),bill.getProductDesc(),bill.getProductUnit(),bill.getProductCount(),bill.getTotalPrice(),bill.getIsPayment(),bill.getCreatedBy(),bill.getCreationDate(),bill.getModifyBy(),bill.getModifyDate(),bill.getProviderId()};
            int updateRows = BaseDao.execute(conn, sql, pstm, params);
            if(updateRows > 0){
                flag = true;
            }
            BaseDao.closeResource(null,pstm,null);
        }
            return flag;
    }
    //删除订单
    @Override
    public boolean deleteBill(Connection conn, int billId) throws SQLException {
        PreparedStatement pstm = null;
        boolean flag = false;
        if(conn != null){
            String sql = "delete from smbms_bill where id = ?";
            Object[] params = {billId};
            int updateRows = BaseDao.execute(conn, sql, pstm, params);
            if(updateRows > 0){
                flag = true;
            }
            BaseDao.closeResource(null,pstm,null);
        }
            return flag;
    }
    //根据订单id 获取订单信息
    @Override
    public Bill findByBillId(Connection conn, int billId) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Bill bill = new Bill();
        if(conn != null){
            String sql = "select b.*,p.proName as providerName from smbms_bill b,smbms_provider p where b.id = ? and b.providerId = p.id";
            Object[] params = {billId};
            rs = BaseDao.executeQuery(conn, sql, pstm, params, rs);
            //遍历此结果集 并存入bill对象
            if(rs.next()){
                bill.setBillCode(rs.getString("billCode"));
                bill.setProductName(rs.getString("productName"));
                bill.setProductDesc(rs.getString("productDesc"));
                bill.setProductUnit(rs.getString("productUnit"));
                bill.setProductCount(rs.getBigDecimal("productCount"));
                bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                bill.setIsPayment(rs.getInt("isPayment"));
                bill.setCreatedBy(rs.getInt("createdBy"));
                bill.setCreationDate(rs.getTimestamp("creationDate"));
                bill.setModifyBy(rs.getInt("modifyBy"));
                bill.setModifyDate(rs.getTimestamp("modifyDate"));
                bill.setProviderId(rs.getInt("providerId"));
                bill.setProviderName(rs.getString("providerName"));
            }
            BaseDao.closeResource(null,pstm,rs);
        }
        return bill;
    }
    //修改订单信息
    @Override
    public boolean modifyBill(Connection conn, int billId, Bill bill) throws SQLException {
        PreparedStatement pstm = null;
        boolean flag = false;
        if(conn != null){
            String sql = "update smbms_bill set billCode = ?,productName =?,productDesc = ?,productUnit = ?,productCount = ? ,totalPrice = ?,isPayment = ?,createdBy = ?,creationDate = ?,modifyBy = ?,modifyDate = ?,providerId = ? where id = ?";
            Object[] params = {bill.getBillCode(),bill.getProductName(),bill.getProductDesc(),bill.getProductUnit(),bill.getProductCount(),bill.getTotalPrice(),bill.getIsPayment(),bill.getCreatedBy(),bill.getCreationDate(),bill.getModifyBy(),bill.getModifyDate(),bill.getProviderId(),billId};
            int updateRows = BaseDao.execute(conn, sql, pstm, params);
            if (updateRows > 0) {
                flag = true;
            }
            BaseDao.closeResource(null,pstm,null);
            }
            return flag;
        }

}
