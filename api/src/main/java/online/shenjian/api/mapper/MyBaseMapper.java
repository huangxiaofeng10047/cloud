package online.shenjian.api.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * MyBaseMapper支持高效批量插入
 * @author hxf16
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入
     *
     * @param batchList
     * @return
     */
    int insertBatchSomeColumn(@Param("list") List<T> batchList);
}
