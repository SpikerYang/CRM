package testAOP;


import org.springframework.stereotype.Component;

@Component
public class AccountDao {

    public void addAccount() {
        System.out.println(getClass() + ": Adding Account");
    }
}
