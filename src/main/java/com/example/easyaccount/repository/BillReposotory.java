package com.example.easyaccount.repository;

import com.example.easyaccount.entity.BillEntity;
import com.example.easyaccount.entity.result.BillResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BillReposotory extends JpaRepository<BillEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT b.id, b.amount, b.date, b.description, c.name AS category_name \n" +
            "FROM bill b\n" +
            "JOIN category c ON b.category_id = c.id\n" +
            "WHERE b.user_id = ?1 \n" +
            "AND ( \n" +
            "(?2 IS NOT NULL AND ?3 IS NULL AND ?4 IS NULL AND DATE_FORMAT(b.date, '%Y') = ?2) \n" +
            "OR \n" +
            "(?2 IS NOT NULL AND ?3 IS NOT NULL AND ?4 IS NULL AND DATE_FORMAT(b.date, '%Y-%m') = CONCAT(?2, '-', ?3)) \n" +
            "OR \n" +
            "(?2 IS NOT NULL AND ?3 IS NOT NULL AND ?4 IS NOT NULL AND DATE_FORMAT(b.date, '%Y-%m-%d') = CONCAT(?2, '-', ?3, '-', ?4)) \n" +
            "OR \n" +
            "(?2 IS NULL AND ?3 IS NULL AND ?4 IS NULL) \n" +
            ") \n" +
            "AND (?5 is null OR b.type = ?5)\n" +
            "AND (?6 is null OR b.category_id = ?6)")
    List<BillResult> find(Long userId, String year, String month, String day, Integer type, Long categoryId);

    @Query(nativeQuery = true, value = "SELECT\n" +
            "    COALESCE(SUM(CASE WHEN type = 1 THEN amount ELSE 0 END), 0) AS incomeTotalAmount,\n" +
            "    COALESCE(SUM(CASE WHEN type = 2 THEN amount ELSE 0 END), 0) AS expenditureTotalAmount\n" +
            "FROM bill b\n" +
            "WHERE b.user_id = ?1 " +
            "AND type IN (1, 2)\n" +
            "AND ( \n" +
            "DATE_FORMAT(b.date, '%Y') = ?2 \n" +
            ") \n" +
            "AND (?3 is null OR b.type = ?3)")
    Map<String, BigDecimal> findAmountByYear(Long userId, String year, Integer type);

    @Query(nativeQuery = true, value = "SELECT\n" +
            "    COALESCE(SUM(CASE WHEN type = 1 THEN amount ELSE 0 END), 0) AS incomeTotalAmount,\n" +
            "    COALESCE(SUM(CASE WHEN type = 2 THEN amount ELSE 0 END), 0) AS expenditureTotalAmount\n" +
            "FROM bill b\n" +
            "WHERE b.user_id = ?1 " +
            "AND type IN (1, 2)\n" +
            "AND ( \n" +
            "DATE_FORMAT(b.date, '%Y-%m') = CONCAT(?2, '-', ?3) \n" +
            ") \n" +
            "AND (?4 is null OR b.type = ?4)")
    Map<String, BigDecimal> findAmountByMonth(Long userId, String year, String month, Integer type);

    @Query(nativeQuery = true, value = "SELECT\n" +
            "    COALESCE(SUM(CASE WHEN type = 1 THEN amount ELSE 0 END), 0) AS incomeTotalAmount,\n" +
            "    COALESCE(SUM(CASE WHEN type = 2 THEN amount ELSE 0 END), 0) AS expenditureTotalAmount\n" +
            "FROM bill b\n" +
            "WHERE b.user_id = ?1 " +
            "AND type IN (1, 2)\n" +
            "AND ( \n" +
            "DATE_FORMAT(b.date, '%Y-%m-%d') = CONCAT(?2, '-', ?3, '-', ?4) \n" +
            ") \n" +
            "AND (?5 is null OR b.type = ?5)")
    Map<String, BigDecimal> findAmountByDay(Long userId, String year, String month, String day, Integer type);


    BillEntity findById(Long userId);
}
