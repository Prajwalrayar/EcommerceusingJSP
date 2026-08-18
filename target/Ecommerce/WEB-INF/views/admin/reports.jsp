<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.crimsonlogic.ecommerce.dao.ReportMapper">

	<!-- ===================================================== SALES REPORT
		===================================================== -->

	<select id="getSalesReport"
		resultType="com.crimsonlogic.ecommerce.model.report.SalesReport">

		SELECT

		COUNT(DISTINCT o.order_id) AS totalOrders,

		COALESCE(SUM(o.quantity), 0) AS totalQuantity,

		COALESCE(SUM(o.total_price), 0) AS totalSales,

		COALESCE(
		AVG(o.total_price),
		0
		) AS averageOrderValue

		FROM orders o

		INNER JOIN products p
		ON o.product_id = p.product_id

		INNER JOIN categories c
		ON p.category_id = c.category_id

		INNER JOIN sellers s
		ON p.user_id = s.user_id

		<if test="paymentStatus != null and paymentStatus != ''">

			INNER JOIN payments pay
			ON o.order_id = pay.order_id

		</if>

		WHERE 1 = 1

		<!-- Customer restriction -->

		<if test="customerId != null and customerId != ''">

			AND o.customer_id = #{customerId}

		</if>

		<!-- Seller restriction -->

		<if test="sellerId != null and sellerId != ''">

			AND p.user_id = #{sellerId}

		</if>

		<!-- Category -->

		<if test="categoryId != null and categoryId != ''">

			AND p.category_id = #{categoryId}

		</if>

		<!-- Product -->

		<if test="productId != null and productId != ''">

			AND p.product_id = #{productId}

		</if>

		<!-- Product Name -->

		<if test="productName != null and productName != ''">

			AND LOWER(p.product_name)
			LIKE CONCAT(
			'%',
			LOWER(#{productName}),
			'%'
			)

		</if>

		<!-- Product Price -->

		<if test="minPrice != null">

			AND p.product_price >= #{minPrice}

		</if>

		<if test="maxPrice != null">

			AND p.product_price <= #{maxPrice}

		</if>

		<!-- Quantity -->

		<if test="minQuantity != null">

			AND o.quantity >= #{minQuantity}

		</if>

		<if test="maxQuantity != null">

			AND o.quantity <= #{maxQuantity}

		</if>

		<!-- Order Status -->

		<if test="orderStatus != null and orderStatus != ''">

			AND o.order_status = #{orderStatus}

		</if>

		<!-- Payment Status -->

		<if test="paymentStatus != null and paymentStatus != ''">

			AND pay.payment_status = #{paymentStatus}

		</if>

		<!-- Date -->

		<if test="fromDate != null">

			AND o.order_date >= #{fromDate}

		</if>

		<if test="toDate != null">

			AND o.order_date <= #{toDate}

		</if>

	</select>

	<!-- ===================================================== PRODUCT SALES
		REPORT ===================================================== -->

	<select id="getProductSalesReport"
		resultType="com.crimsonlogic.ecommerce.model.report.ProductSalesReport">

		SELECT

		p.product_id AS productId,

		p.product_name AS productName,

		c.category_name AS categoryName,

		s.user_id AS sellerId,

		s.user_name AS sellerName,

		p.product_price AS productPrice,

		COALESCE(
		SUM(o.quantity),
		0
		) AS quantitySold,

		COALESCE(
		SUM(o.total_price),
		0
		) AS revenue,

		COALESCE(
		AVG(r.rating),
		0
		) AS averageRating,

		COUNT(DISTINCT r.review_id) AS reviewCount

		FROM products p

		INNER JOIN categories c
		ON p.category_id = c.category_id

		INNER JOIN sellers s
		ON p.user_id = s.user_id

		LEFT JOIN orders o
		ON p.product_id = o.product_id

		LEFT JOIN review r
		ON p.product_id = r.product_id

		<if test="paymentStatus != null and paymentStatus != ''">

			LEFT JOIN payments pay
			ON o.order_id = pay.order_id

		</if>

		WHERE 1 = 1

		<if test="sellerId != null and sellerId != ''">

			AND p.user_id = #{sellerId}

		</if>

		<if test="customerId != null and customerId != ''">

			AND o.customer_id = #{customerId}

		</if>

		<if test="categoryId != null and categoryId != ''">

			AND p.category_id = #{categoryId}

		</if>

		<if test="productId != null and productId != ''">

			AND p.product_id = #{productId}

		</if>

		<if test="productName != null and productName != ''">

			AND LOWER(p.product_name)
			LIKE CONCAT(
			'%',
			LOWER(#{productName}),
			'%'
			)

		</if>

		<if test="minPrice != null">

			AND p.product_price >= #{minPrice}

		</if>

		<if test="maxPrice != null">

			AND p.product_price <= #{maxPrice}

		</if>

		<if test="minQuantity != null">

			AND o.quantity >= #{minQuantity}

		</if>

		<if test="maxQuantity != null">

			AND o.quantity <= #{maxQuantity}

		</if>

		<if test="orderStatus != null and orderStatus != ''">

			AND o.order_status = #{orderStatus}

		</if>

		<if test="paymentStatus != null and paymentStatus != ''">

			AND pay.payment_status = #{paymentStatus}

		</if>

		<if test="fromDate != null">

			AND o.order_date >= #{fromDate}

		</if>

		<if test="toDate != null">

			AND o.order_date <= #{toDate}

		</if>

		GROUP BY

		p.product_id,
		p.product_name,
		c.category_name,
		s.user_id,
		s.user_name,
		p.product_price

		ORDER BY quantitySold DESC

	</select>

	<!-- ===================================================== CATEGORY SALES
		REPORT ===================================================== -->

	<select id="getCategorySalesReport"
		resultType="com.crimsonlogic.ecommerce.model.report.CategorySalesReport">

		SELECT

		c.category_id AS categoryId,

		c.category_name AS categoryName,

		COUNT(DISTINCT p.product_id) AS productCount,

		COALESCE(
		SUM(o.quantity),
		0
		) AS quantitySold,

		COALESCE(
		SUM(o.total_price),
		0
		) AS revenue

		FROM categories c

		INNER JOIN products p
		ON c.category_id = p.category_id

		LEFT JOIN orders o
		ON p.product_id = o.product_id

		WHERE 1 = 1

		<if test="sellerId != null and sellerId != ''">

			AND p.user_id = #{sellerId}

		</if>

		<if test="customerId != null and customerId != ''">

			AND o.customer_id = #{customerId}

		</if>

		<if test="categoryId != null and categoryId != ''">

			AND c.category_id = #{categoryId}

		</if>

		<if test="minPrice != null">

			AND p.product_price >= #{minPrice}

		</if>

		<if test="maxPrice != null">

			AND p.product_price <= #{maxPrice}

		</if>

		<if test="minQuantity != null">

			AND o.quantity >= #{minQuantity}

		</if>

		<if test="maxQuantity != null">

			AND o.quantity <= #{maxQuantity}

		</if>

		<if test="orderStatus != null and orderStatus != ''">

			AND o.order_status = #{orderStatus}

		</if>

		<if test="fromDate != null">

			AND o.order_date >= #{fromDate}

		</if>

		<if test="toDate != null">

			AND o.order_date <= #{toDate}

		</if>

		GROUP BY

		c.category_id,
		c.category_name

		ORDER BY revenue DESC

	</select>

	<!-- ===================================================== SELLER SALES
		REPORT ===================================================== -->

	<select id="getSellerSalesReport"
		resultType="com.crimsonlogic.ecommerce.model.report.SellerSalesReport">

		SELECT

		s.user_id AS sellerId,

		s.user_name AS sellerName,

		s.shop_name AS shopName,

		COUNT(DISTINCT o.order_id) AS orderCount,

		COALESCE(
		SUM(o.quantity),
		0
		) AS quantitySold,

		COALESCE(
		SUM(o.total_price),
		0
		) AS revenue

		FROM sellers s

		INNER JOIN products p
		ON s.user_id = p.user_id

		LEFT JOIN orders o
		ON p.product_id = o.product_id

		WHERE 1 = 1

		<if test="sellerId != null and sellerId != ''">

			AND s.user_id = #{sellerId}

		</if>

		<if test="categoryId != null and categoryId != ''">

			AND p.category_id = #{categoryId}

		</if>

		<if test="productId != null and productId != ''">

			AND p.product_id = #{productId}

		</if>

		<if test="minPrice != null">

			AND p.product_price >= #{minPrice}

		</if>

		<if test="maxPrice != null">

			AND p.product_price <= #{maxPrice}

		</if>

		<if test="orderStatus != null and orderStatus != ''">

			AND o.order_status = #{orderStatus}

		</if>

		<if test="fromDate != null">

			AND o.order_date >= #{fromDate}

		</if>

		<if test="toDate != null">

			AND o.order_date <= #{toDate}

		</if>

		GROUP BY

		s.user_id,
		s.user_name,
		s.shop_name

		ORDER BY revenue DESC

	</select>

	<!-- ===================================================== CUSTOMER SUMMARY
		===================================================== -->

	<select id="getCustomerReport"
		resultType="com.crimsonlogic.ecommerce.model.report.CustomerReport">

		SELECT

		c.user_id AS customerId,

		c.user_name AS customerName,

		COUNT(DISTINCT o.order_id) AS orderCount,

		COALESCE(
		SUM(o.quantity),
		0
		) AS quantityPurchased,

		COALESCE(
		SUM(o.total_price),
		0
		) AS totalSpent,

		COALESCE(
		AVG(o.total_price),
		0
		) AS averageOrderValue

		FROM customers c

		LEFT JOIN orders o
		ON c.user_id = o.customer_id

		LEFT JOIN products p
		ON o.product_id = p.product_id

		WHERE 1 = 1

		<!-- VERY IMPORTANT: Customer report is restricted to customer -->

		<if test="customerId != null and customerId != ''">

			AND c.user_id = #{customerId}

		</if>

		<if test="categoryId != null and categoryId != ''">

			AND p.category_id = #{categoryId}

		</if>

		<if test="productId != null and productId != ''">

			AND p.product_id = #{productId}

		</if>

		<if test="productName != null and productName != ''">

			AND LOWER(p.product_name)
			LIKE CONCAT(
			'%',
			LOWER(#{productName}),
			'%'
			)

		</if>

		<if test="minPrice != null">

			AND p.product_price >= #{minPrice}

		</if>

		<if test="maxPrice != null">

			AND p.product_price <= #{maxPrice}

		</if>

		<if test="orderStatus != null and orderStatus != ''">

			AND o.order_status = #{orderStatus}

		</if>

		<if test="fromDate != null">

			AND o.order_date >= #{fromDate}

		</if>

		<if test="toDate != null">

			AND o.order_date <= #{toDate}

		</if>

		GROUP BY

		c.user_id,
		c.user_name

	</select>

	<!-- ===================================================== CUSTOMER PRODUCT
		REPORT ===================================================== -->

	<select id="getCustomerProductReport"
		resultType="com.crimsonlogic.ecommerce.model.report.ProductSalesReport">

		SELECT

		p.product_id AS productId,

		p.product_name AS productName,

		c.category_name AS categoryName,

		s.user_id AS sellerId,

		s.user_name AS sellerName,

		p.product_price AS productPrice,

		SUM(o.quantity) AS quantitySold,

		SUM(o.total_price) AS revenue,

		0 AS averageRating,

		0 AS reviewCount

		FROM orders o

		INNER JOIN products p
		ON o.product_id = p.product_id

		INNER JOIN categories c
		ON p.category_id = c.category_id

		INNER JOIN sellers s
		ON p.user_id = s.user_id

		WHERE o.customer_id = #{customerId}

		<if test="categoryId != null and categoryId != ''">

			AND p.category_id = #{categoryId}

		</if>

		<if test="productId != null and productId != ''">

			AND p.product_id = #{productId}

		</if>

		<if test="productName != null and productName != ''">

			AND LOWER(p.product_name)
			LIKE CONCAT(
			'%',
			LOWER(#{productName}),
			'%'
			)

		</if>

		<if test="minPrice != null">

			AND p.product_price >= #{minPrice}

		</if>

		<if test="maxPrice != null">

			AND p.product_price <= #{maxPrice}

		</if>

		<if test="minQuantity != null">

			AND o.quantity >= #{minQuantity}

		</if>

		<if test="maxQuantity != null">

			AND o.quantity <= #{maxQuantity}

		</if>

		<if test="orderStatus != null and orderStatus != ''">

			AND o.order_status = #{orderStatus}

		</if>

		<if test="fromDate != null">

			AND o.order_date >= #{fromDate}

		</if>

		<if test="toDate != null">

			AND o.order_date <= #{toDate}

		</if>

		GROUP BY

		p.product_id,
		p.product_name,
		c.category_name,
		s.user_id,
		s.user_name,
		p.product_price

		ORDER BY quantitySold DESC

	</select>

</mapper>