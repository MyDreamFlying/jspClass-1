package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BuyerVO;

@Repository
public interface IOthersDAO {
	public List<Map<String, Object>> selectLprodList();
	public List<BuyerVO> selectBuyerList(@Param("prod_lgu") String buyer_lgu);
}
