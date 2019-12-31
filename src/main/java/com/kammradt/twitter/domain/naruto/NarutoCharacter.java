package com.kammradt.twitter.domain.naruto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@NoArgsConstructor
@Getter @Setter
@Entity
public class NarutoCharacter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imageUrl;

}
