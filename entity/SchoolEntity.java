package kr.hs.dge.dgsw.ex1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_school")
public class SchoolEntity extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String name;
  @ManyToOne
  private SchoolEntity school;

}
