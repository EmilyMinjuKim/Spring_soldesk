//package kr.co.soft.mapper;
//
//import java.util.List;
//
//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//
//import kr.co.soft.mapperVO.ProductVO;
//
//public interface ProductMapper {
//
//	@Insert("insert into RestFulDB values(#{id},#{name},#{brand},#{price})")
//	void registerProduct(ProductVO productVO);
//	
//	@Select("select * from RestFulDB")
//	List<ProductVO> getAllProductList();
//	
//	@Select("select * from RestFulDB where id=#{id}")
//	ProductVO findProductById(String id);
//	
//	@Update("update RestFulDB set name=#{name},brand=#{brand},price=#{price} where id=#{id}")
//	int updateProduct(ProductVO productVO);
//	
//	@Delete("delete from RestFulDB where id=#{id}")
//	void deleteProduct(String id);
//}
