package kr.hs.dge.dgsw.ex1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_grade")
public class GradeEntity extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private int score;
  @ManyToOne
  private StudentEntity student;
}
