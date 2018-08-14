package com.qiao.aspect;
import com.qiao.dao.LogDao;
import com.qiao.entity.LogBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*为service包添加日志，若想用@Aspect注解现在spring-config.xml中配置一下
* 因为要记录日志，所以要有一个日志dao层即：LogDao，方法就是为了添加个日志
* 在去创建实现类，然后把日志封装成一个对象，建一个实体类，接着创建一个日志表在数据库中
* 接下来就是在这个类中写*/
@Component
@Aspect
public class LogAspect {
    @Autowired
    LogDao logDao;
    //创建切入点，对service包及其子包添加日志
    @Pointcut("execution(* com.qiao.service.*.*(..))")
    public  void   log(){}

      /* @Before(value = "text(flower)")
   public void start(String flower){
        System.out.println("方法开始执行  flower:"+flower );
    }
    @AfterReturning(value = "text(flower)",returning = "product")
    public void after_returning(String flower, Product product){
        System.out.println("====after_returning====");
        System.out.println("product="+product);
    }

    @AfterThrowing(value = "text(flower)",throwing = "ex")
    public void throwing(String flower,Throwable ex){
        System.out.println("方法抛出异常  ex="+ex.getMessage());
    }
    @After("text(flower)")
    public void finish(String flower){
        System.out.println("方法执行完成");
    }
*/



//环绕通知
   @Around(value = "log()")
    public  Object  around(ProceedingJoinPoint proceedingJoinPoint){
     /*环绕通知中的ProceedingJoinPoint是将参数都封装到这里了*/
        System.out.println("====环绕通知====");
        String className= proceedingJoinPoint.getTarget().getClass().getName();//获取类名
        String methodName=proceedingJoinPoint.getSignature().getName();  //获取方法名
        Object o=null;
        try {
           o=proceedingJoinPoint.proceed();//调用业务逻辑层目标对象的方法
            LogBean logBean=new LogBean(1,className+methodName);
            logDao.add(logBean);
        } catch (Throwable throwable) {
            throwable.printStackTrace();

        }
        return o;
    }

}
