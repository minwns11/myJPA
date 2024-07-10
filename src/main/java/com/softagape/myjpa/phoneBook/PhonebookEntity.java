package com.softagape.myjpa.phoneBook;

import com.softagape.myjpa.catCategory.CategoryEntity;
import com.softagape.myjpa.catCategory.ICategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="phonebook_tbl")
public class PhonebookEntity implements IPhoneBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50, unique = true) // unique = true 중복값 예외처리
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;

    @NotNull
    @Column(length = 20)
    private String phoneNumber;

    @Column(length = 200)
    private String email;

    @Override
    public String toString() {
        return String.format("{ID:%6d, 이름:%s, 분류:%s, 번호:%s, 이메일:%s}"
                , this.id, this.name, this.category, this.phoneNumber, this.email);
    }

    @Override
    public void setCategory(ICategory category) {
        if ( category == null ) {
            return;
        }
        CategoryEntity entity = new CategoryEntity();
        entity.copyFields(category); //
//        this.category = (CategoryEntity) entity; // 런타임 에러가 일어나기 때문에 안좋음
        this.category = entity;
    }
}

