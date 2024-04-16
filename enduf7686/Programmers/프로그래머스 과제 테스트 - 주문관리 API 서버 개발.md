# 프로그래머스 과제 테스트 - 주문관리 API 서버 개발

## Review 관련 기능

### Review

```java
package com.github.prgrms.orders.review;

import java.time.LocalDateTime;

public class Review {

    private Long seq;

    private Long userSeq;

    private Long productSeq;

    private String content;

    private LocalDateTime createAt;

    public Review(Long userSeq, Long productSeq, String content) {
        this(null, userSeq, productSeq, content, null);
    }

    public Review(Long seq, Long userSeq, Long productSeq, String content, LocalDateTime createAt) {
        this.seq = seq;
        this.userSeq = userSeq;
        this.productSeq = productSeq;
        this.content = content;
        this.createAt = createAt;
    }

    public Long getSeq() {
        return seq;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public Long getProductSeq() {
        return productSeq;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
    
    public static class Builder {

        private Long seq;
        private Long userSeq;
        private Long productSeq;
        private String content;
        private LocalDateTime createAt;

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Builder userSeq(Long userSeq) {
            this.userSeq = userSeq;
            return this;
        }

        public Builder productSeq(Long productSeq) {
            this.productSeq = productSeq;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }
        
        public Review build() {
            return new Review(
                    seq,
                    userSeq,
                    productSeq,
                    content,
                    createAt
            );
        }
    }
}

```

- Builder 패턴

### ReviewDto

```java
package com.github.prgrms.orders.review;

import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;

public class ReviewDto {

    private Long seq;

    private Long productId;

    private String content;

    private LocalDateTime createAt;

    public ReviewDto(Long seq, Long productId, String content, LocalDateTime createAt) {
        this.seq = seq;
        this.productId = productId;
        this.content = content;
        this.createAt = createAt;
    }

    public ReviewDto(Review source) {
        BeanUtils.copyProperties(source, this);

        this.productId = source.getProductSeq();
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}

```

- `BeanUtils.copyProperties(source, target)`: 엔티티 객체의 정보를 DTO로 옮길 때 유용
    - `source`는 `getter`필요
    - `target`은 `setter`필요

### ReviewRequest

```java
package com.github.prgrms.orders.review;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class ReviewRequest {

    @NotBlank(message = "content must be provided")
    @Length(max = 1000, message = "content must be less than 1000 characters")
    private String content;
    
    protected ReviewRequest() {
    }

    public ReviewRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

```

- 사용자의 요청으로부터 Review에 들어갈 content를 가져오기 위한 DTO
- **기본 생성자가 없으면 에러 발생**
- `@NotBlank`: content는 반드시 값을 가져야 한다.
- `@Length`: content의 길이를 제한한다.

### ReviewRestController

```java
package com.github.prgrms.orders.review;

import com.github.prgrms.security.JwtAuthentication;
import com.github.prgrms.utils.ApiUtils.ApiResult;
import javax.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.prgrms.utils.ApiUtils.success;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {

		private final ReviewService reviewService;
	
		public ReviewRestController(ReviewService reviewService) {
				this.reviewService = reviewService;
		}
	
		@PostMapping(path = "{id}/review")
		public ApiResult<ReviewDto> review(@AuthenticationPrincipal JwtAuthentication authentication,
				@PathVariable("id") Long orderId, @Valid @RequestBody ReviewRequest request) {
				return success(
						reviewService.save(
								orderId, authentication.id, request.getContent()).map(ReviewDto::new).get()
						);
		}
}
```

- `@AuthenticationPrincipal`: JWT 인증 정보로부터 사용자 정보를 가져온다. (세션과 비슷한 역할)
- `@Valid`: `ReviewRequest` 검증(Validation)

### ReviewService

```java
package com.github.prgrms.orders.review;

import com.github.prgrms.errors.DuplicatedReviewException;
import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.errors.WrongStateException;
import com.github.prgrms.orders.Order;
import com.github.prgrms.orders.OrderRepository;
import com.github.prgrms.products.ProductRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.github.prgrms.orders.OrderState.COMPLETED;

@Service
@Transactional
public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository reviewRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Optional<Review> save(Long orderId, Long userSeq, String content) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Could not found order for " + orderId));

        if (order.getReviewSeq() != null) {
        throw new DuplicatedReviewException("Could not write review for order " + orderId + " because have already written");
        }

        if (!order.getState().equals(COMPLETED)) {
        throw new WrongStateException("Could not write review for order " + orderId + " because state(" + order.getState() + ") is not allowed");
        }

        Optional<Review> review = reviewRepository.save(new Review(userSeq, order.getProductSeq(), content));
        
        orderRepository.updateOrderReview(orderId, review.get().getSeq());
        productRepository.updateReviewCount(order.getProductSeq());
        
        return review;
    }
}

```

- 예외 처리
    - 중복 리뷰
    - 주문 상태 체크(`OrderState.COMPLETED`)

### ReviewRepository

```java
package com.github.prgrms.orders.review;

import java.util.Optional;

public interface ReviewRepository {
    
    Optional<Review> save(Review review);

    Optional<Review> findById(long id);
}

```

### JdbcReviewRepository

```java
package com.github.prgrms.orders.review;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcReviewRepository implements ReviewRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Optional<Review> save(Review review) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(conn -> {
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO reviews(user_seq, product_seq, content) values (?, ?, ?)", new String[] {"seq"});
            
            pstmt.setLong(1, review.getUserSeq());
            pstmt.setLong(2, review.getProductSeq());
            pstmt.setString(3, review.getContent());

            return pstmt;
        }, keyHolder);

        return findById(keyHolder.getKey().longValue());
    }

    @Override
    public Optional<Review> findById(long id) {
        List<Review> results = jdbcTemplate.query(
            "SELECT * FROM reviews WHERE seq = ?", 
            mapper, 
            id
        );

        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    static RowMapper<Review> mapper = (rs, rowNum) -> 
        new Review.Builder()
            .seq(rs.getLong("seq"))
            .userSeq(rs.getLong("user_seq"))
            .productSeq(rs.getLong("product_seq"))
            .content(rs.getString("content"))
            .createAt(dateTimeOf(rs.getTimestamp("create_at")))
            .build();

}

```

- `KeyHolder`: DB에 객체를 저장한 후 Primary Key를 가져올 수 있다. (Auto-Increment)
- `RowMapper`: 테이블의 한 행을 하나의 객체로 변환해준다.

## Order 관련 기능

### Order

```java
package com.github.prgrms.orders;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {

    private Long seq;

    private Long userSeq;

    private Long productSeq;

    private Long reviewSeq;

    private String reviewContent;

    private LocalDateTime reviewCreateAt;

    private OrderState state;

    private String requestMsg;

    private String rejectMsg;

    private LocalDateTime completedAt;

    private LocalDateTime rejectedAt;

    private LocalDateTime createAt;

    public Order(Long seq, Long userSeq, Long productSeq, Long reviewSeq, String reviewContent, LocalDateTime reviewCreateAt, OrderState state, String requestMsg, String rejectMsg, LocalDateTime completedAt, LocalDateTime rejectedAt, LocalDateTime createAt) {
        this.seq = seq;
        this.userSeq = userSeq;
        this.productSeq = productSeq;
        this.reviewSeq = reviewSeq;
        this.reviewContent = reviewContent;
        this.reviewCreateAt = reviewCreateAt;
        this.state = state;
        this.requestMsg = requestMsg;
        this.rejectMsg = rejectMsg;
        this.completedAt = completedAt;
        this.rejectedAt = rejectedAt;
        this.createAt = createAt;
    }

    public Long getSeq() {
        return seq;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public Long getProductSeq() {
        return productSeq;
    }

    public Long getReviewSeq() {
        return reviewSeq;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public LocalDateTime getReviewCreateAt() {
        return reviewCreateAt;
    }

    public OrderState getState() {
        return state;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public String getRejectMsg() {
        return rejectMsg;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public LocalDateTime getRejectedAt() {
        return rejectedAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(seq, order.seq) && Objects.equals(userSeq, order.userSeq) && Objects.equals(productSeq, order.productSeq) && Objects.equals(reviewSeq, order.reviewSeq) && Objects.equals(reviewContent, order.reviewContent) && Objects.equals(reviewCreateAt, order.reviewCreateAt) && Objects.equals(state, order.state) && Objects.equals(requestMsg, order.requestMsg) && Objects.equals(rejectMsg, order.rejectMsg) && Objects.equals(completedAt, order.completedAt) && Objects.equals(rejectedAt, order.rejectedAt) && Objects.equals(createAt, order.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq, userSeq, productSeq, reviewSeq, reviewContent, reviewCreateAt, state, requestMsg, rejectMsg, completedAt, rejectedAt, createAt);
    }

    public static class Builder {

        private Long seq;
        private Long userSeq;
        private Long productSeq;
        private Long reviewSeq;
        private String reviewContent;
        private LocalDateTime reviewCreateAt;
        private OrderState state;
        private String requestMsg;
        private String rejectMsg;
        private LocalDateTime completedAt;
        private LocalDateTime rejectedAt;
        private LocalDateTime createAt;

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Builder userSeq(Long userSeq) {
            this.userSeq = userSeq;
            return this;
        }

        public Builder productSeq(Long productSeq) {
            this.productSeq = productSeq;
            return this;
        }

        public Builder reviewSeq(Long reviewSeq) {
            this.reviewSeq = reviewSeq;
            return this;
        }

        public Builder reviewContent(String reviewContent) {
            this.reviewContent = reviewContent;
            return this;
        }

        public Builder reviewCreateAt(LocalDateTime reviewCreateAt) {
            this.reviewCreateAt = reviewCreateAt;
            return this;
        }

        public Builder state(OrderState state) {
            this.state = state;
            return this;
        }

        public Builder requestMsg(String requestMsg) {
            this.requestMsg = requestMsg;
            return this;
        }

        public Builder rejectMsg(String rejectMsg) {
            this.rejectMsg = rejectMsg;
            return this;
        }

        public Builder completedAt(LocalDateTime completedAt) {
            this.completedAt = completedAt;
            return this;
        }

        public Builder rejectedAt(LocalDateTime rejectedAt) {
            this.rejectedAt = rejectedAt;
            return this;
        }

        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public Order build() {
            return new Order(
                    seq,
                    userSeq,
                    productSeq,
                    reviewSeq,
                    reviewContent,
                    reviewCreateAt,
                    state,
                    requestMsg,
                    rejectMsg,
                    completedAt,
                    rejectedAt,
                    createAt
            );
        }
    }
}

```

- 응답 시 `Review`의 정보도 같이 보여줘야 하기 때문에 `Review`의 정보도 포함 시켰다.

### OrderDto

```java
package com.github.prgrms.orders;

import com.github.prgrms.orders.review.ReviewDto;
import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class OrderDto {
    
    private Long seq;

    private Long productId;

    private ReviewDto review;

    private OrderState state;

    private String requestMessage;

    private String rejectMessage;

    private LocalDateTime completedAt;

    private LocalDateTime rejectedAt;

    private LocalDateTime createAt;

    public OrderDto(Order source) {
        copyProperties(source, this);

        productId = source.getProductSeq();
        requestMessage = source.getRequestMsg();
        rejectMessage = source.getRejectMsg();

        review = (source.getReviewSeq() == null ? 
                null : new ReviewDto(source.getReviewSeq(), source.getProductSeq(), source.getReviewContent(), source.getReviewCreateAt()));
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ReviewDto getReview() {
        return review;
    }

    public void setReview(ReviewDto review) {
        this.review = review;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public String getRejectMessage() {
        return rejectMessage;
    }

    public void setRejectMessage(String rejectMessage) {
        this.rejectMessage = rejectMessage;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getRejectedAt() {
        return rejectedAt;
    }

    public void setRejectedAt(LocalDateTime rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}

```

- `ReviewDto` 포함

### RejectRequest

```java
package com.github.prgrms.orders;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class RejectRequest {
    
    @NotBlank(message = "message must be provided")
    @Length(max = 1000, message = "message must be less than 1000 characters")
    private String message;

    protected RejectRequest() {
    }

    public RejectRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

```

- 사용자의 요청으로부터 거절 메시지를 받기 위한 DTO
- `message`필드 Validation
- 기본 생성자 반드시 필요

### OrderRestController

```java
package com.github.prgrms.orders;

import java.util.List;
import javax.validation.Valid;
import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.utils.ApiUtils.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.prgrms.utils.ApiUtils.success;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ApiResult<List<OrderDto>> findAll(Pageable pageable) {
        return success(
            orderService.findAll(pageable).stream()
                    .map(OrderDto::new)
                    .collect(toList())
        );
    }

    @GetMapping(path = "{id}")
    public ApiResult<OrderDto> findById(@PathVariable Long id) {
        return success(
            orderService.findById(id)
                    .map(OrderDto::new)
                    .orElseThrow(() -> new RuntimeException())
        );
    }

    @PatchMapping(path = "{id}/accept")
    public ApiResult<Boolean> accept(@PathVariable Long id) {
        return success(orderService.acceptOrder(id));
    }

    @PatchMapping(path = "{id}/reject")
    public ApiResult<Boolean> reject(@PathVariable Long id, @Valid @RequestBody RejectRequest request) {
        return success(orderService.rejectOrder(id, request.getMessage()));
    }

    @PatchMapping(path = "{id}/shipping")
    public ApiResult<Boolean> shipping(@PathVariable Long id) {
        return success(orderService.shipOrder(id));
    }

    @PatchMapping(path = "{id}/complete")
    public ApiResult<Boolean> complete(@PathVariable Long id) {
        return success(orderService.completeOrder(id));
    }
}
```

- Pageable객체를 파라미터로 받기 위해 `SimplePageRequestHandlerMethodArgumentResolver`구현

### SimplePageRequestHandlerMethodArgumentResolver

```java
package com.github.prgrms.configures.web;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SimplePageRequestHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	  private static final String DEFAULT_OFFSET_PARAMETER = "offset";
	
	  private static final String DEFAULT_SIZE_PARAMETER = "size";
	
	  private static final long DEFAULT_OFFSET = 0;
	
	  private static final int DEFAULT_SIZE = 5;
	
	  private String offsetParameterName = DEFAULT_OFFSET_PARAMETER;
	
	  private String sizeParameterName = DEFAULT_SIZE_PARAMETER;

	  @Override
	  public boolean supportsParameter(MethodParameter parameter) {
		    return Pageable.class.isAssignableFrom(parameter.getParameterType());
	  }

	  @Override
	  public Object resolveArgument(
		    MethodParameter methodParameter,
		    ModelAndViewContainer mavContainer,
		    NativeWebRequest webRequest,
		    WebDataBinderFactory binderFactory
	  ) {
		    String offsetString = webRequest.getParameter(offsetParameterName);
		    String sizeString = webRequest.getParameter(sizeParameterName);
		    
		    long offset = offsetString == null ? DEFAULT_OFFSET : Long.parseLong(offsetString);
		    int size = sizeString == null ? DEFAULT_SIZE : Integer.parseInt(sizeString);
		
		    if (offset < 0 || offset > 5) {
			      offset = DEFAULT_OFFSET;
		    }
		
		    if (size < 1 || size > 5) {
			      size = DEFAULT_SIZE;
		    }
		
		    return new SimplePageRequest(offset, size);
	  }
	
	  public void setOffsetParameterName(String offsetParameterName) {
		    this.offsetParameterName = offsetParameterName;
	  }
	
	  public void setSizeParameterName(String sizeParameterName) {
		    this.sizeParameterName = sizeParameterName;
	  }

}
```

- `supportParameter()`: 파라미터의 타입 확인
- `resolveArgument()`: 객체를 생성해서 파라미터에 주입
    - `Pageable`의 구현체인 `SimplePageRequest`를 반환해야 한다.
    - 설정 범위를 벗어나면 Default 값으로 설정

### OrderService

```java
package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.errors.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.github.prgrms.orders.OrderState.ACCEPTED;
import static com.github.prgrms.orders.OrderState.REQUESTED;
import static com.github.prgrms.orders.OrderState.SHIPPING;
import static com.google.common.base.Preconditions.checkNotNull;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    @Transactional(readOnly = true)
    public List<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Order> findById(Long orderId) {
        checkNotNull(orderId, "orderId must be provided");

        return orderRepository.findById(orderId);
    }

    public boolean acceptOrder(Long orderId) {
        checkNotNull(orderId, "orderId must be provided");

        Order order = findById(orderId).orElseThrow(() -> new NotFoundException("Could not found order for " + orderId));

        if (order.getState().equals(REQUESTED)) {
            orderRepository.acceptOrder(orderId);
            return true;
        }

        return false;
    }

    public boolean rejectOrder(Long orderId, String rejectMessage) {
        checkNotNull(orderId, "orderId must be provided");

        Order order = findById(orderId).orElseThrow(() -> new NotFoundException("Could not found order for " + orderId));

        if (order.getState().equals(REQUESTED)) {
            orderRepository.rejectOrder(orderId, rejectMessage);
            return true;
        }

        return false;
    }

    public boolean shipOrder(Long orderId) {
        checkNotNull(orderId, "orderId must be provided");

        Order order = findById(orderId).orElseThrow(() -> new NotFoundException("Could not found order for " + orderId));

        if (order.getState().equals(ACCEPTED)) {
            orderRepository.shipOrder(orderId);
            return true;
        }

        return false;
    }

    public boolean completeOrder(Long orderId) {
        checkNotNull(orderId, "orderId must be provided");

        Order order = findById(orderId).orElseThrow(() -> new NotFoundException("Could not found order for " + orderId));

        if (order.getState().equals(SHIPPING)) {
            orderRepository.completeOrder(orderId);
            return true;
        }

        return false;
    }
}

```

- `Preconditions.checkNotNull()`: null이면 예외 발생 시킴
- 예외 처리
- 조회 전용 쿼리에 `@Transactional(readOnly = true)`어노테이션을 붙여서 성능을 최적화한다.

### OrderRepository

```java
package com.github.prgrms.orders;

import java.util.List;
import java.util.Optional;

import com.github.prgrms.configures.web.Pageable;

public interface OrderRepository {
    
    List<Order> findAll(Pageable pageable);

    Optional<Order> findById(long id);

    int acceptOrder(long id);

    int rejectOrder(long id, String rejectMessage);

    int shipOrder(long id);

    int completeOrder(long id);

    int updateOrderReview(long id, long reviewId);
}

```

### JdbcOrderRepository

```java
package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import static com.github.prgrms.orders.OrderState.ACCEPTED;
import static com.github.prgrms.orders.OrderState.COMPLETED;
import static com.github.prgrms.orders.OrderState.REJECTED;
import static com.github.prgrms.orders.OrderState.SHIPPING;
import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Order> findAll(Pageable pageable) {
        return jdbcTemplate.query(
            "SELECT * FROM orders LEFT OUTER JOIN reviews ON orders.review_seq = reviews.seq ORDER BY seq DESC LIMIT ? OFFSET ?",
            mapper,
            pageable.getSize(),
            pageable.getOffset()
        );
    }

    @Override
    public Optional<Order> findById(long id) {
        List<Order> results = jdbcTemplate.query(
            "SELECT * FROM orders LEFT OUTER JOIN reviews ON orders.review_seq = reviews.seq WHERE orders.seq = ?", 
            mapper,
            id);

        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public int acceptOrder(long id) {
        return jdbcTemplate.update(
            "UPDATE orders SET state = ? WHERE seq = ?",
            ACCEPTED.name(),
            id
        );
    }

    @Override
    public int rejectOrder(long id, String rejectMessage) {
        return jdbcTemplate.update(
            "UPDATE orders SET state = ?, reject_msg = ?, rejected_at = ? WHERE seq = ?",
            REJECTED.name(),
            rejectMessage,
            LocalDateTime.now(),
            id
        );
    }

    @Override
    public int shipOrder(long id) {
        return jdbcTemplate.update(
            "UPDATE orders SET state = ? WHERE seq = ?",
            SHIPPING.name(),
            id
        );
    }

    @Override
    public int completeOrder(long id) {
        return jdbcTemplate.update(
            "UPDATE orders SET state = ?, completed_at = ? WHERE seq = ?",
            COMPLETED.name(),
            LocalDateTime.now(),
            id
        ); 
    }

    @Override
    public int updateOrderReview(long id, long reviewId) {
        return jdbcTemplate.update(
            "UPDATE orders SET review_seq = ? WHERE seq = ?",
            reviewId,
            id
        );
    }

    static RowMapper<Order> mapper = (rs, rowNum) ->
        new Order.Builder()
            .seq(rs.getObject("seq", Long.class))
            .userSeq(rs.getObject("user_seq", Long.class))
            .productSeq(rs.getObject("product_seq", Long.class))
            .reviewSeq(rs.getObject("review_seq", Long.class))
            .reviewContent(rs.getString("reviews.content"))
            .reviewCreateAt(dateTimeOf(rs.getTimestamp("reviews.create_at")))
            .state(OrderState.getState(rs.getString("state")))
            .requestMsg(rs.getString("request_msg"))
            .rejectMsg(rs.getString("reject_msg"))
            .completedAt(dateTimeOf(rs.getTimestamp("completed_at")))
            .rejectedAt(dateTimeOf(rs.getTimestamp("rejected_at")))
            .createAt(dateTimeOf(rs.getTimestamp("orders.create_at")))
            .build();
}

```

- `Order`에 `Review`의 정보도 같이 담아주기 위해 left outer join 사용

## 예외 처리 관련 코드

### DuplicatedReviewException

```java
package com.github.prgrms.errors;

public class DuplicatedReviewException extends RuntimeException {

	public DuplicatedReviewException(String message) {
		super(message);
	}

	public DuplicatedReviewException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

```

- 중복 리뷰 관련 커스텀 예외

### WrongStateException

```java
package com.github.prgrms.errors;

public class WrongStateException extends RuntimeException {
    
    public WrongStateException(String message) {
        super(message);
    }
        
    public WrongStateException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

```

- 리뷰 작성 시 주문 상태 체크 관련 커스텀 예외

### GeneralExceptionHandler

```java
package com.github.prgrms.errors;

import com.github.prgrms.utils.ApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

import static com.github.prgrms.utils.ApiUtils.error;

@ControllerAdvice
public class GeneralExceptionHandler {

    // ...

		// 추가한 부분
    @ExceptionHandler({ DuplicatedReviewException.class, WrongStateException.class })
    public ResponseEntity<?> handleReviewException(Exception e) {
        return newResponse(e, HttpStatus.BAD_REQUEST);
    }

		// 추가한 부분
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(Exception e) {
        return newResponse(e, HttpStatus.BAD_REQUEST);
    }

		// ...
		
}
```

- `HttpMessageNotReadableException`: RejectRequest, ReviewRequest 생성 시 요청의 Body가 비어있으면 발생하는 예외
    - 원래는 500 에러인데 400 에러로 변경