package study;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@Service
class TransactionService {
  @PersistenceContext
  private EntityManager em;

  public void fistTransaction() {
    System.out.println("First transaction");
    System.out.println(em.getDelegate());
    secondTransaction();
  }

  @Transactional
  public void secondTransaction() {
    System.out.println("Second transaction");
    System.out.println(em.getDelegate());
  }
}

@SpringBootTest
public class TransactionTest {
  @Autowired
  private TransactionService transactionService;

  @Test
  void fistTransaction() {
    transactionService.fistTransaction();
  }
}