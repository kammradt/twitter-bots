package com.kammradt.twitter.bots.meangirls;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class MeanGirl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;
}