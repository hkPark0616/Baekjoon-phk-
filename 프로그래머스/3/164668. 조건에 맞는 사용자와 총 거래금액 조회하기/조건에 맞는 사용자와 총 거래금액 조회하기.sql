SELECT b.user_id, b.nickname, SUM(a.price) AS total_sales
FROM used_goods_board a
JOIN used_goods_user b
ON a.writer_id = b.user_id
WHERE a.status = 'DONE'
GROUP BY a.writer_id
HAVING SUM(a.price) >= 700000
ORDER BY total_sales;