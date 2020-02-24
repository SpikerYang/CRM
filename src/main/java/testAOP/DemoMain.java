package testAOP;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoMain {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from spring container
        AccountDao accountDao = context.getBean("accountDao", AccountDao.class);

        // call the business method
        accountDao.addAccount();

        // close the context
        context.close();

    }
}
