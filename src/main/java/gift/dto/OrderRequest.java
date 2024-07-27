package gift.dto;

public class OrderRequest {
  private final Long optionId;
  private final Integer quantity;
  private final String message;

  public OrderRequest(Long optionId, Integer quantity, String message) {
    this.optionId = optionId;
    this.quantity = quantity;
    this.message = message;
  }

  public Long getOptionId() {
    return optionId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public String getMessage() {
    return message;
  }
}