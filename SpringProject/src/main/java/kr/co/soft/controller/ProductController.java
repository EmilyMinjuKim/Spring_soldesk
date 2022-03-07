//package kr.co.soft.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import kr.co.soft.mapper.ProductMapper;
//import kr.co.soft.mapperVO.ProductVO;
//
//@SuppressWarnings({"unchecked", "rawtypes"})
//@RestController	//@Controller + @ResponseBody
//public class ProductController {
//
//	@Autowired
//	ProductMapper productMapper;	//ServletAppContext에 Bean처리되어있음.
//	
//	@GetMapping("/products")
//	public List getAllProductList(){
//		System.out.println("Request Method : GET");
//		
//		return productMapper.getAllProductList();
//	}
//	
//	@GetMapping("/products/{id}")
//	public ResponseEntity findProductById(@PathVariable("id") String id){
//		System.out.println("Request Method : GET");
//		
//		ProductVO product = productMapper.findProductById(id);
//		
//		if(product == null) 
//			return new ResponseEntity("존재하지 않는 상품입니다.", HttpStatus.NOT_FOUND);
//		
//		return new ResponseEntity(product, HttpStatus.OK);
//	}
//	
//	@PostMapping(value = "/products")
//	public ResponseEntity registerProduct(ProductVO productVO){
//		System.out.println("Request Method : POST");
//		
//		productMapper.registerProduct(productVO);
//		
//		return new ResponseEntity(productVO.getId()+" "+productVO.getName()+" 상품등록 완료.", HttpStatus.OK);
//	}
//	
//	@PutMapping("/products")
//	public ResponseEntity updateProduct(ProductVO productVO){
//		System.out.println("Request Method : PUT");
//		
//		int result = productMapper.updateProduct(productVO);
//		String id = productVO.getId();
//		
//		if(result == 0) 
//			return new ResponseEntity(id+"번은 수정할 수 없는 상품입니다.", HttpStatus.NOT_FOUND);
//		
//		return new ResponseEntity(id+"번 상품 수정 완료.", HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/products/{id}")
//	public ResponseEntity deleteProduct(@PathVariable String id){
//		System.out.println("Request Method : DELETE");
//
//		productMapper.deleteProduct(id);
//		
//		return new ResponseEntity(id+"번 상품 삭제 완료.", HttpStatus.OK);
//	}
//	
//}
