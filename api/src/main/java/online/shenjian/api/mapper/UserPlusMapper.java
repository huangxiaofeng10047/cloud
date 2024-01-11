package online.shenjian.api.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import online.shenjian.client.cloud.dto.UserInfoDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 用户加强版Mapper
 */
@Repository
public interface UserPlusMapper extends BaseMapper<UserInfoDto> {

    /**
     * 直接写SQL即可，即使关联查询的结果也会映射到DTO中
     */
    String querySql = "SELECT " +
        "               u.username, u.name " +
        "          FROM " +
        "               user AS u";
    String wrapperSql = "SELECT * FROM ( " + querySql + " ) AS q ${ew.customSqlSegment}";

    @Select(wrapperSql)
    Page<UserInfoDto> page(IPage page, @Param("ew") Wrapper queryWrapper);
}
