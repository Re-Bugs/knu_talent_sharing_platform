package com.zeroone.kstp.domain;

import com.zeroone.kstp.enumeration.UserLevel;
import com.zeroone.kstp.enumeration.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;

@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder(toBuilder = true) // 객체 수정 허용
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role", columnDefinition = "role")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private UserRole role;

    @Column(name = "student_number", unique = true, length = 9)
    private String studentNumber;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(name = "name", nullable = false, length = 5)
    private String name;

    @Column(nullable = false, length = 13)
    private String tel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "level", columnDefinition = "level")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private UserLevel level;

    @Column(nullable = false, length = 20)
    private String major;

    @Column(name = "last_grades", precision = 3, scale = 2, nullable = false)
    private BigDecimal lastGrades;
}
