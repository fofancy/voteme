package com.fofancy.auth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tokens")
@NamedQueries({
        @NamedQuery(
                name = TokenEntity.GET_BY_TOKEN_VALUE,
                query = "SELECT t " +
                        "FROM TokenEntity t " +
                        "JOIN t.user u WHERE t.tokenValue = :tokenValue"
        ),
        @NamedQuery(
                name = TokenEntity.GET_BY_USERNAME,
                query = "SELECT t " +
                        "FROM TokenEntity t " +
                        "JOIN t.user u " +
                        "WHERE u.username = :username"
        )
})
@Getter
@Setter
@NoArgsConstructor
public class TokenEntity {
    public static final String GET_BY_TOKEN_VALUE = "TokenEntity.getByTokenValue";
    public static final String GET_BY_USERNAME = "TokenEntity.getByUsername";

    @Id
    @Column(name = "token_id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tokens_seq")
    @SequenceGenerator(name = "tokens_seq", sequenceName = "tokens_seq")
    private Long id;

    @Column(name = "token_value")
    @NotNull
    private String tokenValue;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "token_user_id")
    @NotNull
    private User user;
}
