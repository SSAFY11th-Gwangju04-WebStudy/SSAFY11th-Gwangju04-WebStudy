살려주세요

## RivewRestController
```java
package com.github.prgrms.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {
  // TODO review 메소드 구현이 필요합니다.
  
  private final ReviewService reviewService;
  public ReviewRestController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @PostMapping("{id}/review")
  public ApiResult<?> review(@PathVariable String id, @RequestBody HashMap<String, String> map) {
    // 주문 상태 'state' 가 COMPLETED가 아닐 경우 처리
    // 중복 리뷰 작성시 오류 처리
    return reviewService.check(id, map.get("content"));
  }
}
```

## ReviewService
```java
package com.github.prgrms.orders;
import com.github.prgrms.utils.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final JdbcOrderRepository jdbcReviewRepository;

    public ReviewService(JdbcOrderRepository jdbcReviewRepository) {
        this.jdbcReviewRepository = jdbcReviewRepository;
    }

    public ApiResult<?> check(String order_seq, String content) {
        Order order = jdbcReviewRepository.checkReview(order_seq);
        // 정상 처리
        if (order.getReview_seq() == null && order.getState().equals("COMPLETED")) {
            return ApiUtils.success(jdbcReviewRepository.makeReview(order, content));
        } 
        // 주문 상태 이상상
        else if (order.getReview_seq() == null) {
            return ApiUtils.error("Could not write review for order 4 because have already written", HttpStatus.BAD_REQUEST);
        } 
        // 중복 발생
        else {
            return ApiUtils.error("Could not write review for order 1 because state(REQUESTED) is not allowed", HttpStatus.BAD_REQUEST);
        }
    }

}
```

## Review
```java
package com.github.prgrms.orders;

import java.time.LocalDateTime;


public class Review {
    
    private Long seq;
    private Long user_seq;
    private Long product_seq;
    private String content;
    private LocalDateTime create_at;

    public Review(Long seq, Long user_seq, Long product_seq, String content, LocalDateTime create_at) {
        this.seq = seq;
        this.user_seq = user_seq;
        this.product_seq = product_seq;
        this.content = content;
        this.create_at = create_at;
    }

    public Long getSeq() {
        return seq;
    }
    public void setSeq(Long seq) {
        this.seq = seq;
    }
    public Long getUser_seq() {
        return user_seq;
    }
    public void setUser_seq(Long user_seq) {
        this.user_seq = user_seq;
    }
    public Long getProduct_seq() {
        return product_seq;
    }
    public void setProduct_seq(Long product_seq) {
        this.product_seq = product_seq;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getCreate_at() {
        return create_at;
    }
    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    static public class Builder {
        private Long seq;
        private Long user_seq;
        private Long product_seq;
        private String content;
        private LocalDateTime create_at;

        public Builder() {}

        public Builder(Review review) {
            this.seq = review.seq;
            this.user_seq = review.user_seq;
            this.product_seq = review.product_seq;
            this.content = review.content;
            this.create_at = review.create_at;
        }

        public Review build() {
            return new Review(
                seq,
                user_seq,
                product_seq,
                content,
                create_at
            );
        }

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }
        public Builder user_seq(Long seq) {
            this.user_seq = seq;
            return this;
        }
        public Builder product_seq(Long seq) {
            this.product_seq = seq;
            return this;
        }
        public Builder content(String content) {
            this.content = content;
            return this;
        }
        public Builder create_at(LocalDateTime create_at) {
            this.create_at = create_at;
            return this;
        }
 
    } 
}
```

## Order
```java
package com.github.prgrms.orders;

public class Order {
    
    private Long seq;
    private Long user_seq;
    private Long product_seq;
    private Long review_seq;
    private String state;
    
    public Order(Long seq, Long user_seq, Long product_seq, Long review_seq, String state) {
        this.seq = seq;
        this.user_seq = user_seq;
        this.product_seq = product_seq;
        this.review_seq = review_seq;
        this.state = state;
    }
    public Long getSeq() {
        return seq;
    }
    public void setSeq(Long seq) {
        this.seq = seq;
    }
    public Long getUser_seq() {
        return user_seq;
    }
    public void setUser_seq(Long user_seq) {
        this.user_seq = user_seq;
    }
    public Long getProduct_seq() {
        return product_seq;
    }
    public void setProduct_seq(Long product_seq) {
        this.product_seq = product_seq;
    }
    public Long getReview_seq() {
        return review_seq;
    }
    public void setReview_seq(Long review_seq) {
        this.review_seq = review_seq;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    static public class Builder {
        private Long seq;
        private Long user_seq;
        private Long product_seq;
        private Long review_seq;
        private String state;

        public Builder() {}

        public Builder(Order order) {
            this.seq = order.seq;
            this.user_seq = order.seq;
            this.product_seq = order.product_seq;
            this.review_seq = order.review_seq;
            this.state = order.state;
        }

        public Order build() {
            return new Order(
                seq,
                user_seq,
                product_seq,
                review_seq,
                state
            );
        }

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }
        public Builder user_seq(Long seq) {
            this.user_seq = seq;
            return this;
        }
        public Builder product_seq(Long seq) {
            this.product_seq = seq;
            return this;
        }
        public Builder review_seq(Long seq) {
            this.review_seq = seq;
            return this;
        }
        public Builder state(String state) {
            this.state = state;
            return this;
        }
    }
}
```
## JdbcOrderRepository
```java
package com.github.prgrms.orders;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;
  
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Order checkReview(String order_seq) {

        return jdbcTemplate.queryForObject(
            "SELECT * FROM orders where seq = ? and review_seq is null",
            mapper,
            order_seq
        );
    }

    @Override
    public Review makeReview(Order order, String content) {
        jdbcTemplate.update("Insert into reviews (user_seq, product_seq, content) values(?, ?, ?)",
        order.getUser_seq(), order.getProduct_seq(), content);

        return jdbcTemplate.queryForObject(
            "SELECT * FROM reviews where user_seq = ? and product_seq = ? and content = ?",
            mapper2,
            order.getUser_seq(),
            order.getProduct_seq(),
            content
        );
    }

    static RowMapper<Order> mapper = (rs, rowNum) ->
        new Order.Builder()
        .seq(rs.getLong("seq"))
        .user_seq(rs.getLong("user_seq"))
        .product_seq(rs.getLong("product_seq"))
        .review_seq(rs.getLong("review_seq"))
        .state(rs.getString("state"))
        .build();

    static RowMapper<Review> mapper2 = (rs, rowNum) ->
        new Review.Builder()
        .seq(rs.getLong("seq"))
        .user_seq(rs.getLong("user_seq"))
        .product_seq(rs.getLong("projuct_seq"))
        .content(rs.getString("content"))
        .create_at(dateTimeOf(rs.getTimestamp("create_at")))
        .build();
}
```

