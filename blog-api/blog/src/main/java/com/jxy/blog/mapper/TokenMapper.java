package  com.jxy.blog.mapper;

import java.util.List;
import  com.jxy.blog.domain.Token;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * TokenMapper接口
 * 
 * @author jxy
 * @date 2021-01-18
 */
@Repository
public interface TokenMapper 
{
    /**
     * 查询Token
     * 
     * @param token token
     * @return Token
     */
    public Token selectTokenById(String token);

    /**
     * 查询Token列表
     * 
     * @param token Token
     * @return Token集合
     */
    public List<Token> selectTokenList(Token token);

    /**
     * 新增Token
     * 
     * @param token Token
     * @return 结果
     */
    public int insertToken(Token token);

    /**
     * 修改Token
     * 
     * @param token Token
     * @return 结果
     */
    public int updateToken(Token token);

    /**
     * 删除Token
     * 
     * @param id userID
     * @return 结果
     */
    public int deleteTokenById(Long id);

    /**
     * 批量删除Token
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTokenByIds(Long[] ids);
}
