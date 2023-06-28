package com.chen.servlet.Employee;

import com.alibaba.fastjson.JSONArray;
import com.chen.pojo.Rank;
import com.chen.pojo.User;
import com.chen.pojo.Employee;
import com.chen.service.Rank.RankServiceImpl;
import com.chen.service.Employee.EmployeeServiceImpl;
import com.chen.util.Constants;
import com.chen.util.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//实现Servlet复用
public class EmployeeServlet extends HttpServlet {
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String method = req.getParameter("method");
		 	if(method.equals("query")){
	            this.query(req,resp);
	        }else if(method.equals("add")){
	            this.add(req,resp);
	        }else if(method.equals("delemployee")){
	            this.deleteEmployee(req,resp);
	        }else if(method.equals("getRankList")){
	            this.getRankList(req,resp);
	        }else if(method.equals("ifExist")){
	            this.ifExist(req,resp);
	        }else if(method.equals("modify")){
	            this.findById(req,resp);
	        }else if(method.equals("modifyexe")){
	            this.modify(req,resp);
	        }else if(method.equals("view")){
	            this.viewEmployee(req,resp);
	        }
	 }
	 	//子模块(验证用户コード是否已经存在)
	 	@Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        doGet(req, resp);
	    }
	    public void ifExist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	        //获取前端输入 的用户コード
	        String userCode = req.getParameter("userCode");
	        //将结果存放在map集合中 让Ajax使用
	        Map<String, String> resultMap = new HashMap<>();
	        if(userCode == null || userCode.equals("")){
	            System.out.println("前端未填写用户コード...");
	            resultMap.put("userCode","NoWrite");
	        }else{
	            System.out.println("前端填写了用户コード...");
	            EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
	            int isNullUser = employeeService.getEmpCounts(userCode);

	            //判断是否已经存在这个用户コード
	            boolean flag = isNullUser != 0 ? true : false;
	            if(flag){
	                //用户コード存在
	                //将信息存入map中
	                resultMap.put("userCode","exist");
	            }
	        }
	        //上面已经封装好 现在需要传给Ajax 格式为json 所以我们得转换格式
	        resp.setContentType("application/json");//将应用的类型变成json
	        PrintWriter writer = resp.getWriter();
	        //JSONArray 阿里巴巴的JSON工具类 用途就是：转换格式
	        writer.write(JSONArray.toJSONString(resultMap));
	        writer.flush();
	        writer.close();

	    }
	    
	 //查询社員列表的方法
    public void query(HttpServletRequest req, HttpServletResponse resp){
        //查询社員列表
            //从前端获取数据
            String queryUserName = req.getParameter("queryUserName");
            if(queryUserName != null) {
            	 queryUserName = req.getParameter("queryUserName").trim();
            }
            String temp = req.getParameter("queryRank");//值为0、1、2、3
            String pageIndex = req.getParameter("pageIndex");
            int queryRank = 0;

            //获取社員列表
            EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
            //第一次走这个请求，一定是第一页，页面大小固定的

            List<Employee> employeeList = null;

            int currentPageNo = 1;//当前页码
            int pageSize = 5;//页数  可以把这个写到配置文件中，方便后期修改

            if(queryUserName == null){
                queryUserName = "";
            }
            if(temp!=null && !temp.equals("")){
                queryRank = Integer.parseInt(temp);//给查询赋值！0,1,2,3
            }
            if(pageIndex!=null){
                currentPageNo = Integer.parseInt(pageIndex);
            }
            //获取社員总数 分页：上一页 下一页
            int totalCount = employeeService.getEmployeeCounts(queryUserName, queryRank);
            //总页数支持
            PageSupport pageSupport = new PageSupport();
                System.out.println("当前页："+currentPageNo);
            pageSupport.setCurrentPageNo(currentPageNo);
            pageSupport.setPageSize(pageSize);
                System.out.println("获取社員总数"+totalCount);
            pageSupport.setTotalCount(totalCount);
            //总共的页数
            int totalPageCount = pageSupport.getTotalPageCount();
            //控制首页和尾页
            //如果页数小于1，就显示第一页  页数大于 最后一页就 显示最后一页
            if(currentPageNo<1){
                currentPageNo =1;
            }else if(currentPageNo>totalPageCount){//当前页面大于了最后一页
                currentPageNo = totalPageCount;
            }
        System.out.println("返回EmployeeList的数据测试"+queryUserName+":"+queryRank+":"+currentPageNo+":"+pageSize);
        	//获取社員列表展示
            employeeList = employeeService.getEmployeeList(queryUserName, queryRank, currentPageNo, pageSize);
            //将数据传给前端
        System.out.println(employeeList);
//        for (Employee employee : employeeList) {
//            System.out.println(employee.toString());
//        }
            req.setAttribute("employeeList",employeeList);

        RankServiceImpl rankService = new RankServiceImpl();
        //所有角色
        List<Rank> rankList = rankService.getRankList();
        req.setAttribute("rankList",rankList);
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("currentPageNo",currentPageNo);
        req.setAttribute("totalPageCount",totalPageCount);
        req.setAttribute("queryUserName",queryUserName);
        req.setAttribute("queryRank",queryRank);

        //返回至前端
        try {
            req.getRequestDispatcher("employeelist.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //添加会員ランク下拉框
    public void getRankList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	List<Rank> rankList = null;
    	RankServiceImpl rankService = new RankServiceImpl();
        List<Rank> rankList1 = rankService.getRankList();
        //把rankList1 转换为json对象输出
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.write(JSONArray.toJSONString(rankList1));
        out.flush();
        out.close();
    }
    
    //增加员工
    public void add(HttpServletRequest req, HttpServletResponse resp)throws IOException,ServletException {
        System.out.println("进入add方法");
        //从前端获取数据
        //String addId = req.getParameter("id");
        String addUserCode = req.getParameter("userCode");
    //  System.out.println("\n前端输入的："+addUserCode+"\n");
        String addUserName = req.getParameter("userName");
        String addRank = req.getParameter("rank");
        String addAge = req.getParameter("age");
        String addGender = req.getParameter("gender");
        String addPhone = req.getParameter("phone");
        String addAddress = req.getParameter("address");
        String addMailAddress = req.getParameter("mailAddress");
        String addUserId = req.getParameter("userId");
        //对数据进行封装
        Employee employee = new Employee();
        //employee.setId(Integer.parseInt(addId));
        employee.setUserCode(addUserCode);
        employee.setUserName(addUserName);
        employee.setRank(Integer.parseInt(addRank));
        employee.setAge(Integer.parseInt(addAge));
        employee.setGender(Integer.parseInt(addGender));
        employee.setPhone(addPhone);
        employee.setAddress(addAddress);
        employee.setMailAddress(addMailAddress);
        employee.setUserId(addUserId);
        //注意这两个参数不在表单的填写范围内
        employee.setCreatedBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        employee.setCreateDate(new Date());
        //调用service执行添加方法
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        boolean flag  = employeeService.addEmployee(employee);
        if(flag){
            //说明执行成功 网页重定向到 用户管理页面(即 查询全部用户列表)
            resp.sendRedirect(req.getContextPath()+"/jsp/employee.do?method=query");
        }else{
            //说明 添加失败 转发到此 添加页面
            req.getRequestDispatcher("employeeadd.jsp").forward(req,resp);
        }
    }
    
    //删除员工
    public void deleteEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //从前端获取 要删除的用户 的信息
        String empid = req.getParameter("empid");
        int delId = 0;
        //先转换
        try {
            delId= Integer.parseInt(empid);
        }catch (Exception e){
            e.printStackTrace();
            delId = 0;
        }
        //将结果存放在map集合中 让Ajax使用
        Map<String, String> resultMap = new HashMap<>();
        if(delId<=0){
            resultMap.put("delResult","notexist");
        }else {
            EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
            if(employeeService.deleteEmployee(delId)){
                resultMap.put("delResult","true");
            }else {
                resultMap.put("delResult", "false");
            }
        }
        //上面已经封装好 现在需要传给Ajax 格式为json 所以我们得转换格式
        resp.setContentType("application/json");//将应用的类型变成json
        PrintWriter writer = resp.getWriter();
        //JSONArray 阿里巴巴的JSON工具类 用途就是：转换格式
        writer.write(JSONArray.toJSONString(resultMap));
        writer.flush();
        writer.close();
    }
    
    //id查询
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从前端获取 要修改的用户 的id
        String empid = req.getParameter("empid");
        int userId = 0;
        try {
            userId = Integer.parseInt(empid);
        }catch (Exception e){
            e.printStackTrace();
        }
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        //查询要更改的用户信息
        Employee employee = employeeService.findById(userId);
        //将用户信息保存至 request中 让employeemodify.jsp显示
        req.setAttribute("employee",employee);
        req.getRequestDispatcher("employeemodify.jsp").forward(req,resp);
    }
    
    //修改
    public void modify(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //从前端获取 要修改的用户 的id
        String empid = req.getParameter("empid");
        int userId = 0;
        try {
            userId = Integer.parseInt(empid);
        }catch (Exception e){
            e.printStackTrace();
        }
        //从修改信息的表单中封装信息
        Employee employee = new Employee();
        //employee.setUserCode(req.getParameter("userCode"));
        employee.setUserName(req.getParameter("userName"));
        employee.setRank(Integer.parseInt(req.getParameter("rank")));
        employee.setAge(Integer.parseInt(req.getParameter("age")));
        employee.setGender(Integer.parseInt(req.getParameter("gender")));
        employee.setPhone(req.getParameter("phone"));
        employee.setAddress(req.getParameter("address"));
        employee.setMailAddress(req.getParameter("mailAddress"));
        employee.setUserId(req.getParameter("userId")); 
         //注意这两个参数不在表单的填写范围内
        employee.setModifyBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        employee.setModifyDate(new Date());

        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        if(employeeService.modify(userId,employee)){
            //如果执行成功了 网页重定向到 用户管理页面(即 查询全部用户列表)
            resp.sendRedirect(req.getContextPath()+"/jsp/employee.do?method=query");
        }else{
            //说明 添加失败 转发到此 添加页面
        	System.out.println("添加失败");
            req.getRequestDispatcher("employeemodify.jsp").forward(req,resp);
        }
    }
    
    //查询
    public void viewEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从前端获取 要查询用户 的id
        String empid = req.getParameter("empid");
        int userId = 0;
        try {
            userId = Integer.parseInt(empid);
        }catch (Exception e){
            e.printStackTrace();
            userId = 0;
        }
        //调用 根据id查询用户信息的方法
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        Employee employee = employeeService.findById(userId);
        //将此user发送到展示前端 的页面进行展示
        req.setAttribute("employee",employee);
        //跳转到前端 的展示页面
        req.getRequestDispatcher("employeeview.jsp").forward(req,resp);
    }
}
