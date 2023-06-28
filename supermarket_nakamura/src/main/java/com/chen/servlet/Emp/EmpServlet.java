package com.chen.servlet.Emp;

import com.alibaba.fastjson.JSONArray;
import com.chen.pojo.Role;
import com.chen.pojo.User;
import com.chen.pojo.Emp;
import com.chen.service.Role.RoleServiceImpl;
import com.chen.service.Emp.EmpServiceImpl;
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

public class EmpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method.equals("savePwd")){
            this.savePwd(req,resp);
        }else if(method.equals("pwdmodify")){
            this.verifyPwd(req,resp);
        }else if(method.equals("query")){
            this.query(req,resp);
        }else if(method.equals("add")){
            this.add(req,resp);
        }else if(method.equals("getRoleList")){
            this.getRoleList(req,resp);
        }else if(method.equals("ifExist")){
            this.ifExist(req,resp);
        }else if(method.equals("delemp")){
            this.deleteEmp(req,resp);
        }else if(method.equals("modify")){
            this.findById(req,resp);
        }else if(method.equals("modifyexe")){
            this.modify(req,resp);
        }else if(method.equals("view")){
            this.viewEmp(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    //用户修改密码方法
    public void savePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从Session中获取ID
        Object obj = req.getSession().getAttribute(Constants.USER_SESSION);
        //获取前端页面传来的新密码
        String newpassword = req.getParameter("newpassword");
        //先判断不为空 再比较密码是否相等
        if(obj != null && newpassword != null){
            Emp emp = (Emp) obj;
            EmpServiceImpl empService = new EmpServiceImpl();
            //修改密码并返回结果
            boolean flag = empService.updatePassword(emp.getId(), newpassword);
            //如果密码修改成功 移除当前session
            if(flag){
                req.setAttribute("message","修改密码成功，请使用新密码登录!");
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else{
                req.setAttribute("message","密码修改失败 新密码不符合规范");
            }
        }else{
            req.setAttribute("message","新密码不能为空!");
        }
        //修改完了 重定向到此修改页面
        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);

    }
    
    //验证密码的方法
    public void verifyPwd(HttpServletRequest req, HttpServletResponse resp)throws  IOException{
        /*
    	//依旧从session中取ID
        Object obj = req.getSession().getAttribute(Constants.USER_SESSION);
        //取 前端传来的旧密码
        String oldpassword = req.getParameter("oldpassword");
        //将结果存放在map集合中 让Ajax使用
        Map<String, String> resultMap = new HashMap<>();
        //下面开始判断 键都是用result 此处匹配js中的Ajax代码
        if(obj == null){
            //说明session被移除了 或未登录|已注销
            resultMap.put("result","sessionerror");
        }else if(oldpassword == null){
            //前端输入的密码为空
            resultMap.put("result","error");
        }else {
            //如果旧密码与前端传来的密码相同
            if(((Emp)obj).getEmpPassword().equals(oldpassword)){
                resultMap.put("result","true");
            }else{
                //前端输入的密码和真实密码不相同
                resultMap.put("result","false");
            }
        }
        //上面已经封装好 现在需要传给Ajax 格式为json 所以我们得转换格式
        resp.setContentType("application/json");//将应用的类型变成json
        PrintWriter writer = resp.getWriter();
        //JSONArray 阿里巴巴的JSON工具类 用途就是：转换格式
        writer.write(JSONArray.toJSONString(resultMap));
        writer.flush();
        writer.close();
		*/
    }
    
    //查询用户列表的方法
    public void query(HttpServletRequest req, HttpServletResponse resp){
        //查询用户列表
            //从前端获取数据
            String queryEmpName = req.getParameter("queryName");
            String temp = req.getParameter("queryEmpRole");//值为0 、1、2、3
            String pageIndex = req.getParameter("pageIndex");
            int queryEmpRole = 0;

            //获取用户列表
            EmpServiceImpl empService = new EmpServiceImpl();
            //第一次走这个请求，一定是第一页，页面大小固定的

            List<Emp> empList = null;

            int currentPageNo = 1;//当前页码
            int pageSize = 5;//页数  可以把这个写到配置文件中，方便后期修改

            if(queryEmpName == null){
                queryEmpName = "";
            }
            if(temp!=null && !temp.equals("")){
                queryEmpRole = Integer.parseInt(temp);//给查询赋值！0,1,2,3
            }
            if(pageIndex!=null){
                currentPageNo = Integer.parseInt(pageIndex);
            }
            //获取用户总数 分页：上一页 下一页
            int totalCount = empService.getEmpCounts(queryEmpName, queryEmpRole);
            //总页数支持
            PageSupport pageSupport = new PageSupport();
                System.out.println("当前页："+currentPageNo);
            pageSupport.setCurrentPageNo(currentPageNo);
            pageSupport.setPageSize(pageSize);
                System.out.println("获取用户总数"+totalCount);
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
        System.out.println("返回UserList的数据测试"+queryEmpName+":"+queryEmpRole+":"+currentPageNo+":"+pageSize);
        //获取用户列表展示
            empList = empService.getEmpList(queryEmpName, queryEmpRole, currentPageNo, pageSize);
            //将数据传给前端
        System.out.println(empList);
//        for (User user : userList) {
//            System.out.println(user.toString());
//        }
            req.setAttribute("empList",empList);

        RoleServiceImpl roleService = new RoleServiceImpl();
        //所有角色
        List<Role> roleList = roleService.getRoleList();
        req.setAttribute("roleList",roleList);
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("currentPageNo",currentPageNo);
        req.setAttribute("totalPageCount",totalPageCount);
        req.setAttribute("queryEmpName",queryEmpName);
        req.setAttribute("queryEmpRole",queryEmpRole);

        //返回至前端
        try {
            req.getRequestDispatcher("emplist.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //添加用户方法
    public void add(HttpServletRequest req, HttpServletResponse resp)throws IOException,ServletException {
        System.out.println("进入add方法");
        //从前端获取数据
        String empCode = req.getParameter("empCode");
        String empName = req.getParameter("empName");
        String rank = req.getParameter("rank");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String mail = req.getParameter("mail");
        String IDCard = req.getParameter("IDCard");
        String picture = req.getParameter("picture");
        String address = req.getParameter("address");
        //对数据进行封装
        
        Emp emp = new Emp();
        emp.setEmpCode(empCode);
        emp.setEmpName(empName);
        emp.setRank(rank);
        emp.setAge(Integer.parseInt(age));
        if(gender.equals("男性")) {
        	emp.setGender(1);
        }
        else if(gender.equals("女性")) {
        	emp.setGender(2);
        }
        emp.setPhone(phone);
        emp.setMail(mail);
        emp.setIDCard(IDCard);
        emp.setPicture(picture);
        emp.setAddress(address);
        //注意这两个参数不在表单的填写范围内
        emp.setCreatedBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        emp.setCreationDate(new Date());

//        System.out.println("封装好的："+user.getUserCode());
        //调用service执行添加方法
        EmpServiceImpl empService = new EmpServiceImpl();
        boolean flag  = empService.addEmp(emp);
        if(flag){
            //说明执行成功 网页重定向到 用户管理页面(即 查询全部用户列表)
            resp.sendRedirect(req.getContextPath()+"/jsp/emp.do?method=query");
        }else{
            //说明 添加失败 转发到此 添加页面
            req.getRequestDispatcher("empadd.jsp").forward(req,resp);
        }
    }
    //用户管理模块中 子模块(添加用户——表单中的用户角色下拉框)
    public void getRoleList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Role> roleList = null;
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList1 = roleService.getRoleList();
        //把roleList1 转换为json对象输出
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.write(JSONArray.toJSONString(roleList1));
        out.flush();
        out.close();
    }
    //用户管理模块 子模块(验证用户コード是否已经存在)
    public void ifExist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取前端输入 的用户コード
        String empCode = req.getParameter("empCode");
        //将结果存放在map集合中 让Ajax使用
        Map<String, String> resultMap = new HashMap<>();
        if(empCode == null || empCode.equals("")){
            System.out.println("前端未填写用户コード...");
            resultMap.put("empCode","NoWrite");
        }else{
            System.out.println("前端填写了用户コード...");
            EmpServiceImpl empService = new EmpServiceImpl();
            Emp isNullEmp = empService.login(empCode, "");

            //判断是否已经存在这个用户コード
            boolean flag = isNullEmp != null ? true : false;
            if(flag){
                //用户コード存在
                //将信息存入map中
                resultMap.put("empCode","exist");
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
    //用户管理模块中的子模块 —— 删除用户
    public void deleteEmp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //从前端获取 要删除的用户 的信息
        String empid = req.getParameter("eid");
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
            EmpServiceImpl empService = new EmpServiceImpl();
            if(empService.deleteEmp(delId)){
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
    //用户管理模块中的功能 —— 根据id查询用户信息
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从前端获取 要修改的用户 的id
        String eId = req.getParameter("eid");
        int empId = 0;
        try {
            empId = Integer.parseInt(eId);
        }catch (Exception e){
            e.printStackTrace();
        }
        EmpServiceImpl empService = new EmpServiceImpl();
        //查询要更改的用户信息
        Emp emp = empService.findById(empId);
        //将用户信息保存至 request中 让usermodify.jsp显示
        req.setAttribute("emp",emp);
        req.getRequestDispatcher("empmodify.jsp").forward(req,resp);
    }
    //用户管理模块中的子模块 —— 更改用户信息
    public void modify(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //从前端获取 要修改的用户 的id
        String eId = req.getParameter("empid");
        int empId = 0;
        try {
            empId = Integer.parseInt(eId);
        }catch (Exception e){
            e.printStackTrace();
        }
        //从前端获取サプライヤ信息
        
        //int id = Integer.parseInt(req.getParameter("id"));
        String empCode = req.getParameter("empCode");
        String empName = req.getParameter("empName");
        String rank = req.getParameter("rank");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String mail = req.getParameter("mail");
        String IDCard = req.getParameter("IDCard");
        String picture = req.getParameter("picture");
        String address = req.getParameter("address");
        //封装成一个对象
        Emp emp = new Emp();
        emp.setEmpCode(empCode);
        emp.setEmpName(empName);
        emp.setRank(rank);
        emp.setAge(Integer.parseInt(age));
        if(gender.equals("男性")) {
        	emp.setGender(1);
        }
        else if(gender.equals("女性")) {
        	emp.setGender(2);
        }
        emp.setPhone(phone);
        emp.setMail(mail);
        emp.setIDCard(IDCard);
        emp.setPicture(picture);
        emp.setAddress(address);
        //下面的参数不是由前端传来的
        emp.setModifyDate(new Date());
        emp.setModifyBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());

        EmpServiceImpl empService = new EmpServiceImpl();
        if(empService.modify(empId,emp)){
            //如果执行成功了 网页重定向到 用户管理页面(即 查询全部用户列表)
            resp.sendRedirect(req.getContextPath()+"/jsp/emp.do?method=query");
        }else{
            //说明 添加失败 转发到此 添加页面
            req.getRequestDispatcher("empmodify.jsp").forward(req,resp);
        }
    }
    //用户管理模块中的子模块 —— 查询用户信息
    public void viewEmp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从前端获取 要查询用户 的id
        String id = req.getParameter("eid");
        int empId = 0;
        try {
            empId = Integer.parseInt(id);
        }catch (Exception e){
            e.printStackTrace();
            empId = 0;
        }
        //调用 根据id查询用户信息的方法
        EmpServiceImpl empService = new EmpServiceImpl();
        Emp emp = empService.findById(empId);
        //将此user发送到展示前端 的页面进行展示
        req.setAttribute("emp",emp);
        //跳转到前端 的展示页面
        req.getRequestDispatcher("empview.jsp").forward(req,resp);
    }
}
