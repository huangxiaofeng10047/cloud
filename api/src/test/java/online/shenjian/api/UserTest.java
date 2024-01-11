package online.shenjian.api;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import online.shenjian.client.cloud.dto.UserInfoDto;
import online.shenjian.api.entity.UserModel;
import online.shenjian.api.mapper.UserMapper;
import online.shenjian.api.mapper.UserPlusMapper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudApplication.class)
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 批量插入
     */
//    @Test
//    public void testBatchSave() {
//        List<UserModel> users = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            UserModel user = new UserModel();
//            user.setName("犬小哈" + i);
//            user.setUsername("qinxiaoha" + i);
//            user.setId(IdUtil.getSnowflakeNextIdStr());
//            users.add(user);
//        }
//        int i= userMapper.insertBatchSomeColumn(users);
//        Assert.isTrue(i==3,"插入失败");
//    }

    @Before
    public void testBefore(){
        System.out.println("before.. 每个Test都会执行一次");
    }
    @Test
    public void test1(){
        List<UserModel> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserModel user = new UserModel();
            user.setName("hxf" + i);
            user.setUsername("qinxiaoha" + i);
            user.setId(IdUtil.getSnowflakeNextIdStr());
            users.add(user);
        }
        int i= userMapper.insertBatchSomeColumn(users);


        System.out.println("toIndex:" + i);
    }
    @After
    public void TestAfter(){
        System.out.println("after... 每个Test都会执行一次");
    }

    @BeforeClass
    public static void initClass(){
        System.out.println("******测试开始初始化，必须是static方法，仅执行一次");
    }

    @AfterClass
    public static void endClass(){
        System.out.println("******测试结束初始化，必须是static方法，仅执行一次");
    }
    @Autowired
    private UserPlusMapper userPlusMapper;

    /**
     * 分页查询
     */
    @Test
    public void testPage() {
        IPage<UserInfoDto> page = new Page<>(1, 10);
        QueryWrapper<UserModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "sfxs");
        Page<UserInfoDto> resultPage = userPlusMapper.page(page, queryWrapper);
        Assert.assertNotNull(resultPage.getRecords());
        Assert.assertEquals(2, resultPage.getTotal());
    }
}

