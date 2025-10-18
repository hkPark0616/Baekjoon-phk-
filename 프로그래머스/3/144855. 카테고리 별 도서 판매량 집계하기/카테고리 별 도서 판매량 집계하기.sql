SELECT a.category, SUM(b.sales) AS total_sales
FROM book a
JOIN book_sales b ON a.book_id = b.book_id
WHERE YEAR(b.sales_date) = 2022 AND MONTH(b.sales_date) = 1
GROUP BY a.category
ORDER BY a.category;