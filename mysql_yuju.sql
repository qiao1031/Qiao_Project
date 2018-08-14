使用两种方式查询员工（emp）信息
select  * from  emp;
select empno ,ename,job,mgr,hiredate,sal,comm,deptno from emp \G;
员工转正后，月薪上调20%，查询出所有员工转正后的月薪
select  sal*1.2 from emp;
员工试用期6个月，转正后月薪上调20%，请查询出所有员工工作第一年的年薪所得（不考虑奖金部分，年薪的试用期6个月的月薪+转正后6个月的月薪）
select  (sal*6)+(sal*1.2*6) "年薪" from emp;//“年新”是起的别名
#起别名
select empno as "员工编号",ename "员工姓名",job ,mgr,hiredate,sal,comm,deptno from emp;
员工试用期6个月，转正后月薪上调20%，请查询出所有员工工作第一年的年薪所得（考虑奖金部分），要求显示列标题为员工姓名，工资收入，奖金收入，总收入）
select ename "员工姓名",sal*6+sal*1.2*6 "工资收入",comm*12 "奖金收入",(sal*6+sal*1.2*6)+ifnull(comm,0)!=0 comm*12 "总收入" from emp;
       注意，这个语句，查询出来总收入是null的，因为无论什么乘以null结果都为null，处理方案，利用函数将null转为0   ifnull(e.comm,0)!=0

查询员工表中一共有几种岗位类型，有重复的，加上disinct
select  distinct job  from emp;
查询部门为10的所有员工信息
select  *  from  emp  where deptno=10;
查询岗位是clerk的所有员工信息
select  *  from  emp  where job='clerk';
查询岗位是salesman（售货员）的所有员工的员工编号，职位，入职日期
select empno,job,hiredate from  emp  where job='salesman';
查询1985年12月31号之前入职的员工姓名及入职日期
select ename,hiredate from emp  where hiredate<'1985-12-31';
查询部门编号不再10部门的员工姓名，部门编号
select  ename,deptno from emp where deptno<>10;
查询入职日期在82年到85年的员工姓名，入职日期
select ename,hiredate from  emp  where hiredate between  '1982-01-01'  and '1985-12-31';
查询月薪在3000到5000的员工姓名，月薪
select ename,sal from emp where sal between 3000 and 5000;
查询部门编号为10或者20的员工姓名，部门编号
select  ename,deptno from emp  where deptno in (10,20);
查询经理编号为7902，7566，7788的员工姓名，经理编号

LIKE 运算符 %代表0或任意更多的字符
查询奖金为空的员工信息
select  * from  emp where  comm is null;
查询员工姓名一w开头的员工姓名
select ename from emp where ename like 'W%';
查询员工姓名倒数第二个字符为T的员工姓名
select ename from emp where ename like '%t_';
查询奖金为空的员工姓名，奖金
SELECT ename,comm from  emp where comm is null;
查询工资查超过2000并且职位是MANAGER，或者职位是SALESMAN的员工姓名，职位，工资
select  ename,job,sal from  emp where  (sal >2000 and job='manager') or job='salesman';
查询工资查超过2000并且职位是MANAGER或SALESMAN的员工姓名，职位，工资
select  ename,job,sal from  emp where  sal >2000 and (job='manager' or job='salesman');
查询部门编号在10或者20，并且工资在3000到5000之间的员工姓名，部门，工资
select  ename,deptno,sal from emp where deptno in(10,20) and  sal between 3000 and 5000;
查询日志日期在81年，并且职位不是SALES开头的员工姓名，入职日期，职位
select ename,hiredate,job from emp where  (hiredate between '1981-01-01' and '1981-12-31') and job not like 'sales%';
查询职位为SALESMAN或者MANAGER,部门编号为10 或者20，姓名，包括 岗位和部门编号
select  ename,job,deptno from emp where job in('salesman','manager')  and  deptno in(10,20) and  ename like '%A%';

ORDER BY  排序语句
查询部门在20到30 的员工姓名，部门编号，并按照工资升序排序
   select ename,deptno,sal from emp where deptno in(20,30) order by sal asc;
查询工资在2000到3000之间，部门不再10号的员工姓名，部门编号，工资，并按照部门升序，工资降序的排序
   select ename,deptno,sal from emp where (sal between 2000 and 3000 ) and (deptno!=10) order by sal desc;
查询入职日期在80年到83年之间，指纹以SALES或者MAN开头的员工姓名，入职日期，职位，并按照日志日期降序排序
   select ename,hiredate,job from emp where (hiredate between '1980-01-01' and '1983-12-31' )
   and( job like 'sales%' or job like 'man%' )
   order by  hiredate desc;


   # 查询员工表第3-5条记录
   select  * from emp  limit 2,3;
   # 查询员工表前5条记录
   select  * from emp  limit  0,5;
   # 查询第1页的数据，查3条    0 ,3
    select * from  emp  limit  0,3;
    #查询第2页的数据，查3条    4,3
   select * from  emp  limit  3,3 ;
    #查询第pageNo页的数据，查pageSize条
     select * from  emp  limit  (pageNo-1)*pageSize,pageSize;
查询入职日期最早的前5名与昂共姓名，日志日期
     select ename,hiredate from emp   order by hiredate limit 0,5;
查询工作在CHICAGO并且入职日期最早的两名员工，入职日期 （需要做表的关联）

MySQL的函数

显示所有员工的前三个字符  截取字符转用substring（）函数
     select  substring(ename,1,3)  from emp;
#显示正好为5个字符的员工名字，工资，部门号
     select ename,sal,deptno from emp where   length(ename)=5;
#查询服务器的当前时间  格式为：  2018年7月2号 10:55:58
     select date_format(now(),'%Y年%m月%d号 %H:%i:%s');
 查询部门10，20的员工截至到2000年1月1日，工作了多少个月，入职月份   （不会写）
   （有错） select  datefiff( 2000, year(hiredate)+1) from emp;

    #多表关联
  查询员工姓名，部门名称，工作地点
    select  ename,dname,loc from emp
    join  dept
    on  emp.deptno=dept.deptno ;
  查询员工姓名，部门名称，工作地点（其别名的方式）（99语法）
    select  e.ename,d.dname,d.loc from emp e
    join  dept d
    on  e.deptno=d.deptno ;
     查询部门在new york的员工编号、姓名、部门名称  （92语法）
    select  ename,dname,loc from  emp, dept
    where emp.deptno=dept.deptno;
  查询部门在new york的员工编号、姓名、部门名称   （99语法）
    select  empno,ename,dname from  emp
    join dept
    on emp.deptno=dept.deptno
    where loc='NEW YORK';
   查询部门在new york的员工编号、姓名、部门名称（92语法）
    SELECT empno,ename,dname from  emp,dept
    where emp.deptno=dept.deptno and loc='NEW YORK';
查询部门在new york的员工编号、姓名、部门名称（92语法）（起别名）
      SELECT e.empno,e.ename,d.dname from  emp e,dept  d
    where e.deptno=d.deptno and d.loc='NEW YORK';
查询工作在CHICAGO并且并且奖金不为空的与昂共姓名，工作地点，奖金
     select  e.ename,d.loc,e.comm from  emp e
     join  dept d
     on  e.deptno=d.deptno
     where d.loc='CHICAGO' AND  e.comm is not null;

      查询工作在CHICAGO并且并且奖金不为空的与昂共姓名，工作地点，奖金  （做一个奖金是不是空判断，是空就转成0）
     select  e.ename,d.loc,e.comm from  emp e
     join  dept d
     on  e.deptno=d.deptno
     where d.loc='CHICAGO' AND  ifnull(e.comm,0)!=0 ;

查询所有姓名中含有A字符的与昂共姓名，工作地点
    select e.ename,d.loc from emp e
    join dept d
    on e.deptno=d.deptno
    where  e.ename like '%A%';

    SELECT  e.ename "员工姓名",e.sal "薪资",s.grade "薪资等级" from   emp e
    join salgrade "工资等级表" s
    on e.sal between  s.losal "最低工资" and  s.hisal "最高工资" ;
查询每个员工的编号，姓名，工资等级，所在工作城市，按照工资等级进行升序排序
    select  e.empno,e.ename,e.sal,s.grade,d.loc from emp e
    join salgrade s on  e.sal between s.losal and s.hisal
    join dept d  on  e.deptno=d.deptno
    order by s.grade asc;

查询每个员工的姓名和直接上级姓名（自身连接）
    select  e.ename, m.ename from  emp e   //就员工表对于员工来所就叫员工表
    join emp m   //就员工表对于领导来说叫领导表， 其实都是同一个表，就不同职位区分起的别名
    on  e.mgr=m.empno;//员工的领导编号=领导表的员工编号
查询所有工作在NEW YORK 和CHICAGO的员工姓名，员工编号，以及他们的经理姓名，经理编号
    select  e.ename,e.empno,m.ename,m.empno from  emp e
    join emp m
    on  e.mgr=m.empno
    join dept d
    on e.deptno=d.deptno
    where  d.loc in ('NEW YORK','CHICAGO');

左外连接
查询所有雇员的姓名吗部门编号吗部门名称，包括没有部门的员工也要显示出来（实习生）
    select e.ename,d.deptno,.e.dname from emp e
    left join dept d
    on e.deptno = d.deptno;
    右外连接
    查询所有雇员姓名，部门编号，部门名称，包括没有员工的部门也要显示出来 （刚成立的部门，目前还没招到员工）
   select e.ename,e.deptno,d.dname from emp e
   right join dept d
   on e.deptno=d.deptno;


交叉连接的结果和笛卡尔积的结果一样
    #创建一个员工表和部门表的交叉连接  （笛卡尔积）
    select e.ename,d.dnam e,m.ename from emp e
    join dept d on e.deptno = d.deptno
    join emp m on e.mgr = m.empno
    where e.ename='SMITH';
     #创建一个员工表和部门表的交叉连接  （交叉连接）
    select e.ename,d.dname,m.ename from emp e
    cross join dept d on
    #使用自然连接，  显示入职日期在80年5月1日以后的员工姓名，部门编号，工作地点。
    select e.ename,d.dname,e.sal,s.grade from emp e
    join dept d on e.deptno = d.deptno
    join salgrade s on e.sal between losal and hisal
    where grade>4;
    #使用USING，显示工作在CHICAGO的员工姓名，部门编号，工作地点。
    select m.ename '领导',e.ename from emp e
    join dept d on e.mgr = m.empno
    where  m.ename = 'KING'or m.ename = 'FORD'
    #使用NO语句，显示工作在CHICAGO的员工姓名，部门编号，工作地点，薪资等级。

    #使用左连接，查询每个员工的姓名，经理姓名，没有经理的King也要显示出来。

    #使用右连接，查询每个员工的姓名，经理姓名，没有经理的King也要显示出来。

    #显示员工的SMITH的姓名，部门名称，直接上级名称。
     select e.ename,d.dnam e,m.ename from emp e
    join dept d on e.deptno = d.deptno
    join emp m on e.mgr = m.empno
    where e.ename='SMITH';
    #显示员工姓名，部门名称，工资，工资级别，要求工资级别大于4级
     SELECT  e.ename,d.dname,e.sal,s.grade
     FROM  emp e join dept d
     ON e.deptno=d.deptno
     JOIN salgrade s
     ON  e.sal between  s.losal  AND  s.hisal
     WHERE  s.grade>4;
    #显示员工KING和FORD管理的员工姓名及其经理姓名。
     SELECT e.ename,m.ename FROM  emp e
     JOIN  emp m
     ON  e.mgr=m.empno
     WHERE  m.ename IN ('KING','FORD');
    #显示员工姓名，参加工作时间，经理名，参加工作时间，要求参见时间比经理早。
     SELECT  e.ename '员工名字',e.hiredate,m.ename '领导名字',m.hiredate from emp e
     JOIN  emp m
     ON  e.mgr=m.empno
     WHERE  e.hiredate<m.hiredate;


分组函数
查询入职日期最早和最晚的日期            (没有用分组，所以返回的就一行记录)
select min(hiredate),max(hiredate) from emp;
查询职位已SALES开头的所有员工平均工资，最低工资，最高工资。工资总和
select  avg(sal)'平均工资'MIN (sal)'最低工资'，max(sal)'最高工资'sun(sal)'工资总和'  FROM  emp
WHERE  job like  'SALSE%';
在员工表中一共有多少个员工
SELECT   count(empno)  from emp;
查询部门30一共有多少个员工
select count(empno)  from  emp  WHERE   deptno=30;
查询有多少员工领取奖金
SELECT  COUNT (common)  from  emp;
在表中查询有多少个部门（不能重复）
SELECT  count(DISTINCT deptno) from emp;
查询领取奖金的人         若奖金为null就转成0
SELECT  ifnull(comm,0) from emp;
查询平均奖金
SELECT  avg(ifnull(comm,0)) from emp;
