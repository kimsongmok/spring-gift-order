package gift.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders") // 테이블 이름을 "orders"로 변경하여 예약어 문제 해결
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String message;
  private Long optionId;
  private Integer quantity;
  private LocalDateTime orderDateTime;

  // 생성자
  public Order(Long optionId, Integer quantity, String message) {
    this.optionId = optionId;
    this.quantity = quantity;
    this.message = message;
    this.orderDateTime = LocalDateTime.now();
  }

  // Getter 메서드
  public Long getId() {
    return id;
  }

  public String getMessage() {
    return message;
  }

  public Long getOptionId() {
    return optionId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public LocalDateTime getOrderDateTime() {
    return orderDateTime;
  }

  // 필요한 경우 필드 값을 변경할 수 있는 메서드 추가
  public void updateMessage(String message) {
    this.message = message;
  }
}