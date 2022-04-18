package com.berg.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name = "daily_menu")
public class DailyMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "breakfast")
    private Recipe breakfast;

    @ManyToOne
    @JoinColumn(name = "first_snack")
    private Recipe firstSnack;

    @ManyToOne
    @JoinColumn(name = "lunch")
    private Recipe lunch;

    @ManyToOne
    @JoinColumn(name = "second_snack")
    private Recipe secondSnack;

    @ManyToOne
    @JoinColumn(name = "dinner")
    private Recipe dinner;

    private String title;
}
