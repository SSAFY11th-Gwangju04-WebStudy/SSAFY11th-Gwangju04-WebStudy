# 주문관리 API 서버 JPA 적용

# 1. Entity

### BaseEntity

```java
package com.github.prgrms.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createAt;
}
```

- `createAt`(작성 시간) 공통 처리

### Review

```java
package com.github.prgrms.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_seq")
    private Product product;

    private String content;

    public Review(User user, Product product, String content) {
        this(null, user, product, content);
    }
}

```

### Order

```java
package com.github.prgrms.entity;

import com.github.prgrms.orders.OrderState;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_seq")
    private Product product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_seq")
    private Review review;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    private String requestMsg;

    private String rejectMsg;

    private LocalDateTime completedAt;

    private LocalDateTime rejectedAt;

    public void setReview(Review review) {
        this.review = review;
    }

    public void changeState(OrderState state) {
        this.state = state;
    }

    public void setRejectMsg(String rejectMsg) {
        this.rejectMsg = rejectMsg;
    }

    public void setCompletedAt() {
        completedAt = LocalDateTime.now();
    }

    public void setRejectedAt() {
        rejectedAt = LocalDateTime.now();
    }
}

```

### Product

```java
package com.github.prgrms.entity;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String name;

    private String details;

    private int reviewCount;

    public Product(String name, String details) {
        this(null, name, details, 0);
    }

    public Product(Long seq, String name, String details, int reviewCount) {
        checkArgument(isNotEmpty(name), "name must be provided");
        checkArgument(name.length() >= 1 && name.length() <= 50, "name length must be between 1 and 50 characters");
        checkArgument(isEmpty(details) || details.length() <= 1000, "details length must be less than 1000 characters");

        this.seq = seq;
        this.name = name;
        this.details = details;
        this.reviewCount = reviewCount;
    }

    public Optional<String> getDetails() {
        return ofNullable(details);
    }

    public void addReviewCount() {
        reviewCount++;
    }
}
```

### User

```java
package com.github.prgrms.entity;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import com.github.prgrms.security.Jwt;
import com.github.prgrms.users.Email;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String name;

    private Email email;

    private String password;

    private int loginCount;

    private LocalDateTime lastLoginAt;

    public User(String name, Email email, String password) {
        this(null, name, email, password, 0, null);
    }

    public User(Long seq, String name, Email email, String password, int loginCount, LocalDateTime lastLoginAt) {
        checkArgument(isNotEmpty(name), "name must be provided");
        checkArgument(
                name.length() >= 1 && name.length() <= 10,
                "name length must be between 1 and 10 characters"
        );
        checkNotNull(email, "email must be provided");
        checkNotNull(password, "password must be provided");

        this.seq = seq;
        this.name = name;
        this.email = email;
        this.password = password;
        this.loginCount = loginCount;
        this.lastLoginAt = lastLoginAt;
    }

    public String newJwt(Jwt jwt, String[] roles) {
        Jwt.Claims claims = Jwt.Claims.of(seq, name, roles);
        return jwt.create(claims);
    }

    public void login(PasswordEncoder passwordEncoder, String credentials) {
        if (!passwordEncoder.matches(credentials, password)) {
            throw new IllegalArgumentException("Bad credential");
        }
    }

    public void afterLoginSuccess() {
        loginCount++;
        lastLoginAt = now();
    }

    public Optional<LocalDateTime> getLastLoginAt() {
        return ofNullable(lastLoginAt);
    }

}
```

---

## 2. Repository

### ReviewRepository

```java
package com.github.prgrms.orders.review;

import com.github.prgrms.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

```

### OrderRepository

```java
package com.github.prgrms.orders;

import com.github.prgrms.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
}

```

### OrderRepositoryCustom

```java
package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.entity.Order;
import java.util.List;

public interface OrderRepositoryCustom {

    List<Order> findAll(Pageable pageable);
}
 	 
```

### OrderRepositoryCustomImpl

```java
package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.entity.Order;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Order> findAll(Pageable pageable) {
        return em.createQuery("select o from Order o left join fetch o.review r order by o.seq desc", Order.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getSize())
                .getResultList();
    }
}

```

- fetch join을 활용하여 N + 1문제 해결

### ProductRepository

```java
package com.github.prgrms.products;

import com.github.prgrms.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderBySeqDesc();
}
```

- 쿼리 메서드(메서드 이름으로 쿼리문 생성) 활용

### UserRepository

```java
package com.github.prgrms.users;

import com.github.prgrms.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(Email email);
}
```

---

## 3. Service

### ReviewService

```java
package com.github.prgrms.orders.review;

import static com.github.prgrms.orders.OrderState.COMPLETED;

import com.github.prgrms.entity.Order;
import com.github.prgrms.entity.Product;
import com.github.prgrms.entity.Review;
import com.github.prgrms.entity.User;
import com.github.prgrms.errors.DuplicatedReviewException;
import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.errors.WrongStateException;
import com.github.prgrms.orders.OrderRepository;
import com.github.prgrms.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public Review save(Long orderSeq, Long userSeq, String content) {
        Order order = orderRepository.findById(orderSeq)
                .orElseThrow(() -> new NotFoundException("Could not found order for " + orderSeq));

        if (order.getReview() != null) {
            throw new DuplicatedReviewException(
                    "Could not write review for order " + orderSeq + " because have already written");
        }

        if (!order.getState().equals(COMPLETED)) {
            throw new WrongStateException(
                    "Could not write review for order " + orderSeq + " because state(" + order.getState()
                            + ") is not allowed");
        }

        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new NotFoundException("Could not found user for " + userSeq));
        Product product = order.getProduct();

        Review review = reviewRepository.save(new Review(user, order.getProduct(), content));

				// 변경 감지 활용
        order.setReview(review);
        product.addReviewCount();

        return review;
    }
}

```

- update 쿼리를 직접 작성하지 않고 **변경 감지**를 활용하였다.

### OrderService

```java
package com.github.prgrms.orders;

import static com.github.prgrms.orders.OrderState.ACCEPTED;
import static com.github.prgrms.orders.OrderState.COMPLETED;
import static com.github.prgrms.orders.OrderState.REJECTED;
import static com.github.prgrms.orders.OrderState.REQUESTED;
import static com.github.prgrms.orders.OrderState.SHIPPING;
import static com.google.common.base.Preconditions.checkNotNull;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.entity.Order;
import com.github.prgrms.errors.NotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

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

        Order order = findById(orderId).orElseThrow(
                () -> new NotFoundException("Could not found order for " + orderId));

				// 변경 감지 활용
        if (order.getState().equals(REQUESTED)) {
            order.changeState(ACCEPTED);
            return true;
        }

        return false;
    }

    public boolean rejectOrder(Long orderId, String rejectMessage) {
        checkNotNull(orderId, "orderId must be provided");

        Order order = findById(orderId).orElseThrow(
                () -> new NotFoundException("Could not found order for " + orderId));

				// 변경 감지 활용
        if (order.getState().equals(REQUESTED)) {
            order.changeState(REJECTED);
            order.setRejectMsg(rejectMessage);
            order.setRejectedAt();
            return true;
        }

        return false;
    }

    public boolean shipOrder(Long orderId) {
        checkNotNull(orderId, "orderId must be provided");

        Order order = findById(orderId).orElseThrow(
                () -> new NotFoundException("Could not found order for " + orderId));

				// 변경 감지 활용
        if (order.getState().equals(ACCEPTED)) {
            order.changeState(SHIPPING);
            return true;
        }

        return false;
    }

    public boolean completeOrder(Long orderId) {
        checkNotNull(orderId, "orderId must be provided");

        Order order = findById(orderId).orElseThrow(
                () -> new NotFoundException("Could not found order for " + orderId));

				// 변경 감지 활용
        if (order.getState().equals(SHIPPING)) {
            order.changeState(COMPLETED);
            order.setCompletedAt();
            return true;
        }

        return false;
    }
}

```

- 변경 감지 활용