package gift.controller;

import gift.dto.OrderRequest;
import gift.dto.OrderResponse;
import gift.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public ResponseEntity<OrderResponse> placeOrder(@RequestHeader("Authorization") String authorization, @RequestBody OrderRequest orderRequest) {
    try {
      String accessToken = authorization.replace("Bearer ", "");
      OrderResponse orderResponse = orderService.placeOrder(orderRequest, accessToken);
      return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    } catch (ResponseStatusException e) {
      if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
      }
      return new ResponseEntity<>(null, e.getStatusCode());
    }
  }
}