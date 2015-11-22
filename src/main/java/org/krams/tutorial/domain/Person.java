package org.krams.tutorial.domain;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "User")
public class Person implements Serializable {
 
 private static final long serialVersionUID = -5527566248002296042L;

 @Id
 @Column(name = "id")
 @GeneratedValue
 private Integer id;

 @Column(name = "name")
 private String name;

 @Column(name = "age")
 private Integer age;

 @Column(name = "isAdmin")
 private Integer isAdmin;

 @Column(name = "createdDate")
 private String createdDate;

 public Integer getId() {
  return id;
 }

 public void setId(Integer id) {
  this.id = id;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public Integer getAge() {
  return age;
 }

 public void setAge(Integer age) {
  this.age = age;
 }

 public Integer getIsAdmin() {
  return isAdmin;
 }

 public void setIsAdmin(Integer isAdmin) {
  this.isAdmin = isAdmin;
 }

 public String getCreatedDate() {
  return createdDate;
 }

 public void setCreatedDate(String createdDate) {
  this.createdDate = createdDate;
 }

 // Getters and setters


}