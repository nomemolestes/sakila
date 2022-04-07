package dao;

public class RentalDao {
	public List<Map<String, Object>> selectRentalSearchList() {
		/*
		 --고객이름 조인쿼리
SELECT  
	r.*,
	concat(c.first_name,' ',c.last_name) cName,
	s.store_id storeId,
	i.film_id filmId,
	f.title
FROM rental r 
INNER JOIN customer c
ON r.customer_id = c.customer_id
--위의 결과물과 staff를 조인
	INNER JOIN staff s
	ON r.staff_id = s.staff_id
--inventory_id를 통해 film_id출력
		INNER JOIN inventory i
		ON r.inventory_id = i.inventory_id
--film_id까지
			INNER JOIN film f
				ON i.film_id = f.film_id
--2번 가게에서 tony가 2006-02-01~2006-02-28까지 대여한 목록 출력
WHERE s.store_id=2 AND concat(c.first_name,' ',c.last_name) LIKE '%JOEL%(?가됨, %랑 이름값 모두 들어가야함)'
AND r.rental_date between STR_TO_DATE('2005-07-01'(?), '%Y-%m-%d')
AND STR_TO_DATE('2005-07-30'(?), '%Y-%m-%d');
		 */
	}

}
