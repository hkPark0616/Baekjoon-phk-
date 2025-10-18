# 
SELECT COUNT(a.fish_type) AS fish_count, b.fish_name
FROM fish_info a 
JOIN fish_name_info b ON a.fish_type = b.fish_type
GROUP BY a.fish_type, b.fish_name
ORDER BY fish_count DESC;