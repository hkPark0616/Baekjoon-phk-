SELECT CAR_TYPE, COUNT(*) AS CARS
FROM CAR_RENTAL_COMPANY_CAR
WHERE INSTR(OPTIONS, '통풍시트') | INSTR(OPTIONS, '열선시트') | INSTR(OPTIONS, '가죽시트')
GROUP BY CAR_TYPE
ORDER BY CAR_TYPE;