package com.example.code.repository;

import com.example.code.model.Book;
import com.example.code.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

    SubCategory findSubCategoryBySubCategoryName(String subCategoryName);

//    @Query("select sub from SubCategory sub left join fetch sub.books where sub.subCategoryName = :name")
//    SubCategory findSubCategoryBySubCategoryName(@Param("name") String subCategoryName);


}
